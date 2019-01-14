/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.identification.servers;

import java.util.logging.Level;
import java.util.logging.Logger;
import mp3.identification.handlers.TUserHandler;
import mp3.utils.thrift.services.TUserServices;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;

/**
 *
 * @author chungnt
 */
public class TServer {
    private String host;
    private String name;
    private int port;
    
    TMultiplexedProcessor _processors;
    private static final Logger _LOGGER = Logger.getLogger(TServer.class.getName());
    
    public TServer(String host, String name, int port) {
        this.host = host;
        this.name = name;
        this.port = port;
    }
    
    private void setup(){
        _processors = new TMultiplexedProcessor();
        
        _processors.registerProcessor("userServices", 
                new TUserServices.Processor(new TUserHandler()));
    }
    
    public void start(){
        setup();
        Runnable runner = new Runnable() {
            @Override
            public void run() {
                runServer();
            }
        };
        new Thread(runner).start();
    }
    
    private void runServer(){
        try {
            TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(port);
            TNonblockingServer server = new TNonblockingServer(new TNonblockingServer.Args(serverTransport).processor(_processors));   
            _LOGGER.info(String.format("%s started at host: %s, port: %d", name, host, port));
            server.serve(); 
        } catch (TTransportException ex) {
            Logger.getLogger(TNonblockingServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
