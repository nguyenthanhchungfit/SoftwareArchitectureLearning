/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.databases;

import java.util.List;
import mp3.utils.thrift.models.TSong;
import mp3.utils.thrift.models.TSongResult;

/**
 *
 * @author chungnt
 */
public interface ISongDB {
    public TSongResult getSongById(String id);

    public void getNameInsensitive(String name);

    public boolean isExistedSong(String id);

    public void isertSong(TSong song);

    public void insertSongs(List<TSong> songs);

    // Kiểm tra album đã tồn tại trong song chưa
    public boolean isExistedAlbumInSong(String idSong, String idAlbum);

    public List<TSong> getSongsSearchAPIByName(String name);

    public List<TSong> getAllSongs();

    public long getTotalDocumentInDB();
    
    public void removeAllRecords();
}
