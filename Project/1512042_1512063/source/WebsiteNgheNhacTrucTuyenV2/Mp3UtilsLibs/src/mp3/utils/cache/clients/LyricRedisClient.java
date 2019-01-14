/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.utils.cache.clients;

import java.util.HashMap;
import java.util.Map;
import mp3.utils.thrift.initiation.TModelInitiation;
import mp3.utils.thrift.models.TDataLyric;
import mp3.utils.thrift.models.TLyric;

/**
 *
 * @author chungnt
 */
public class LyricRedisClient extends RedisClient {

    public TLyric getCachedLyric(String key) {
        if (!super.isExistedKey(key)) {
            return null;
        }

        TLyric lyric = new TLyric();
        TModelInitiation.initTLyric(lyric);

        lyric.id = _jedisClient.hget(key, "id");
        _jedisClient.expire(key, maxAge);
        int dataLyricSize = Integer.parseInt(_jedisClient.hget(key, "amount_data_lyrics"));

        for (int i = 0; i < dataLyricSize; i++) {
            String keyData = key + ":data:" + i;
            String contributor = _jedisClient.hget(keyData, "contributor");
            String content = _jedisClient.hget(keyData, "content");
            lyric.datas.add(new TDataLyric(contributor, content));
            _jedisClient.expire(keyData, maxAge);
        }
        return lyric;
    }

    public void putNewLyricCache(TLyric lyric) {
        String keyLyric = "lyric:" + lyric.id;
        Map<String, String> mapLyric = new HashMap<>();
        mapLyric.put("id", lyric.id);
        mapLyric.put("amount_data_lyrics", lyric.datas.size() + "");

        _jedisClient.hmset(keyLyric, mapLyric);
        _jedisClient.expire(keyLyric, maxAge);

        int index = 0;
        for (TDataLyric dataLyric : lyric.datas) {
            String keyData = keyLyric + ":data:" + index;
            Map<String, String> mapDataLyric = new HashMap<>();
            mapDataLyric.put("contributor", dataLyric.contributor);
            mapDataLyric.put("content", dataLyric.content);

            _jedisClient.hmset(keyData, mapDataLyric);
            _jedisClient.expire(keyData, maxAge);
            index++;
        }
    }

    public void deleteLyricCacheAt(String key) {
        _jedisClient.del(key);

        int dataLyricSize = Integer.parseInt(_jedisClient.hget(key, "amount_data_lyrics"));
        for (int i = 0; i < dataLyricSize; i++) {
            _jedisClient.del(key + ":data:" + i);
        }
    }
}
