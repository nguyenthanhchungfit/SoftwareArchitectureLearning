/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.utils.cache.clients;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

/**
 *
 * @author chungnt
 */
public class RedisClient {
    
    protected static final Logger LOGGER = Logger.getLogger(RedisClient.class);
     
    protected Jedis _jedisClient;
    protected String name;
    protected int maxAge = 10000;
    
    public RedisClient(){
        _init();
    }
    
    public RedisClient(String name){
        this.name = name;
        _init();
    }
    
    public RedisClient(String name, int maxAge){
        this.maxAge = maxAge;
        this.name = name;
        _init();
    }
    
    protected void _init(){
        _jedisClient = new Jedis(RedisConfig.REDIS_SERVER_HOST, RedisConfig.REDIS_SERVER_PORT
                , maxAge);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }
   
    public boolean isExistedKey(String key){
        boolean isExisted = false;
        try{
            isExisted = _jedisClient.exists(key);
        }catch(Exception ex){
            LOGGER.error(ex.getMessage(), ex);
        }
        return isExisted;
    }
    
    public long getTimeToLive(String key){
        return _jedisClient.ttl(key);
    }

}
