/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.thrift.app;

import mp3.me.contracts.ServerConfig;
import mp3.me.thrift.server.TServer;

/**
 *
 * @author chungnt
 */
public class Mp3ThriftMainApp {
    public static void main(String[] args) {
        TServer server = new TServer(ServerConfig.SERVER_HOST, ServerConfig.SERVER_NAME
                , ServerConfig.SERVER_PORT);
        server.start();
    }
}
