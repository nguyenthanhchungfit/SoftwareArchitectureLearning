
import java.util.Properties;
import mp3.utils.kafka.KafkaSender;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chungnt
 */
public class TestKafka {

    public static void main(String[] args) {
//        KafkaSender.send("song_lookup", "1" , "aaaa");
        // create instance for properties to access producer configs   
        /*
        Properties eprops = new Properties();

      String topicName = "song_lookup";
        //Assign localhost id
        eprops.put("bootstrap.servers", "localhost:9092");
      
      //Set acknowledgements for producer requests.      
      eprops.put("acks", "all");
      
      //If the request fails, the producer can automatically retry,
      eprops.put("retries", 0);

        //Specify buffer size in config
        eprops.put("batch.size", 16384);

        //Reduce the no of requests less than 0   
        eprops.put("linger.ms", 1);

        //The buffer.memory controls the total amount of memory available to the producer for buffering.   
        eprops.put("buffer.memory", 33554432);

        eprops.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        eprops.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        
        Producer<String, String> producer = new KafkaProducer<String, String>(eprops);


        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<String, String>(topicName,
                    Integer.toString(i), Integer.toString(i)));
        }
        System.out.println("Message sent successfully");
               producer.close();
        */
        KafkaSender.send("song_lookup", "20", "312");
    }
}
