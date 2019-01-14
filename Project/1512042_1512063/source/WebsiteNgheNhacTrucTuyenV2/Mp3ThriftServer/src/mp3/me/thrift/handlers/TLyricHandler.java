/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.thrift.handlers;

import java.util.List;
import mp3.me.thrift.models.TLyricModel;
import mp3.utils.thrift.models.TDataLyric;
import mp3.utils.thrift.models.TLyric;
import mp3.utils.thrift.models.TLyricResult;
import mp3.utils.thrift.services.TLyricServices;
import org.apache.thrift.TException;

/**
 *
 * @author chungnt
 */
public class TLyricHandler implements TLyricServices.Iface {

    @Override
    public boolean putLyric(TLyric lyric) throws TException {
        return TLyricModel.Instance.putLyric(lyric);
    }

    @Override
    public TLyricResult getLyricByIdAndPage(String id, String page) throws TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TDataLyric> getDataLyricsById(String id) throws TException {
        return TLyricModel.Instance.getDataLyricsById(id);
    }

    @Override
    public long getTotalNumberLyrics() throws TException {
        return TLyricModel.Instance.getTotalNumberLyrics();
    }
    
}
