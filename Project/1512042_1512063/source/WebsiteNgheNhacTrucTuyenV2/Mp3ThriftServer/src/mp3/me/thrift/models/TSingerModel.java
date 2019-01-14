/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.thrift.models;

import java.util.List;
import mp3.me.databases.ISingerDB;
import mp3.me.databases.mongodb.MongoDBSingerModel;
import mp3.utils.thrift.models.TSinger;
import mp3.utils.thrift.models.TSingerResult;

/**
 *
 * @author chungnt
 */
public class TSingerModel {
    
    public static final TSingerModel Instance = new TSingerModel();
    private static final ISingerDB singerDbObj = new MongoDBSingerModel();
    
    private TSingerModel(){
    }
    
    public boolean putSinger(TSinger singer){
        return singerDbObj.insertSinger(singer);
    }
    
    public List<TSinger> getSingersByName(String name) {
         throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public TSingerResult getSingerById(String id){
        return singerDbObj.getSinger(id);
    }
    
    public long getTotalNumberSingers(){
        return singerDbObj.getTotalDocumentInDB();
    }
    
    public boolean putMultiSingers(List<TSinger> singers){
        return singerDbObj.insertSingers(singers);
    }
}
