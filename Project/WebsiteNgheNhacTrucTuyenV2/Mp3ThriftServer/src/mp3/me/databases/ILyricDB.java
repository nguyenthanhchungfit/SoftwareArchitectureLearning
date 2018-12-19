/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.databases;

import java.util.List;
import mp3.utils.thrift.models.TDataLyric;
import mp3.utils.thrift.models.TLyric;
import mp3.utils.thrift.models.TLyricResult;


/**
 *
 * @author chungnt
 */
public interface ILyricDB {
    public boolean isExistedLyric(String id);

    public void InsertLyric(TLyric lyric);
    
    public void InsertLyrics(List<TLyric> lyrics);

    public List<TDataLyric> getDataLyricsById(String id);
    
    public long getTotalDocumentInDB();
    
    public void removeAllRecords();
    
    public TLyricResult getLyricResult(String id);
    
    public TLyric getLyric(String id);
    
    public List<TLyric> getAllLyrics();
}
