/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import gamer.Gamer;
import java.util.Arrays;
import notificationpusher.PushNotificationServer;
import server.GameServer;
import server.Newsfeed;

/**
 *
 * @author chungnt
 */
public class App {
    public static void main(String[] args) {
        Gamer gamer1 = new Gamer("chungnt", 21, "Chung ku", 
                Arrays.asList(new String[]{"Kiếm thế", "boom", "Leage of legends"}));
        
        Gamer gamer2 = new Gamer("chungnt2", 18, "Chung987",
                Arrays.asList(new String[]{"Võ lâm truyền kỳ", "Phong thần", "Đột kích"}));
        
        PushNotificationServer pushNotificationServer = new PushNotificationServer();
        
        gamer1.subcribe(pushNotificationServer);
        gamer2.subcribe(pushNotificationServer);
        
        GameServer gameServerVng = new GameServer("VNG");
        GameServer gameServerVtc = new GameServer("VTC");
        GameServer gameServerGarena = new GameServer("Garena");
        
        gameServerVng.postNewsfeed(pushNotificationServer, 
                new Newsfeed("Event tháng 9", "Kiếm thế ra mắt event mới"));
        gameServerVng.postNewsfeed(pushNotificationServer, 
                new Newsfeed("Event tháng 10", "Võ lâm truyền kỳ cho ra server Phụng Kiếm"));
        gameServerVtc.postNewsfeed(pushNotificationServer, 
                new Newsfeed("Event quốc khánh", "Đột kích ra loại súng mới tự bắn"));
        gameServerGarena.postNewsfeed(pushNotificationServer, 
                new Newsfeed("Event trung thu", "Leage of legends chung kết thế giới 2018"));
    }
}
