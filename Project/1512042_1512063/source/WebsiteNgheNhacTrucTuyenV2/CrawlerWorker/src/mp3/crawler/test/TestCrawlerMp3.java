/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.crawler.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import mp3.crawler.models.ICrawler;
import mp3.crawler.models.Mp3Crawler;

/**
 *
 * @author chungnt
 */
public class TestCrawlerMp3 {
    public static void main(String[] args) {
        
            ICrawler crawler = new Mp3Crawler();
            //String pathSave = "../Mp3WebServer/Resources/file.png";
            crawler.crawlSongByName("Em của ngày hôm qua");
            //crawler.crawlAndSaveFile(new URL("https://upload.wikimedia.org/wikipedia/commons/d/d6/Logo_Zalo.png"), pathSave);
        
    }
}
