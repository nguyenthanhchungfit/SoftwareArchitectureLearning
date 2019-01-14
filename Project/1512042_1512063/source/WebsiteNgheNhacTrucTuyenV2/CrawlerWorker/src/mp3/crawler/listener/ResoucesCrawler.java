/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.crawler.listener;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import mp3.crawler.executor.MyExecutor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 *
 * @author chungnt
 */
public class ResoucesCrawler {

    private static Properties prop;
    private static String topicName;
    private static final MyExecutor excutor = new MyExecutor();
    
    public static void main(String[] args) {
        startListener();
    }

    private static void startListener() {
        
        Runnable runner = new Runnable() {
            @Override
            public void run() {
                initConsumerKafka(LookupSongListenerProperties.getConsumerProperties(), "download_resource");

                KafkaConsumer<String, String> consumer = new KafkaConsumer<>(prop);

                consumer.subscribe(Arrays.asList(topicName));

                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(100);
                    for (ConsumerRecord<String, String> record : records) {
                        String key = record.key();
                        String value = record.value();
                        System.out.printf("offset = %d, key = %s, value = %s\n",
                                record.offset(), key, value);

                        try {
                            // crawler by url: key and pathFileStore: value
                            excutor.crawlResouces(new URL(key), value);
                        } catch (MalformedURLException ex) {
                            Logger.getLogger(ResoucesCrawler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        };
        new Thread(runner).start();
    }
    
    private static void initConsumerKafka(Properties aprop, String aTopicName) {
        prop = aprop;
        topicName = aTopicName;
    }
}
