/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.crawler.test;

import mp3.utils.kafka.KafkaSender;

/**
 *
 * @author chungnt
 */
public class TestKafka {
    public static void main(String[] args) {
        KafkaSender.send("song_lookup", "1" , "aaaa");
    }
}
