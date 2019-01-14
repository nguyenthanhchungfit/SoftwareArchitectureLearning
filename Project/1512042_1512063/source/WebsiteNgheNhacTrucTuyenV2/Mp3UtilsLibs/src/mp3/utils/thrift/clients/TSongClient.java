/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.utils.thrift.clients;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mp3.utils.thrift.models.TSong;
import mp3.utils.thrift.models.TSongResult;
import mp3.utils.thrift.services.TSongServices;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 *
 * @author chungnt
 */
public class TSongClient {

    private String name;
    private TTransport _transport;
    private TProtocol _protocol;
    private TMultiplexedProtocol _mulProtocol;
    private TSongServices.Client client;
    private static final String T_REMOTE_SERVER_HOST = "127.0.0.1";
    private static final int T_REMOTE_SERVER_PORT = 8001;
    private static final String T_REMOTE_SERVICE_NAME = "songServices";

    public TSongClient(String name) {
        this.name = name;
        _init();
    }

    private void _init() {
        _transport = new TFastFramedTransport(new TSocket(T_REMOTE_SERVER_HOST, T_REMOTE_SERVER_PORT));
        _protocol = new TBinaryProtocol(_transport);
        _mulProtocol = new TMultiplexedProtocol(_protocol, T_REMOTE_SERVICE_NAME);
        client = new TSongServices.Client(_mulProtocol);
    }

    public boolean putSong(TSong song) {
        try {
            _transport.open();
            return client.putSong(song);
        } catch (TException ex) {
            Logger.getLogger(TAlbumClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            _transport.close();
        }
        return false;
    }
    
    public List<TSong> getListSongSearchByName(String name){
        try {
            _transport.open();
            return client.getListSongSearchByName(name);
        } catch (TException ex) {
            Logger.getLogger(TAlbumClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            _transport.close();
        }
        return new ArrayList<>();
    }
    
    public TSong getSongById(String id){
        try {
            _transport.open();
            TSongResult tResut = client.getSongById(id);
            if(tResut.error == 0){
                return tResut.song;
            }
        } catch (TException ex) {
            Logger.getLogger(TAlbumClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            _transport.close();
        }
        return null;
    }
    
    public long getTotalNumberSongs(){
        try {
            _transport.open();
            return client.getTotalNumberSongs();
        } catch (TException ex) {
            Logger.getLogger(TAlbumClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            _transport.close();
        }
        return 0;
    }
}
