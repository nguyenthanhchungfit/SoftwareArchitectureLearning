/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.thrift.models;

import mp3.me.databases.IAlbumDB;
import mp3.me.databases.mongodb.MongoDBAlbumModel;
import mp3.utils.thrift.models.TAlbum;
import mp3.utils.thrift.models.TAlbumResult;

/**
 *
 * @author chungnt
 */
public class TAlbumModel {
    
    public static final TAlbumModel Instace = new TAlbumModel();
    
    private static final  IAlbumDB albumDbObj = new MongoDBAlbumModel();
    
    private TAlbumModel(){
    }
    
    public boolean putAlbum(TAlbum album){
        return albumDbObj.insertAlbum(album); 
    }
    
    public TAlbumResult getAlbumById(String id){
        return albumDbObj.getAlbumResult(id);
    }
    
    public long getTotalNumberAlbums(){
        return albumDbObj.getTotalDocumentInDB();
    }
}
