/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.app;

import mp3.me.contracts.ServerConfig;
import mp3.me.server.HServer;

/**
 *
 * @author chungnt
 */
public class Mp3MainApp {

    
    public static void main(String[] args) {
        HServer server = new HServer(ServerConfig.SERVER_HOST, ServerConfig.SERVER_NAME,
                ServerConfig.SERVER_PORT);
        server.start();
    }

}
