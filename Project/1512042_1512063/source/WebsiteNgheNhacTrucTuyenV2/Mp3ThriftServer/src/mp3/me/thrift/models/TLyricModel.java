/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.thrift.models;

import java.util.List;
import mp3.me.databases.ILyricDB;
import mp3.me.databases.mongodb.MongoDBLyricModel;
import mp3.utils.thrift.models.TDataLyric;
import mp3.utils.thrift.models.TLyric;
import mp3.utils.thrift.models.TLyricResult;

/**
 *
 * @author chungnt
 */
public class TLyricModel {
    public static final TLyricModel Instance = new TLyricModel();
    
    private static final ILyricDB lyricDbObj = new MongoDBLyricModel();
    
    public boolean putLyric(TLyric lyric){
        return lyricDbObj.insertLyric(lyric);
    }
    
    public TLyricResult getLyricByIdAndPage(String id, String page){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<TDataLyric> getDataLyricsById(String id){
        return lyricDbObj.getDataLyricsById(id);
    }
    
    public long getTotalNumberLyrics(){
        return lyricDbObj.getTotalDocumentInDB();
    }
}
