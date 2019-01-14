/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.identification.app;

import mp3.identification.contracts.ServerConfig;
import mp3.identification.servers.TServer;

/**
 *
 * @author chungnt
 */
public class MainApp {
    public static void main(String[] args) {
        TServer server = new TServer(ServerConfig.SERVER_HOST, ServerConfig.SERVER_NAME
                , ServerConfig.SERVER_PORT);
        server.start();
    }
}
