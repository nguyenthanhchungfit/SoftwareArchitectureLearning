/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notificationpusher;

import java.util.HashMap;
import java.util.Map;
import server.GameServer;
import server.Newsfeed;

/**
 *
 * @author chungnt
 */
public class PushNotificationServer {
    private Map<Integer, Object> newsfeed;
    private Map<Integer, Object> subcribers;

    public PushNotificationServer() {
        newsfeed = new HashMap<>();
        subcribers = new HashMap<>();
    }

    private int nextSubcriberHandle = 1;
    private int nextNewfeedHandle = 1;
    
    public PushNotificationServer(Map<Integer, Object> newsfeed, Map<Integer, Object> subcribers) {
        this.newsfeed = newsfeed;
        this.subcribers = subcribers;
    }
    
    public int postNewsfeed(GameServer gameServer, Newsfeed newsfeed){
        this.newsfeed.put(nextNewfeedHandle, newsfeed);
        
        return nextNewfeedHandle++;
    }
    
    
}
