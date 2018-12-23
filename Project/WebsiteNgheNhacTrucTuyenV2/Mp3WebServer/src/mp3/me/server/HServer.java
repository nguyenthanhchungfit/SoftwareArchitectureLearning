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
import mp3.me.servlets.AdminServlet;
import mp3.me.servlets.HomeServlet;
import mp3.me.servlets.LoginServlet;
import mp3.me.servlets.LogoutServlet;
import mp3.me.servlets.LyricServlet;
import mp3.me.servlets.SearchServlet;
import mp3.me.servlets.SingerServlet;
import mp3.me.servlets.SongServlet;
import mp3.me.servlets.StatisticServlet;
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
            
            ServletHolder myHome = new ServletHolder(new HomeServlet());
            contextContent.addServlet(myHome, "/");
            
            ServletHolder searchServlet = new ServletHolder(new SearchServlet());
            contextContent.addServlet(searchServlet, "/search");
            
            ServletHolder songServlet = new ServletHolder(new SongServlet());
            contextContent.addServlet(songServlet, "/song");
            
            ServletHolder singerServlet = new ServletHolder(new SingerServlet());
            contextContent.addServlet(singerServlet, "/singer");
            
            ServletHolder lyricsServlet = new ServletHolder(new LyricServlet());
            contextContent.addServlet(lyricsServlet, "/lyric");
            
            ServletHolder loginServlet = new ServletHolder(new LoginServlet());
            contextContent.addServlet(loginServlet, "/login");
            
            ServletHolder logoutServlet = new ServletHolder(new LogoutServlet());
            contextContent.addServlet(logoutServlet, "/logout");        
            
            ServletHolder adminServlet = new ServletHolder(new AdminServlet());
            contextContent.addServlet(adminServlet, "/admin");
            
            ServletHolder statisticServlet = new ServletHolder(new StatisticServlet());
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
