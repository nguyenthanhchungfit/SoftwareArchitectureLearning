/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.crawler.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import mp3.crawler.models.ICrawler;
import mp3.crawler.models.Mp3Crawler;

/**
 *
 * @author chungnt
 */
public class MyExecutor {
    
    public static final MyExecutor Instance = new MyExecutor();
    private static ExecutorService executor;
    private static ICrawler crawler;
    static{
        executor = Executors.newFixedThreadPool(2);
        crawler = new Mp3Crawler();
    }
    
    private MyExecutor(){
    }
    
    private void addNewTask(String songName){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                crawler.crawlSongByName(songName);
            }
        });
    }
}
