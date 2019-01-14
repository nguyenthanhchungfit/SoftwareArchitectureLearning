
import java.util.List;
import mp3.utils.thrift.clients.TSongClient;
import mp3.utils.thrift.models.TSong;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chungnt
 */
public class TestTSongClient {
    public static void main(String[] args) {
        TSongClient tSongClient = new TSongClient(TestTSongClient.class.getName());
        List<TSong> listSong = tSongClient.getListSongSearchByName("Anh");
        for(TSong song : listSong){
            System.out.println(song);
        }
    }
}
