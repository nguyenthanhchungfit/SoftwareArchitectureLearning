/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.contracts;

import java.util.ArrayList;
import mp3.utils.thrift.models.TDataLyric;
import mp3.utils.thrift.models.TReferencer;
import org.bson.Document;

/**
 *
 * @author chungnt
 */
public class DBMongoContract {
    public static final String HOST = "localhost";
    public static final int PORT = 27017;
    public static final String USERNAME = "thanhchung";
    public static final String PASSWORD = "123";
    public static final String DATABASE_NAME = "app_mp3";
    public static final String COLLECTION_SONGS = "songs";
    public static final String COLLECTION_SINGERS = "singers";
    public static final String COLLECTION_VIDEOS = "videos";
    public static final String COLLECTION_ALBUMS = "albums";
    public static final String COLLECTION_COMMENTS = "comments";
    public static final String COLLECTION_USERS = "users";
    public static final String COLLECTION_KINDS = "kinds";
    public static final String COLLECTION_LYRICS = "lyrics";
    
    
    public static ArrayList<Document> getReferencers(ArrayList<TReferencer> refs){
        ArrayList<Document> docs = new ArrayList<>();
        for(TReferencer ref : refs){
            Document doc = getReferencer(ref);
            docs.add(doc);
        }
        return docs;
    }
    
    public static ArrayList<Document> getReferencersLyric(ArrayList<TDataLyric> refs){
        ArrayList<Document> docs = new ArrayList<>();
        for(TDataLyric ref : refs){
            Document doc = getReferencerLyric(ref);
            docs.add(doc);
        }
        return docs;
    }
    
    public static Document getReferencer(TReferencer ref){
        Document doc = new Document("id", ref.id)
                               .append("name", ref.name);
        return doc;
    }
    
    public static Document getReferencerLyric(TDataLyric ref){
        Document doc = new Document("contributor", ref.contributor)
                               .append("content", ref.content);
        return doc;
    }
}
