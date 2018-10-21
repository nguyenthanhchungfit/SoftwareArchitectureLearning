/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import notificationpusher.PushNotificationServer;

/**
 *
 * @author chungnt
 */
public class GameServer {
    private String name;

    public GameServer() {
    }

    public GameServer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void postNewsfeed(PushNotificationServer notice, Newsfeed news){
        notice.postNewsfeed(this, news);
    }
}
