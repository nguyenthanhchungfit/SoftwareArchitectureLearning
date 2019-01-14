/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.thrift.handlers;

import mp3.me.thrift.models.TAlbumModel;
import mp3.utils.thrift.models.TAlbum;
import mp3.utils.thrift.models.TAlbumResult;
import mp3.utils.thrift.services.TAlbumServices;
import org.apache.thrift.TException;

/**
 *
 * @author chungnt
 */
public class TAlbumHandler implements TAlbumServices.Iface{

    @Override
    public boolean putAlbum(TAlbum album) throws TException {
        return TAlbumModel.Instace.putAlbum(album);
    }

    @Override
    public TAlbumResult getAlbumById(String id) throws TException {
        return TAlbumModel.Instace.getAlbumById(id);
    }

    @Override
    public long getTotalNumberAlbums() throws TException {
        return TAlbumModel.Instace.getTotalNumberAlbums();
    }

//    @Override
//    public TAlbumResult getAlbumById(String id) throws TException {
//        return TAlbumModel.Instace.getAlbumById(id);
//    }
//
//    @Override
//    public long getTotalNumberAlbums() throws TException {
//        return TAlbumModel.Instace.getTotalNumberAlbums();
//    }
    
}
