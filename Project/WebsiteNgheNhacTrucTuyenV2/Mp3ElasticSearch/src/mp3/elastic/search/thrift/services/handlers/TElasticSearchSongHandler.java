/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.elastic.search.thrift.services.handlers;

import java.util.List;
import mp3.utils.thrift.models.TElasticSong;
import mp3.utils.thrift.services.TElasticSearchServices;

/**
 *
 * @author chungnt
 */
public class TElasticSearchSongHandler implements TElasticSearchServices.Iface {

    @Override
    public List<TElasticSong> getListSongs(String string) throws org.apache.thrift.TException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
