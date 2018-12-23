/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.databases.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import mp3.me.databases.IAlbumDB;
import mp3.me.contracts.DBMongoContract;
import mp3.utils.thrift.initiation.TModelInitiation;
import mp3.utils.thrift.models.TAlbum;
import mp3.utils.thrift.models.TAlbumResult;
import mp3.utils.thrift.models.TReferencer;
import org.bson.Document;

/**
 *
 * @author chungnt
 */
public class MongoDBAlbumModel implements IAlbumDB{
    private static MongoClient mongo = null;
    private static MongoDatabase mongo_db = null;
    private static MongoCollection<Document> collectionAlbums = null;
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_SONGS = "songs";
    private static final String FIELD_IMAGE = "image";

    static {
        mongo = new MongoClient(DBMongoContract.HOST, DBMongoContract.PORT);
        mongo_db = mongo.getDatabase(DBMongoContract.DATABASE_NAME);
        collectionAlbums = mongo_db.getCollection(DBMongoContract.COLLECTION_ALBUMS);
    }

    @Override
    public boolean isExistedAlbum(String id) {

        FindIterable<Document> k = collectionAlbums.find(new Document("id", id));
        if (k.iterator().hasNext()) {
            return true;
        }
        return false;
    }

    @Override
    public void insertAlbum(TAlbum album) {
        if (!isExistedAlbum(album.id)) {
            ArrayList<Document> song_docs = DBMongoContract.getReferencers((ArrayList<TReferencer>) album.songs);

            Document doc = new Document(FIELD_ID, album.id)
                    .append(FIELD_NAME, album.name)
                    .append(FIELD_IMAGE, album.image)
                    .append(FIELD_SONGS, song_docs);

            collectionAlbums.insertOne(doc);
        }
    }
    
    @Override
    public long getTotalDocumentInDB(){
        return collectionAlbums.count();
    }

    @Override
    public void removeAllRecords() {
        Document filter = new Document();
        collectionAlbums.deleteMany(filter);
    }

    @Override
    public void insertAlbums(List<TAlbum> albums) {
        for(TAlbum album : albums){
            insertAlbum(album);
        }
    }

    @Override
    public TAlbumResult getAlbumResult(String id) {
        TAlbumResult ar = new TAlbumResult();
        ar.error = -1;
        ar.album = null;
        
        TAlbum album = getAlbum(id);
        if(album != null){
            ar.error = 0;
            ar.album = album;
        }
        return ar;
    }

    @Override
    public TAlbum getAlbum(String id) {
        TAlbum album = null;
        FindIterable<Document> docs = collectionAlbums.find(new Document("id", id));
        Document doc = docs.first();
        if (null != doc) {
            album = new TAlbum();
            TModelInitiation.initTAlbum(album);      
            
            album.id = doc.getString(FIELD_ID);
            album.name = doc.getString(FIELD_NAME);
            album.image = doc.getString(FIELD_IMAGE);
            List<Document> song_docs = (List<Document>) doc.get("songs");
            
            for (Document docSong : song_docs) {
                String sID = docSong.getString("id");
                String sName = docSong.getString("name");
                album.songs.add(new TReferencer(sID, sName));
            }
        }
        return album;
    }

    @Override
    public List<TAlbum> getAllAlbums() {
        ArrayList<TAlbum> albums = new ArrayList<>();
        FindIterable<Document> docs = collectionAlbums.find();
        
        for(Document doc : docs){
            TAlbum album = new TAlbum();
            TModelInitiation.initTAlbum(album);      
            
            album.id = doc.getString(FIELD_ID);
            album.name = doc.getString(FIELD_NAME);
            album.image = doc.getString(FIELD_IMAGE);
            List<Document> song_docs = (List<Document>) doc.get("songs");
            
            for (Document docSong : song_docs) {
                String sID = docSong.getString("id");
                String sName = docSong.getString("name");
                album.songs.add(new TReferencer(sID, sName));
            }
            
            albums.add(album);
        }
        
        return albums;
    }
}
