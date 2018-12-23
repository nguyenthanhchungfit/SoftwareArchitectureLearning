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
import mp3.utils.thrift.models.TDataLyric;
import mp3.utils.thrift.models.TLyric;
import mp3.utils.thrift.services.TLyricServices;
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
public class TLyricClient {

    private String name;
    private TTransport _transport;
    private TProtocol _protocol;
    private TMultiplexedProtocol _mulProtocol;
    private TLyricServices.Client client;
    private static final String T_REMOTE_SERVER_HOST = "127.0.0.1";
    private static final int T_REMOTE_SERVER_PORT = 8001;
    private static final String T_REMOTE_SERVICE_NAME = "lyricServices";

    public TLyricClient(String name) {
        this.name = name;
        _init();
    }

    private void _init() {
        _transport = new TFastFramedTransport(new TSocket(T_REMOTE_SERVER_HOST, T_REMOTE_SERVER_PORT));
        _protocol = new TBinaryProtocol(_transport);
        _mulProtocol = new TMultiplexedProtocol(_protocol, T_REMOTE_SERVICE_NAME);
        client = new TLyricServices.Client(_mulProtocol);
    }
    
    public boolean putLyric(TLyric lyric){
        try {
            _transport.open();
            return client.putLyric(lyric);
        } catch (TException ex) {
            Logger.getLogger(TAlbumClient.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            _transport.close();
        }
        return false;
    }
    
    public List<TDataLyric> getDataLyricsById(String id){
        try {
            _transport.open();
            return client.getDataLyricsById(id);
        } catch (TException ex) {
            Logger.getLogger(TAlbumClient.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            _transport.close();
        }
        return new ArrayList<>();
    }
    
    public long getTotalNumberLyrics(){
        try {
            _transport.open();
            return client.getTotalNumberLyrics();
        } catch (TException ex) {
            Logger.getLogger(TAlbumClient.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            _transport.close();
        }
        return 0;
    }
}
