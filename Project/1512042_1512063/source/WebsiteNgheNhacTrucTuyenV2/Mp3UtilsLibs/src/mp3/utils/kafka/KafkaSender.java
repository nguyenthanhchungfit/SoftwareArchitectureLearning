/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.utils.kafka;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 *
 * @author chungnt
 */
public class KafkaSender {

    public static int count = 0;
    private static Properties props;
    private static Producer<String, String> producer;

    static {
        props = KafkaSenderProperties.getProducerProperties();
        //Properties eprops = new Properties();        
        producer = new KafkaProducer<>(props);
    }

    public static void initProducerKafka(Properties aprop) {
        props = aprop;
        producer = new KafkaProducer<>(props);
    }

    public static void send(String topicName, String key, String value) {
//        count++;
//        ProducerRecord<String, String> pr = new ProducerRecord<>(topicName,
//            key, value);
//        
//        if(producer!= null){
//            System.out.println("done");
//            producer.send(pr);
//        }

        

        //Producer<String, String> producer = new KafkaProducer<String, String>(eprops);
        //producer = new KafkaProducer<>(eprops);
        
        producer.send(new ProducerRecord<String, String>(topicName,
                    key, value));
        //producer.close();
    }

    public static void close() {
        if (producer != null) {
            producer.close();
        }
    }
}
