/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_user;

import contracts.UserServerContract;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import servers.UserServer;
import thrift_services.UserServices;
import thrift_services.UserServicesImpl;

/**
 *
 * @author cpu11165-local
 */
public class UsersServices {
    
    private static final int port = UserServerContract.PORT;
    
    public static void main(String[] args) throws TTransportException {
//        TMultiplexedProcessor processors = new TMultiplexedProcessor();
//        
//        processors.registerProcessor("UserServices", new UserServices.Processor<>(
//                new UserServicesImpl()));
//        
//        
//        TServerTransport serverTransport = new TServerSocket(port);
//        TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processors));
//        server.serve();
        UserServer userServer = new UserServer();
        if(!userServer.setupAndStart()){
            System.err.println("Could not start thrift servers! Exit now.");
            System.exit(1);
        }else{
            System.out.println("@@@ User Server started!!!");
        }
    }
}
