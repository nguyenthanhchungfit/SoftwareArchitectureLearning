/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.crawler.executor;

import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import mp3.crawler.models.ICrawler;
import mp3.crawler.models.Mp3Crawler;

/**
 *
 * @author chungnt
 */
public class MyExecutor {
    
    private static ExecutorService executor;
    private static ICrawler crawler;
    static{
        executor = Executors.newFixedThreadPool(1);
        crawler = new Mp3Crawler();
    }
    
    public MyExecutor(){
    }
    
    public void crawlSong(String songName){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                crawler.crawlSongByName(songName);
            }
        });
    }
    
    public void crawlResouces(URL url, String pathFileSave){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                crawler.crawlAndSaveFile(url, pathFileSave);
            }
        });
    }
}
