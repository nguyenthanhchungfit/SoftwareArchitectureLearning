/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.utils.cache.clients;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import mp3.utils.entities.Session;
import mp3.utils.impl.DatatypeParserHelper;

/**
 *
 * @author chungnt
 */
public class SessionRedisClient extends RedisClient{

    public SessionRedisClient() {
    }

    public SessionRedisClient(String name) {
        super(name);
    }

    public SessionRedisClient(String name, int maxAge) {
        super(name, maxAge);
    }
    
    public Session getCacheSession(String key){
        Session session = null;
        if(_jedisClient.exists(key)){
            session = new Session();
            
            String reSessionId = _jedisClient.hget(key, "sessionId");
            String reUsername = _jedisClient.hget(key, "username");
            String reType = _jedisClient.hget(key, "type");
            String reExpires = _jedisClient.hget(key, "expires");
            String reLastAccess = _jedisClient.hget(key, "lastAccess");
            String reMaxAge = _jedisClient.hget(key, "maxAge");
            
            session.setSessionID(reSessionId);
            session.setUsername(reUsername);
            session.setType(DatatypeParserHelper.parseStringToInt(reType));
            session.setExpires(new Date(DatatypeParserHelper.parseStringToLong(reExpires)));
            session.setLastAccess(new Date(DatatypeParserHelper.parseStringToLong(reLastAccess)));
            session.setMaxAge(DatatypeParserHelper.parseStringToInt(reMaxAge));
         }
        return session;
    }
    
    public void putNewCacheSession(Session session){
        String keySession = "session:" + session.getSessionID();
        Map<String, String> mapSession = new HashMap<>();
        mapSession.put("sessionId", session.getSessionID());
        mapSession.put("username", session.getUsername());
        mapSession.put("type", session.getType() + "");
        mapSession.put("expires", session.getExpires().getTime() +"");
        mapSession.put("lastAccess", session.getLastAccess().getTime()+"");
        mapSession.put("maxAge", session.getMaxAge()+"");
        _jedisClient.hmset(keySession, mapSession);
    }
    
    public void deleteCacheSessionAt(String key){
        _jedisClient.del(key);
    }
}
