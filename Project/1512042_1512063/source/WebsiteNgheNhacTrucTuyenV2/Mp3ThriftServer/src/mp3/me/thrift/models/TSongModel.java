/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.thrift.models;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mp3.me.databases.ISongDB;
import mp3.me.databases.mongodb.MongoDBSongModel;
import mp3.utils.kafka.KafkaSender;
import mp3.utils.kafka.KafkaSenderProperties;
import mp3.utils.thrift.models.TSong;
import mp3.utils.thrift.models.TSongResult;

/**
 *
 * @author chungnt
 */
public class TSongModel {
    public static final TSongModel Instance = new TSongModel();
    private static final ISongDB songDbObj = new MongoDBSongModel();
    
    private TSongModel(){
    }
    
    public boolean putSong(TSong song){
        return songDbObj.insertSong(song);
    }
    
    public List<TSong> getSongsSearchByName(String name){
        List<TSong> listSong = songDbObj.getSongsSearchByName(name);
        if(listSong.size() == 0){
            try {
                KafkaSender.send(KafkaSenderProperties.TOPIC_SONG_LOOKUP, null, name);
                Thread.sleep(2000);
                listSong = songDbObj.getSongsSearchByName(name);
            } catch (InterruptedException ex) {
                Logger.getLogger(TSongModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listSong;
    }
    
    public TSongResult getSongById(String id){
        return songDbObj.getSongById(id);
    }
    
    public long getTotalNumberSongs(){
        return songDbObj.getTotalDocumentInDB();
    }
}
