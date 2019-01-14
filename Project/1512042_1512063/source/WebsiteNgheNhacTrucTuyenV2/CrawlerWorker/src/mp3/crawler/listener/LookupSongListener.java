/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.crawler.listener;

import java.net.URL;
import java.util.Arrays;
import java.util.Properties;
import mp3.crawler.executor.MyExecutor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 *
 * @author chungnt
 */
public class LookupSongListener {

    private static Properties prop;
    private static String topicName;
    private static boolean isRunning = false;
    private static final MyExecutor excutor = new MyExecutor();
    
    static {
        prop = LookupSongListenerProperties.getConsumerProperties();
    }

    public static void main(String[] args) {
        startListener();
    }

    private static void initConsumerKafka(Properties aprop, String aTopicName) {
        prop = aprop;
        topicName = aTopicName;
    }

    private static void startListener() {
        
        Runnable runner = new Runnable() {
            @Override
            public void run() {
                
                
                initConsumerKafka(LookupSongListenerProperties.getConsumerProperties(), "song_lookup");

                KafkaConsumer<String, String> consumer = new KafkaConsumer<>(prop);

                consumer.subscribe(Arrays.asList(topicName));

                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(100);
                    for (ConsumerRecord<String, String> record : records) {
                        String key = record.key();
                        String value = record.value();
                        System.out.printf("offset = %d, key = %s, value = %s\n",
                                record.offset(), key, value);

                        // crawler by url: key and pathFileStore: value
                        excutor.crawlSong(value);
                        //crawler.crawlAndSaveFile(new URL(key), value);
                    }
                }
            }
        };
        new Thread(runner).start();
    }
}
