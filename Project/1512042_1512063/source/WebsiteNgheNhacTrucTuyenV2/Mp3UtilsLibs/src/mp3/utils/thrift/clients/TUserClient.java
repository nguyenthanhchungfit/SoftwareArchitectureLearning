/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.utils.thrift.clients;

import java.util.logging.Level;
import java.util.logging.Logger;
import mp3.utils.thrift.models.TUser;
import mp3.utils.thrift.services.TSongServices;
import mp3.utils.thrift.services.TUserServices;
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
public class TUserClient {

    private String name;
    private TTransport _transport;
    private TProtocol _protocol;
    private TMultiplexedProtocol _mulProtocol;
    private TUserServices.Client client;
    private static final String T_REMOTE_SERVER_HOST = "127.0.0.1";
    private static final int T_REMOTE_SERVER_PORT = 8002;
    private static final String T_REMOTE_SERVICE_NAME = "userServices";

    public TUserClient(String name) {
        this.name = name;
        _init();
    }

    private void _init() {
        _transport = new TFastFramedTransport(new TSocket(T_REMOTE_SERVER_HOST, T_REMOTE_SERVER_PORT));
        _protocol = new TBinaryProtocol(_transport);
        _mulProtocol = new TMultiplexedProtocol(_protocol, T_REMOTE_SERVICE_NAME);
        client = new TUserServices.Client(_mulProtocol);
    }

    public boolean signup(TUser user) {
        try {
            _transport.open();
            return client.signup(user);
        } catch (TException ex) {
            Logger.getLogger(TAlbumClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            _transport.close();
        }
        return false;
    }

    public String login(String username, String password) {
        try {
            _transport.open();
            return client.login(username, password);
        } catch (TException ex) {
            Logger.getLogger(TAlbumClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            _transport.close();
        }
        return "";
    }
}
