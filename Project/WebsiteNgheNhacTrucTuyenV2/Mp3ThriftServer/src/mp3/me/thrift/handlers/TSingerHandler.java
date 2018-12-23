/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.thrift.handlers;

import java.util.List;
import mp3.me.thrift.models.TSingerModel;
import mp3.utils.thrift.models.TSinger;
import mp3.utils.thrift.models.TSingerResult;
import mp3.utils.thrift.services.TSingerServices;
import org.apache.thrift.TException;

/**
 *
 * @author chungnt
 */
public class TSingerHandler implements TSingerServices.Iface{

    @Override
    public boolean putSinger(TSinger singer) throws TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TSinger> getSingersByName(String name) throws TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TSingerResult getSingerById(String id) throws TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getTotalNumberSingers() throws TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
//    @Override
//    public List<TSinger> getSingersByName(String name) throws TException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public TSingerResult getSingerById(String id) throws TException {
//        return TSingerModel.Instance.getSingerById(id);
//    }
//
//    @Override
//    public long getTotalNumberSingers() throws TException {
//        return TSingerModel.Instance.getTotalNumberSingers();
//    }
    
}
