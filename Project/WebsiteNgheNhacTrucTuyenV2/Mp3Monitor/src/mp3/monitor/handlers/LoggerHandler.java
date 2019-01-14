/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.monitor.handlers;

import javax.servlet.annotation.WebServlet;
import mp3.monitor.models.LoggerSocketModel;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 *
 * @author chungnt
 */

@WebServlet(urlPatterns = "/getlogs")
public class LoggerHandler extends WebSocketServlet {
    public LoggerHandler() {
        System.out.println("Logs Servlet constructor");
    }
 
    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.register(LoggerSocketModel.class);
        factory.getPolicy().setIdleTimeout(1000000);
    }
}
