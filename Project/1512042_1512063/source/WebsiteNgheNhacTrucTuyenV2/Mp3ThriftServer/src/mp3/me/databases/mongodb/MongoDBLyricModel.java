/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.databases.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import mp3.me.contracts.DBMongoContract;
import mp3.me.databases.ILyricDB;
import mp3.utils.thrift.initiation.TModelInitiation;
import mp3.utils.thrift.models.TDataLyric;
import mp3.utils.thrift.models.TLyric;
import mp3.utils.thrift.models.TLyricResult;
import org.bson.Document;

/**
 *
 * @author chungnt
 */
public class MongoDBLyricModel implements ILyricDB {

    private static MongoClient mongo = null;
    private static MongoDatabase mongo_db = null;
    private static MongoCollection<Document> collectionLyrics = null;
    private static final String FIELD_ID = "id";
    private static final String FIELD_DATAS = "datas";

    static {
        mongo = new MongoClient(DBMongoContract.HOST, DBMongoContract.PORT);
        mongo_db = mongo.getDatabase(DBMongoContract.DATABASE_NAME);
        collectionLyrics = mongo_db.getCollection(DBMongoContract.COLLECTION_LYRICS);
    }

    @Override
    public boolean isExistedLyric(String id) {
        FindIterable<Document> k = collectionLyrics.find(new Document("id", id));
        if (k.iterator().hasNext()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean insertLyric(TLyric lyric) {
        if (!isExistedLyric(lyric.id)) {
            ArrayList<Document> song_docs = DBMongoContract.getReferencersLyric((ArrayList<TDataLyric>) lyric.datas);

            Document doc = new Document(FIELD_ID, lyric.id)
                    .append(FIELD_DATAS, song_docs);

            collectionLyrics.insertOne(doc);
            return true;
        }
        return false;
    }

    @Override
    public void insertLyrics(List<TLyric> lyrics) {
        for (TLyric lyric : lyrics) {
            insertLyric(lyric);
        }
    }

    @Override
    public List<TDataLyric> getDataLyricsById(String id) {
        ArrayList<TDataLyric> dataLyrics = new ArrayList<>();

        TLyric lyric = getLyric(id);
        if (lyric != null) {
            dataLyrics = (ArrayList<TDataLyric>) lyric.datas;
        }

        return dataLyrics;
    }

    @Override
    public long getTotalDocumentInDB() {
        return collectionLyrics.count();
    }

    @Override
    public void removeAllRecords() {
        Document filter = new Document();
        collectionLyrics.deleteMany(filter);
    }

    @Override
    public TLyricResult getLyricResult(String id) {
        TLyricResult lr = new TLyricResult();
        lr.error = -1;
        lr.lyric = null;

        FindIterable<Document> docs = collectionLyrics.find(new Document(FIELD_ID, id));
        if (docs != null) {
            Document doc = docs.first();
            TLyric lyric = new TLyric();
            TModelInitiation.initTLyric(lyric);
            lyric.id = (String) doc.get(FIELD_ID);

            ArrayList<Document> data_docs = (ArrayList<Document>) doc.get(FIELD_DATAS);
            for (Document data_doc : data_docs) {
                String contributor = data_doc.getString("contributor");
                String content = data_doc.getString("content");
                lyric.datas.add(new TDataLyric(contributor, content));
            }

            lr.error = 0;
            lr.lyric = lyric;
        }
        return lr;
    }

    @Override
    public TLyric getLyric(String id) {
        TLyricResult lr = getLyricResult(id);
        return lr.lyric;
    }

    @Override
    public List<TLyric> getAllLyrics() {
        ArrayList<TLyric> lyrics = new ArrayList<>();

        FindIterable<Document> docs = collectionLyrics.find();

        if (docs != null) {
            for (Document doc : docs) {
                
                TLyric lyric = new TLyric();
                TModelInitiation.initTLyric(lyric);
                
                lyric.id = (String) doc.get(FIELD_ID);
                ArrayList<Document> data_docs = (ArrayList<Document>) doc.get(FIELD_DATAS);
                for (Document data_doc : data_docs) {
                    String contributor = data_doc.getString("contributor");
                    String content = data_doc.getString("content");
                    lyric.datas.add(new TDataLyric(contributor, content));
                }
                lyrics.add(lyric);
            }
        }
        return lyrics;
    }

}
