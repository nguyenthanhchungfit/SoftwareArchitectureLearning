/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notificationpusher;

import gamer.Gamer;
import helpers.ContentCompatibilityChecker;
import helpers.KeywordsMatcher;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import server.GameServer;
import server.Newsfeed;

/**
 *
 * @author chungnt
 */
public class PushNotificationServer {

    private Map<Integer, Object> newsfeed;
    private Map<Integer, Object> subcribers;
    private static ContentCompatibilityChecker checker = new KeywordsMatcher();

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

    public int postNewsfeed(GameServer gameServer, Newsfeed newsfeed) {
        this.newsfeed.put(nextNewfeedHandle, newsfeed);
        Set<Integer> keys = subcribers.keySet();
        for(Integer key : keys){
            Gamer gamer = (Gamer) subcribers.get(key);
            if(checker.check(newsfeed, gamer)){
                gamer.notify(newsfeed);
            }
        }
        return nextNewfeedHandle++;
    }

    public int subcribe(Gamer gamer) {
        subcribers.put(nextSubcriberHandle, gamer);
        return nextSubcriberHandle++;
    }
}
