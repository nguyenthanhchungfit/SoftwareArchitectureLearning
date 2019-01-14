/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.identification.handlers;

import mp3.identification.models.TUserModel;
import mp3.utils.thrift.models.TUser;
import mp3.utils.thrift.services.TUserServices;
import org.apache.thrift.TException;

/**
 *
 * @author chungnt
 */
public class TUserHandler implements TUserServices.Iface{

    @Override
    public String login(String username, String password) throws TException {
        return TUserModel.Instance.login(username, password);
    }

    @Override
    public boolean signup(TUser tuser) throws TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getTotalNumberUsers() throws TException {
        return TUserModel.Instance.getTotalNumberUsers();
    }

    @Override
    public void logout(String userId) throws TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAdminSession(String session) throws TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
