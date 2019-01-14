/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.server;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.apache.log4j.Logger;
import mp3.me.handlers.AdminHandler;
import mp3.me.handlers.HomeHandler;
import mp3.me.handlers.LoginHandler;
import mp3.me.handlers.LogoutHandler;
import mp3.me.handlers.LyricHandler;
import mp3.me.handlers.SearchHandler;
import mp3.me.handlers.SingerHandler;
import mp3.me.handlers.SongHandler;
import mp3.me.handlers.StatisticHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

/**
 *
 * @author chungnt
 */
public class HServer {
    
    private static final Logger LOGGER = Logger.getLogger(HServer.class);
    
    private String host;
    private String name;
    private int port;

    public HServer(String host, String name, int port) {
        this.host = host;
        this.name = name;
        this.port = port;
    }
    
    public void start(){
        Runnable runner = new Runnable() {
            @Override
            public void run() {
                setup();
            }
        };
        new Thread(runner).start();
    }
    
    private void setup(){
        try {
            Server server = new Server();
            ServerConnector http = new ServerConnector(server);
            http.setHost(host);
            http.setPort(port);
            http.setIdleTimeout(30000);
            
            HandlerList handlers = new HandlerList();
            ServletContextHandler contextResource = new ServletContextHandler();
            contextResource.setContextPath("/static");
            
            ServletContextHandler contextContent = new ServletContextHandler();
            contextContent.setContextPath("/");
            
            
            URL url = HServer.class.getResource("/public");
            if(url == null)
                throw new FileNotFoundException("Unable to find required /resources");
            
            URI baseURI = url.toURI().resolve("./"); // resolve to directory itself.
            System.out.println("Base Resource URI is " + baseURI);
            
            try {
                contextResource.setBaseResource(Resource.newResource(baseURI));
            } catch (MalformedURLException ex) {
                LOGGER.error(ex.getMessage());
            }
            
            // Add something to serve the static files
            // It's named "default" to conform to servlet spec
            
            ServletHolder myHome = new ServletHolder(new HomeHandler());
            contextContent.addServlet(myHome, "/");
            
            ServletHolder searchServlet = new ServletHolder(new SearchHandler());
            contextContent.addServlet(searchServlet, "/search");
            
            ServletHolder songServlet = new ServletHolder(new SongHandler());
            contextContent.addServlet(songServlet, "/song");
            
            ServletHolder singerServlet = new ServletHolder(new SingerHandler());
            contextContent.addServlet(singerServlet, "/singer");
            
            ServletHolder lyricsServlet = new ServletHolder(new LyricHandler());
            
            contextContent.addServlet(lyricsServlet, "/lyric");
            
            ServletHolder loginServlet = new ServletHolder(new LoginHandler());
            contextContent.addServlet(loginServlet, "/login");
            
            ServletHolder logoutServlet = new ServletHolder(new LogoutHandler());
            contextContent.addServlet(logoutServlet, "/logout");        
            
            ServletHolder adminServlet = new ServletHolder(new AdminHandler());
            contextContent.addServlet(adminServlet, "/admin");
            
            ServletHolder statisticServlet = new ServletHolder(new StatisticHandler());
            contextContent.addServlet(statisticServlet, "/statistic");
            
            ServletHolder staticHolder = new ServletHolder(new DefaultServlet());
            contextResource.addServlet(staticHolder, "/");
            
            handlers.addHandler(contextResource);
            handlers.addHandler(contextContent);
            
            server.setHandler(handlers);
            // Set the connector
            server.addConnector(http);
            try {
                LOGGER.info(String.format("%S started at host: %s, port: %d", this.name, this.host, this.port));
                server.start();
                server.join();
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        } catch (FileNotFoundException ex) {
            LOGGER.error(ex.getMessage());
        } catch (URISyntaxException ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
