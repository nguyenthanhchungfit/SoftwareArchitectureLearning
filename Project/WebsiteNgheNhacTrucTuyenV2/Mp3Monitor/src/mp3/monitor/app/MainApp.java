/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.monitor.app;

import mp3.monitor.contract.ServerConfig;
import mp3.monitor.servers.WebsocketServer;

/**
 *
 * @author chungnt
 */
public class MainApp {
    public static void main(String[] args) {
        WebsocketServer server = new WebsocketServer(ServerConfig.SERVER_HOST, 
                ServerConfig.SERVER_NAME, ServerConfig.SERVER_PORT);
        
        server.start();
    }
}
