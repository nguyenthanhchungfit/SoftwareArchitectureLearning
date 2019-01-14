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
import mp3.utils.thrift.models.TSong;

/**
 *
 * @author chungnt
 */
public class SongRedisClient extends RedisClient {

    public SongRedisClient() {
    }

    public SongRedisClient(String name) {
        super(name);
    }

    public SongRedisClient(String name, int maxAge) {
        super(name, maxAge);
    }

    public TSong getCachedSong(String key) {
        if (!super.isExistedKey(key)) {
            return null;
        }

        TSong song = new TSong();
        TModelInitiation.initTSong(song);

        song.id = _jedisClient.hget(key, "id");
        song.name = _jedisClient.hget(key, "name");
        song.lyrics = _jedisClient.hget(key, "lyric");
        song.kara = _jedisClient.hget(key, "kara");
        song.duration = Short.parseShort(_jedisClient.hget(key, "duration"));
        song.views = Long.parseLong(_jedisClient.hget(key, "views"));
        song.comment = _jedisClient.hget(key, "comment");
        song.image = _jedisClient.hget(key, "image");

        _jedisClient.expire(key, maxAge);

        // get Album
        String keySongAlbum = key + ":album";
        song.album.id = _jedisClient.hget(keySongAlbum, "id");
        song.album.name = _jedisClient.hget(keySongAlbum, "name");
        _jedisClient.expire(keySongAlbum, maxAge);

        // get Composer
        int composer_size = Integer.parseInt(_jedisClient.hget(key, "amount_composer"));
        for (int i = 0; i < composer_size; i++) {
            String keyComposer = key + ":composer:" + i;
            _jedisClient.expire(keyComposer, maxAge);
            String name = _jedisClient.hget(keyComposer, "name");
            song.composers.add(name);
        }

        // get Kind
        int kind_size = Integer.parseInt(_jedisClient.hget(key, "amount_kind"));
        for (int i = 0; i < kind_size; i++) {
            String keyKind = key + ":kind:" + i;
            _jedisClient.expire(keyKind, maxAge);
            String id = _jedisClient.hget(keyKind, "id");
            String name = _jedisClient.hget(keyKind, "name");
            song.kinds.add(new TReferencer(id, name));
        }

        // getSinger
        int singer_size = Integer.parseInt(_jedisClient.hget(key, "amount_singer"));
        for (int i = 0; i < singer_size; i++) {
            String keySinger = key + ":singer:" + i;
            _jedisClient.expire(keySinger, maxAge);
            String id = _jedisClient.hget(keySinger, "id");
            String name = _jedisClient.hget(keySinger, "name");
            song.singers.add(new TReferencer(id, name));
        }
        return song;
    }

    public void putNewSongCache(TSong song) {
        String keySong = "song:" + song.id;

        Map<String, String> mapSong = new HashMap<>();
        mapSong.put("id", song.id);
        mapSong.put("name", song.name);
        mapSong.put("lyric", song.lyrics);
        mapSong.put("kara", song.kara);
        mapSong.put("duration", ((Short) song.duration).toString());
        mapSong.put("views", ((Long) song.views).toString());
        mapSong.put("comment", song.comment);
        mapSong.put("image", song.image);

        String keySongAlbum = keySong + ":album";
        mapSong.put("album", keySongAlbum);
        mapSong.put("amount_composer", ((Integer) song.composers.size()).toString());
        mapSong.put("amount_singer", ((Integer) song.singers.size()).toString());
        mapSong.put("amount_kind", ((Integer) song.kinds.size()).toString());

        _jedisClient.hmset(keySong, mapSong);
        _jedisClient.expire(keySong, maxAge);

        // Tạo key album 
        Map<String, String> mapSongAlbum = new HashMap<>();
        mapSongAlbum.put("id", song.album.id);
        mapSongAlbum.put("name", song.album.name);
        _jedisClient.hmset(keySongAlbum, mapSongAlbum);
        _jedisClient.expire(keySongAlbum, maxAge);

        // Lưu tác giả
        int index = 0;
        for (String composer : song.composers) {
            String keyComposer = keySong + ":composer:" + index;
            Map<String, String> mapComposer = new HashMap<>();
            mapComposer.put("name", composer);
            _jedisClient.hmset(keyComposer, mapComposer);
            _jedisClient.expire(keyComposer, maxAge);
            index++;
        }

        // Lưu loại kind
        index = 0;
        for (TReferencer ref : song.kinds) {
            String keyKind = keySong + ":kind:" + index;
            Map<String, String> mapKind = new HashMap<>();
            mapKind.put("id", ref.id);
            mapKind.put("name", ref.name);
            _jedisClient.hmset(keyKind, mapKind);
            _jedisClient.expire(keyKind, maxAge);
            index++;
        }

        // Lưu loại kind
        index = 0;
        for (TReferencer ref : song.singers) {
            String keySinger = keySong + ":singer:" + index;
            Map<String, String> mapSinger = new HashMap<>();
            mapSinger.put("id", ref.id);
            mapSinger.put("name", ref.name);
            _jedisClient.hmset(keySinger, mapSinger);
            _jedisClient.expire(keySinger, maxAge);
            index++;
        }
    }
    
    public void deleteCacheSongAt(String key){
        _jedisClient.del(key);
        _jedisClient.del(key + ":album");
        int composer_size = Integer.parseInt(_jedisClient.hget(key, "amount_composer"));
        for (int i = 0; i < composer_size; i++) {
            _jedisClient.del(key + ":composer:" + i);
        }
        int kind_size = Integer.parseInt(_jedisClient.hget(key, "amount_kind"));
        for (int i = 0; i < kind_size; i++) {
            _jedisClient.del(key + ":kind:" + i);
        }
        int singer_size = Integer.parseInt(_jedisClient.hget(key, "amount_singer"));
        for (int i = 0; i < singer_size; i++) {
            _jedisClient.del(key + ":singer:" + i);
        }
    }
}
