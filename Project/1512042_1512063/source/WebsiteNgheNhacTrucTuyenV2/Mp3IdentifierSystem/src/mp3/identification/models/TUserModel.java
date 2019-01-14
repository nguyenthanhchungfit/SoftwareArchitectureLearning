/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.identification.models;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mp3.identification.database.UserMongoDB;
import mp3.identification.entities.Session;
import mp3.utils.impl.EncryptAndDecrypt;
import mp3.utils.thrift.models.TUser;
import mp3.utils.thrift.services.TUserServices;
import org.apache.thrift.TException;

/**
 *
 * @author chungnt
 */
public class TUserModel{

    public static final TUserModel Instance = new TUserModel();
    private EncryptAndDecrypt encryptAndDecrypt = new EncryptAndDecrypt();
    private UserMongoDB userDB = new UserMongoDB();
    
    private TUserModel(){
        
    }
    
    public String login(String username, String password) {
        String c_user = "";
        Session session = new Session();
        boolean flag = false;
        try {
            if(userDB.isValidAccount(username, password) == 1){
                session.setType(1);
                flag = true;
            }else if(userDB.isValidAccount(username, password) == 0){
                session.setType(0);
                flag = true;
            }
            if(flag){
                boolean isAdmin = (session.getType() == 0);
                c_user = encryptAndDecrypt.createUserID(username, isAdmin);
                session.setSessionID(c_user);
                session.setUsername(username);
                //dataCacher.insertNewSession(session);
            }
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(TUserModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TUserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c_user;
    }

    public boolean signup(TUser user){
        try {
            return userDB.insertNewAdmin(user);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(TUserModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TUserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public long getTotalNumberUsers(){
        return userDB.getTotalDocumentInDB();
    }

    public void logout(String userId){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isAdminSession(String string){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
