/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.crawler.models;

import java.net.URL;

/**
 *
 * @author chungnt
 */
public interface ICrawler {
    public void crawlSongByName(String songName);
    
    public void crawlAndSaveFile(URL url, String pathSave);
}
