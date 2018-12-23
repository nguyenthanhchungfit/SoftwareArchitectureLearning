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
import mp3.utils.thrift.models.TSinger;
import mp3.utils.thrift.models.TSingerResult;
import mp3.utils.thrift.services.TAlbumServices;
import mp3.utils.thrift.services.TSingerServices;
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
public class TSingerClient {

    private String name;
    private TTransport _transport;
    private TProtocol _protocol;
    private TMultiplexedProtocol _mulProtocol;
    private TSingerServices.Client client;
    private static final String T_REMOTE_SERVER_HOST = "127.0.0.1";
    private static final int T_REMOTE_SERVER_PORT = 8001;
    private static final String T_REMOTE_SERVICE_NAME = "singerServices";

    public TSingerClient(String name) {
        this.name = name;
        _init();
    }

    private void _init() {
        _transport = new TFastFramedTransport(new TSocket(T_REMOTE_SERVER_HOST, T_REMOTE_SERVER_PORT));
        _protocol = new TBinaryProtocol(_transport);
        _mulProtocol = new TMultiplexedProtocol(_protocol, T_REMOTE_SERVICE_NAME);
        client = new TSingerServices.Client(_mulProtocol);
    }

    public boolean putSinger(TSinger singer) {
        try {
            _transport.open();
            return client.putSinger(singer);
        } catch (TException ex) {
            Logger.getLogger(TAlbumClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            _transport.close();
        }
        return false;
    }

    public List<TSinger> getListSingerByName(String name) {
        try {
            _transport.open();
            return client.getSingersByName(name);
        } catch (TException ex) {
            Logger.getLogger(TAlbumClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            _transport.close();
        }
        return new ArrayList<>();
    }

    public TSinger getSingerById(String id) {
        try {
            _transport.open();
            TSingerResult tResult = client.getSingerById(id);
            if (tResult.error == 0) {
                return tResult.singer;
            }
        } catch (TException ex) {
            Logger.getLogger(TAlbumClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            _transport.close();
        }
        return null;
    }

    public long getTotalNumberSingers() {
        try {
            _transport.open();
            return client.getTotalNumberSingers();
        } catch (TException ex) {
            Logger.getLogger(TAlbumClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            _transport.close();
        }
        return 0;
    }
}
