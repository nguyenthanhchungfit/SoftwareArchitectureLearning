/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import java.util.Properties;

/**
 *
 * @author chungnt
 */
public class LookupSongListener {
    private static Properties prop;
    private static String topicName;
    private static boolean isRunning = false;
    
    static {
        prop = LookupSongListenerProperties.getConsumerProperties();
    }
}
