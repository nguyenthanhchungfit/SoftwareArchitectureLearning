/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.monitor.listener;

import java.net.URI;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import mp3.monitor.client.LogClientProcessor;
import mp3.utils.kafka.KafkaSenderProperties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

/**
 *
 * @author chungnt
 */
public class LogListener {
    private static Properties prop;
    private static String topicName;
    private static boolean isRunning = false;
    
    public static void main(String[] args) {
        try {
            System.out.println("*** LogsConsumer is running");
            String dest = "ws://0.0.0.0:8003/getlogs";
            WebSocketClient client = new WebSocketClient();
            LogClientProcessor socket = new LogClientProcessor();
            client.start();
            URI echoUri = new URI(dest);
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            System.out.println(client.connect(socket, echoUri, request));
            
            socket.getLatch().await();
            
            initConsumerKafka(LogKafkaConsumerProperties.getConsumerProperties(), KafkaSenderProperties.TOPIC_LOGGER_NOTIFY);
            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(prop);
            consumer.subscribe(Arrays.asList(topicName));
            
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    String value = record.value();
                    System.out.printf("offset = %d, value = %s\n",
                            record.offset(), value);
                    sendMessage(socket, value);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(LogListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void initConsumerKafka(Properties aprop, String aTopicName) {
        prop = aprop;
        topicName = aTopicName;
    }

    private static void sendMessage(LogClientProcessor socket, String message) {
        socket.sendMessage(message);
    }
}
