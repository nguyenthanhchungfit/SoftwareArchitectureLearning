/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.databases;

import java.util.List;
import mp3.utils.thrift.models.TReferencer;
import mp3.utils.thrift.models.TSinger;
import mp3.utils.thrift.models.TSingerResult;

/**
 *
 * @author chungnt
 */
public interface ISingerDB {
    public TSingerResult getSinger(String idSinger);

    // Lấy thông tin ca sỹ 
    public TSinger getSingerInformation(String idSinger);

    // Kiểm tra sự tồn tại của Singer
    public boolean isExistedSinger(String id);

    // Chèn đối tượng singer vào DB
    public boolean insertSinger(TSinger singer);

    // Chèn nhiều đối tượng vào singer
    public boolean insertSingers(List<TSinger> list);

    public boolean isExistedSongInSinger(String idSinger, String idSong);

    public void insertNewAlbumToAlbumSinger(String idSinger, TReferencer newAlbum);
    
    public List<TSinger> getAllSingers();
    
    public long getTotalDocumentInDB();
    
    public void removeAllRecords();
}
