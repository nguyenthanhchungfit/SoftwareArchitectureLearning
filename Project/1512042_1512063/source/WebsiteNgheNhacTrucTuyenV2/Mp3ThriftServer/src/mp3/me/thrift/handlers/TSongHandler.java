/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.thrift.handlers;

import java.util.List;
import mp3.me.thrift.models.TSongModel;
import mp3.utils.thrift.models.TSong;
import mp3.utils.thrift.models.TSongResult;
import mp3.utils.thrift.services.TSongServices;
import org.apache.thrift.TException;

/**
 *
 * @author chungnt
 */
public class TSongHandler implements TSongServices.Iface{

    @Override
    public boolean putSong(TSong song) throws TException {
        return TSongModel.Instance.putSong(song);
    }

    @Override
    public List<TSong> getListSongSearchByName(String name) throws TException {
        return TSongModel.Instance.getSongsSearchByName(name);
    }

    @Override
    public TSongResult getSongById(String id) throws TException {
        return TSongModel.Instance.getSongById(id);
    }

    @Override
    public long getTotalNumberSongs() throws TException {
        return TSongModel.Instance.getTotalNumberSongs();
    }
}
