/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.utils.thrift.clients;

import java.util.logging.Level;
import java.util.logging.Logger;
import mp3.utils.thrift.models.TAlbum;
import mp3.utils.thrift.models.TAlbumResult;
import mp3.utils.thrift.services.TAlbumServices;
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
public class TAlbumClient {
    private String name;
    private TTransport _transport;
    private TProtocol _protocol;
    private TMultiplexedProtocol _mulProtocol;
    private TAlbumServices.Client client;
    private static final String T_REMOTE_SERVER_HOST = "127.0.0.1";
    private static final int T_REMOTE_SERVER_PORT = 8001;
    private static final String T_REMOTE_SERVICE_NAME = "albumServices";

    public TAlbumClient(String name) {
        this.name = name;
        _init();
    }
    
    private void _init(){
        _transport = new TFastFramedTransport(new TSocket(T_REMOTE_SERVER_HOST, T_REMOTE_SERVER_PORT));
        _protocol = new TBinaryProtocol(_transport);
        _mulProtocol = new TMultiplexedProtocol(_protocol, T_REMOTE_SERVICE_NAME);
        client = new TAlbumServices.Client(_mulProtocol);
    }
    
    public boolean putAlbum(TAlbum album){
        try {
            _transport.open();
            return client.putAlbum(album);
        } catch (TException ex) {
            Logger.getLogger(TAlbumClient.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            _transport.close();
        }
        return false;
    }
    
    public TAlbum getAlbumById(String id){
        try {
            _transport.open();
            TAlbumResult tResult = client.getAlbumById(id);
            if(tResult.error == 0){
                return tResult.album;
            }
        } catch (TException ex) {
            Logger.getLogger(TAlbumClient.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            _transport.close();
        }
        return null;
    }
    
    public long getTotalNumberAlbums(){
        try {
            _transport.open();
            return client.getTotalNumberAlbums();
        } catch (TException ex) {
            Logger.getLogger(TAlbumClient.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            _transport.close();
        }
        return 0;
    }
}
