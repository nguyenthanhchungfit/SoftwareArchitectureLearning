/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.monitor.models;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

/**
 *
 * @author chungnt
 */

@WebSocket
public class LoggerSocketModel {
    private static BlockingQueue<String> serverMessageQueue = new LinkedBlockingQueue<>();
    
    public LoggerSocketModel(){
        System.out.println("Created new Object!!!");
    }
    
    @OnWebSocketMessage
    public void onText(Session session, String message) throws IOException, InterruptedException {
        
        if (session.isOpen()) {
            String messageQueue = "";
            RemoteEndpoint remote = session.getRemote(); 
            // Xử lý mesage từ webrowser client
            if("admin_client_browser".equals(message)){
                while((messageQueue = serverMessageQueue.poll()) != null){
                    remote.sendString(messageQueue);
                }     
            }else{ // Xử lý message từ các server khác 
                System.out.println("Message received:" + message);
                serverMessageQueue.put(message);
            }
        }
    }
    
    
    @OnWebSocketConnect
    public void onConnect(Session session) throws IOException {
        System.out.println(session.getRemoteAddress().getHostString() + " connected!");
    }

    @OnWebSocketClose
    public void onClose(Session session, int status, String reason) {
        System.out.println(session.getRemoteAddress().getHostString() + " closed!");
    }
}
