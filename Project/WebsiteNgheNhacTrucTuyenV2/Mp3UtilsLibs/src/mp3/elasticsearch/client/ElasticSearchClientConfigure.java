/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.elasticsearch.client;

/**
 *
 * @author chungnt
 */
public class ElasticSearchClientConfigure {
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 9200;
    public static final String PROTOCOL = "http";
    
    public static final String INDEX_NAME = "appmp3";
    public static final String TYPE_SONG_NAME = "songs";
    
    public static final long MAX_RESULT = 20;
}
