/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.databases;

import java.util.List;
import mp3.utils.thrift.models.TAlbum;
import mp3.utils.thrift.models.TAlbumResult;

/**
 *
 * @author chungnt
 */
public interface IAlbumDB {
    public boolean isExistedAlbum(String id);

    public boolean insertAlbum(TAlbum album);
    
    public void insertAlbums(List<TAlbum> albums);
    
    public TAlbumResult getAlbumResult(String id);
    
    public TAlbum getAlbum(String id);
    
    public List<TAlbum> getAllAlbums();
    
    public long getTotalDocumentInDB();
    
    public void removeAllRecords();
}
