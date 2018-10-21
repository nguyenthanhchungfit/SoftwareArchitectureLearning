/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamer;

import java.util.ArrayList;
import java.util.List;
import notificationpusher.PushNotificationServer;
import server.Newsfeed;

/**
 *
 * @author chungnt
 */
public class Gamer {
    private String name;
    private int age;
    private String nickName;
    private List<String> gameNames;

    public Gamer() {
        gameNames = new ArrayList<>();
    }

    public Gamer(String name, int age, String nickName, List<String> gameNames) {
        this.name = name;
        this.age = age;
        this.nickName = nickName;
        this.gameNames = gameNames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<String> getGameNames() {
        return gameNames;
    }

    public void setGameNames(List<String> gameNames) {
        this.gameNames = gameNames;
    }
    
    public void subcribe(PushNotificationServer pushGameServer){
        pushGameServer.subcribe(this);
    }
    
    public void notify(Newsfeed newsfeed){
        String notify = String.format("Notify to %s\n%s", this.nickName, newsfeed);
        System.out.println(notify);
    }
}
