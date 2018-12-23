/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.thrift.models;

import java.util.List;
import mp3.me.databases.ISongDB;
import mp3.me.databases.mongodb.MongoDBSongModel;
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
    
    public List<TSong> getSongsSearchAPIByName(String name){
        return null;
    }
    
    public TSongResult getSongById(String id){
        return songDbObj.getSongById(id);
    }
    
    public List<TSong> getSongsSearchESEByName(String name){
        return null;
    }
    
    public long getTotalNumberSongs(){
        return songDbObj.getTotalDocumentInDB();
    }
}
