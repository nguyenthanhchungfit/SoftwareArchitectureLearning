/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.elasticsearch.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mp3.utils.thrift.models.TReferencer;
import org.apache.http.entity.ContentType;
import mp3.utils.thrift.models.TSong;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author chungnt
 */
public class ElasticSearchSongClient {

    private String host;
    private int port;
    private String name;
    private String protocol;
    RestClient client;
    RestClientBuilder clientBuilder;

    public ElasticSearchSongClient(String name){
        this.host = ESClientConfig.HOST;
        this.port = ESClientConfig.PORT;
        this.name = name;
        _init();
    }
    
    public ElasticSearchSongClient(String host, int port) {
        this.host = host;
        this.port = port;
        _init();
    }

    private void _init() {
        protocol = ESClientConfig.PROTOCOL;
        clientBuilder = RestClient.builder(new HttpHost(host, port, protocol));
    }

    public void insertNewSong(String id, String name, String arrSingerStr) {
        try {
            client = clientBuilder.build();
            String jsonString = "{"
                    + "\"id\" : \"" + id + "\","
                    + "\"name\" : \"" + name + "\","
                    + "\"singers\": [" + arrSingerStr + "]}";

            System.out.println(jsonString);

            HttpEntity entity = new NStringEntity(jsonString, ContentType.APPLICATION_JSON);
            String requestString = "/" + ESClientConfig.INDEX_NAME
                    + "/" + ESClientConfig.TYPE_SONG_NAME + "/";

            Response response = client.performRequest("POST", requestString, Collections.emptyMap(), entity);
            System.out.println(response.getRequestLine());
            System.out.println(EntityUtils.toString(response.getEntity()));
            System.out.println("\n*************************\n");
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(ElasticSearchSongClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<TSong> getListSongSearchByName(String songName) {
        try {

            List<TSong> songs = new ArrayList<>();

            if (songName.equals("")) {
                return songs;
            }

            client = clientBuilder.build();

            String queryDSL = "{\n"
                    + "\"from\" : 0, \"size\" : 10,"
                    + "\"query\" : {\n"
                    + "\"match_phrase_prefix\" : {\n"
                    + "\"name\" : " + "\"" + songName + "\"\n"
                    + "}\n"
                    + "}\n"
                    + "}\n"
                    + "}";
            HttpEntity entity = new NStringEntity(queryDSL, ContentType.APPLICATION_JSON);
            String requestString = "/" + ESClientConfig.INDEX_NAME
                    + "/" + ESClientConfig.TYPE_SONG_NAME + "/_search";
            Response response = client.performRequest("GET", requestString, Collections.singletonMap("pretty", "true"), entity);

            System.out.println(response.getRequestLine());

            JSONParser jsonParser = new JSONParser();

            JSONObject jsonObj = (JSONObject) jsonParser.parse(EntityUtils.toString(response.getEntity()));
            JSONObject jsonHit = (JSONObject) jsonObj.get("hits");
            long total = (long) jsonHit.get("total");
            total = (total < ESClientConfig.MAX_RESULT)
                    ? total : ESClientConfig.MAX_RESULT;

            if (total > 0) {
                JSONArray hitsArr = (JSONArray) jsonHit.get("hits");
                Iterator it = hitsArr.iterator();
                int i = 0;
                while (it.hasNext() && i < total) {
                    JSONObject item = (JSONObject) it.next();
                    JSONObject source = (JSONObject) item.get("_source");
                    String id = (String) source.get("id");
                    String name = (String) source.get("name");
                    JSONArray jsonSingers = (JSONArray) source.get("singers");
                    Iterator itSinger = jsonSingers.iterator();

                    List<TReferencer> singers = new ArrayList<>();

                    while (itSinger.hasNext()) {
                        String nameS = (String) itSinger.next();
                        TReferencer refSinger = new TReferencer();
                        refSinger.name = nameS;
                        singers.add(refSinger);
                    }

                    TSong song = new TSong();
                    song.id = id;
                    song.name = name;
                    song.singers = singers;

                    i++;
                    songs.add(song);
                }
            }
            client.close();
            return songs;
        } catch (IOException ex) {
            Logger.getLogger(ElasticSearchSongClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ElasticSearchSongClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    public void deleteSong(String idSongIndex) {
        try {
            client = clientBuilder.build();
            String requestString = "/" + ESClientConfig.INDEX_NAME 
                    + "/" + ESClientConfig.TYPE_SONG_NAME + "/" + idSongIndex;
            Response response = client.performRequest("DELETE", requestString);
            System.out.println(response.getRequestLine());
            System.out.println(EntityUtils.toString(response.getEntity()));
            System.out.println("\n*************************\n");
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(ElasticSearchSongClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
