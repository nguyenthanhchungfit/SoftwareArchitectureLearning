/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.monitor.servers;

import java.util.logging.Level;
import java.util.logging.Logger;
import mp3.monitor.contract.ServerConfig;
import mp3.monitor.handlers.LoggerHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 *
 * @author chungnt
 */
public class WebsocketServer {

    private String host;
    private String name;
    private int port;
    
    public WebsocketServer(){
        host = "127.0.0.1";
        port = 8080;
        name = "";
    }

    public WebsocketServer(String host, String name, int port) {
        this.host = host;
        this.name = name;
        this.port = port;
    }

    public void start(){
        Runnable runner = new Runnable() {
            @Override
            public void run() {
                setupAndStart();
            }
        };
        new Thread(runner).start();
    }
    
    private boolean setupAndStart() {
        Server server = new Server(ServerConfig.SERVER_PORT);
        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(LoggerHandler.class, "/");
        server.setHandler(handler);

        try {
            server.start();
        } catch (Exception ex) {
            Logger.getLogger(WebsocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            server.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(WebsocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
