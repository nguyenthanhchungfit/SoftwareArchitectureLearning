/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.elasticsearch.test;

import mp3.elasticsearch.client.ElasticSearchSongClient;

/**
 *
 * @author chungnt
 */
public class TestElasticSeach {
    
    private static final ElasticSearchSongClient eClient = 
            new ElasticSearchSongClient(TestElasticSeach.class.getName());
    
    public static void main(String[] args) {
        deleteSongIndex("5Q6NNmgB2WBQn-sRpInp");
        deleteSongIndex("97bPM2gBR-lIopCOEdpl");
        deleteSongIndex("3w5-NmgB2WBQn-sR-oms");
        deleteSongIndex("-LbQM2gBR-lIopCOXNr1");
        //deleteSongIndex("5w6NNmgB2WBQn-sRqIkc");
        
    }
    
    private static void deleteSongIndex(String id){
        eClient.deleteSong(id);
    }
}
