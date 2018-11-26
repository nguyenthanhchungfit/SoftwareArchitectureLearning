/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_data;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import models.Kind;
import models.Referencer;
import org.bson.Document;

/**
 *
 * @author cpu11165-local
 */
public class DBKindModel {
    private static MongoClient mongo = null;
    private static MongoCredential credential = null;
    private static MongoDatabase mongo_db = null;
    private static MongoCollection<Document> collectionKinds = null;
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_SONGS= "songs";
    private static final String FIELD_AMOUNT_SONG = "amount_song";

    
    
    static{
        mongo = new MongoClient(DBDataContracts.HOST, DBDataContracts.PORT);
        credential = MongoCredential.createCredential(DBDataContracts.USERNAME
                , DBDataContracts.DATABASE_NAME, DBDataContracts.PASSWORD.toCharArray());
        mongo_db = mongo.getDatabase(DBDataContracts.DATABASE_NAME);
        collectionKinds = mongo_db.getCollection(DBDataContracts.COLLECTION_KINDS);
    }
    
    public static void InsertKind(Kind kind){
        
        ArrayList<Document> song_docs = DBDataContracts.getReferencers((ArrayList<Referencer>) kind.songs);
        
        Document doc = new Document(FIELD_ID, kind.id)
                            .append(FIELD_NAME, kind.name)
                            .append(FIELD_SONGS, song_docs)
                            .append(FIELD_AMOUNT_SONG, kind.songs);
        
        collectionKinds.insertOne(doc);
    }
}
