/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.utils.cache.clients;

import java.util.HashMap;
import java.util.Map;
import mp3.utils.thrift.initiation.TModelInitiation;
import mp3.utils.thrift.models.TReferencer;
import mp3.utils.thrift.models.TSinger;

/**
 *
 * @author chungnt
 */
public class SingerRedisClient extends RedisClient {

    public SingerRedisClient() {
    }

    public SingerRedisClient(String name) {
        super(name);
    }

    public SingerRedisClient(String name, int maxAge) {
        super(name, maxAge);
    }

    public TSinger getCachedSinger(String key) {
        if (!super.isExistedKey(key)) {
            return null;
        }

        TSinger singer = new TSinger();
        TModelInitiation.initTSinger(singer);

        singer.id = _jedisClient.hget(key, "id");
        singer.name = _jedisClient.hget(key, "name");
        singer.realname = _jedisClient.hget(key, "realname");
        singer.dob = _jedisClient.hget(key, "dob");
        singer.country = _jedisClient.hget(key, "country");
        singer.description = _jedisClient.hget(key, "description");
        singer.imgAvatar = _jedisClient.hget(key, "img_avatar");
        singer.imgCover = _jedisClient.hget(key, "img_cover");
        _jedisClient.expire(key, maxAge);

        // getSong
        int song_size = Integer.parseInt(_jedisClient.hget(key, "amount_song"));
        for (int i = 0; i < song_size; i++) {
            String keySong = key + ":song:" + i;
            _jedisClient.expire(keySong, maxAge);
            String id = _jedisClient.hget(keySong, "id");
            String name = _jedisClient.hget(keySong, "name");
            singer.songs.add(new TReferencer(id, name));
        }

        // getAlbum
        int album_size = Integer.parseInt(_jedisClient.hget(key, "amount_album"));
        for (int i = 0; i < album_size; i++) {
            String keyAlbum = key + ":album:" + i;
            _jedisClient.expire(keyAlbum, maxAge);
            String id = _jedisClient.hget(keyAlbum, "id");
            String name = _jedisClient.hget(keyAlbum, "name");
            singer.albums.add(new TReferencer(id, name));
        }

        // getVideo
        int video_size = Integer.parseInt(_jedisClient.hget(key, "amount_video"));
        for (int i = 0; i < video_size; i++) {
            String keyVideo = key + ":video:" + i;
            _jedisClient.expire(keyVideo, maxAge);
            String id = _jedisClient.hget(keyVideo, "id");
            String name = _jedisClient.hget(keyVideo, "name");
            singer.videos.add(new TReferencer(id, name));
        }

        return singer;
    }

    public void putNewSingerCache(TSinger singer) {
        String keySinger = "singer:" + singer.id;

        Map<String, String> mapSinger = new HashMap<>();
        mapSinger.put("id", singer.id);
        mapSinger.put("name", singer.name);
        mapSinger.put("realname", singer.realname);
        mapSinger.put("dob", singer.dob);
        mapSinger.put("country", singer.country);
        mapSinger.put("description", singer.description);
        mapSinger.put("img_avatar", singer.imgAvatar);
        mapSinger.put("img_cover", singer.imgCover);
        mapSinger.put("amount_song", singer.songs.size() + "");
        mapSinger.put("amount_album", singer.albums.size() + "");
        mapSinger.put("amount_video", singer.videos.size() + "");
        _jedisClient.hmset(keySinger, mapSinger);
        _jedisClient.expire(keySinger, maxAge);

        // Lưu album
        int index = 0;
        for (TReferencer ref : singer.albums) {
            String keyAlbum = keySinger + ":album:" + index;
            Map<String, String> mapAlbum = new HashMap<>();
            mapAlbum.put("id", ref.id);
            mapAlbum.put("name", ref.name);
            _jedisClient.hmset(keyAlbum, mapAlbum);
            _jedisClient.expire(keyAlbum, maxAge);
            index++;
        }

        // Lưu loại song
        index = 0;
        for (TReferencer ref : singer.songs) {
            String keySong = keySinger + ":song:" + index;
            Map<String, String> mapSong = new HashMap<>();
            mapSong.put("id", ref.id);
            mapSong.put("name", ref.name);
            _jedisClient.hmset(keySong, mapSong);
            _jedisClient.expire(keySong, maxAge);
            index++;
        }

        // Lưu loại video
        index = 0;
        for (TReferencer ref : singer.videos) {
            String keyVideo = keySinger + ":video:" + index;
            Map<String, String> mapVideo = new HashMap<>();
            mapVideo.put("id", ref.id);
            mapVideo.put("name", ref.name);
            _jedisClient.hmset(keyVideo, mapVideo);
            _jedisClient.expire(keyVideo, maxAge);
            index++;
        }
    }

    public void deleteSingerCacheAt(String key) {
        _jedisClient.del(key);
        int song_size = Integer.parseInt(_jedisClient.hget(key, "amount_song"));
        for (int i = 0; i < song_size; i++) {
            _jedisClient.del(key + ":song:" + i);
        }

        int album_size = Integer.parseInt(_jedisClient.hget(key, "amount_album"));
        for (int i = 0; i < album_size; i++) {
            _jedisClient.del(key + ":album:" + i);
        }
        int video_size = Integer.parseInt(_jedisClient.hget(key, "amount_video"));
        for (int i = 0; i < video_size; i++) {
            _jedisClient.del(key + ":video:" + i);
        }
    }
}
