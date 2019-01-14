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
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import mp3.me.contracts.DBMongoContract;
import mp3.me.databases.ISongDB;
import mp3.utils.thrift.initiation.TModelInitiation;
import mp3.utils.thrift.models.TReferencer;
import mp3.utils.thrift.models.TSong;
import mp3.utils.thrift.models.TSongResult;
import org.bson.Document;

/**
 *
 * @author chungnt
 */
public class MongoDBSongModel implements ISongDB {

    private static MongoClient mongo = null;
    private static MongoDatabase mongo_db = null;
    private static MongoCollection<Document> collectionSongs = null;

    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_ALBUM = "album";
    private static final String FIELD_LYRIC = "lyric";
    private static final String FIELD_COMPOSERS = "composers";
    private static final String FIELD_KARA = "kara";
    private static final String FIELD_DURATION = "duration";
    private static final String FIELD_KINDS = "kinds";
    private static final String FIELD_SINGERS = "singers";
    private static final String FIELD_VIEWS = "views";
    private static final String FIELD_COMMENT = "comment";
    private static final String FIELD_IMAGE = "image";
    
    
    static {
        mongo = new MongoClient(DBMongoContract.HOST, DBMongoContract.PORT);
        mongo_db = mongo.getDatabase(DBMongoContract.DATABASE_NAME);
        collectionSongs = mongo_db.getCollection(DBMongoContract.COLLECTION_SONGS);
    }
    
    @Override
    public TSongResult getSongById(String id) {
        TSongResult sr = new TSongResult();
        
        Document regQuery = new Document();
        regQuery.append("$regex", "^(?)" + Pattern.quote(id));
        regQuery.append("$options", "i");
        
        Document findQuery = new Document();
        findQuery.append("id", regQuery);
        
        FindIterable<Document> docs = collectionSongs.find(findQuery);
        
        Document doc = docs.first();
        
        if (doc != null) {
            sr.error = 0;
            sr.song = new TSong();
            sr.song.id = doc.getString(FIELD_ID);
            sr.song.name = doc.getString(FIELD_NAME);
            sr.song.lyrics = (String) doc.get(FIELD_LYRIC);
            sr.song.composers = (List<String>) doc.get(FIELD_COMPOSERS);
            sr.song.views = doc.getLong(FIELD_VIEWS);
            sr.song.image = doc.getString(FIELD_IMAGE);
            sr.song.kara = doc.getString(FIELD_KARA);
            sr.song.duration = doc.getInteger(FIELD_DURATION).shortValue();
            sr.song.comment = doc.getString(FIELD_COMMENT);
            sr.song.singers = new ArrayList<>();
            sr.song.kinds = new ArrayList<>();
            List<Document> kind_docs = (List<Document>) doc.get(FIELD_KINDS);
            List<Document> singer_docs = (List<Document>) doc.get(FIELD_SINGERS);
            Document album_doc = (Document) doc.get(FIELD_ALBUM);
            
            sr.song.album = new TReferencer(album_doc.getString("id"), album_doc.getString("name"));
            
            for (Document docSinger : singer_docs) {
                String sId = docSinger.getString("id");
                String sName = docSinger.getString("name");
                TReferencer singer = new TReferencer(sId, sName);
                sr.song.singers.add(singer);
            }
            
            for (Document docKind : kind_docs) {
                String sId = docKind.getString("id");
                String sName = docKind.getString("name");
                TReferencer kind = new TReferencer(sId, sName);
                sr.song.kinds.add(kind);
            }
        } else {
            sr.error = -1;
            sr.song = null;
        }        
        return sr;
    }

    @Override
    public void getNameInsensitive(String name) {
        Document regQuery = new Document();
        regQuery.append("$regex", "^(?)" + Pattern.quote(name));
        regQuery.append("$options", "i");
        
        Document findQuery = new Document();
        findQuery.append("name", regQuery);
        FindIterable<Document> iterable = collectionSongs.find(findQuery);
        Iterator<Document> it = iterable.iterator();
        while (it.hasNext()) {
            Document doc = it.next();
            System.out.println(doc.getString("id"));
        }
    }

    @Override
    public boolean isExistedSong(String id) {
        FindIterable<Document> k = collectionSongs.find(new Document("id", id));
        if (k.iterator().hasNext()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean insertSong(TSong song) {
        if (!isExistedSong(song.id)) {
            ArrayList<Document> singer_docs = DBMongoContract.getReferencers((ArrayList<TReferencer>) song.singers);
            ArrayList<Document> kind_docs = DBMongoContract.getReferencers((ArrayList<TReferencer>) song.kinds);
            Document album_doc = DBMongoContract.getReferencer(song.album);
            
            Document document = new Document(FIELD_ID, song.id)
                    .append(FIELD_NAME, song.name)
                    .append(FIELD_ALBUM, album_doc)
                    .append(FIELD_LYRIC, song.lyrics)
                    .append(FIELD_COMPOSERS, song.composers)
                    .append(FIELD_KARA, song.kara)
                    .append(FIELD_DURATION, song.duration)
                    .append(FIELD_KINDS, kind_docs)
                    .append(FIELD_SINGERS, singer_docs)
                    .append(FIELD_VIEWS, song.views)
                    .append(FIELD_COMMENT, song.comment)
                    .append(FIELD_IMAGE, song.image);
            
            collectionSongs.insertOne(document);
            return true;
        }
        return false;
    }

    @Override
    public void insertSongs(List<TSong> songs) {
        for (TSong song : songs) {
            insertSong(song);
        }
    }

    @Override
    public boolean isExistedAlbumInSong(String idSong, String idAlbum) {
        FindIterable<Document> k = collectionSongs.find(new Document(FIELD_ID, idSong));
        if (k != null) {
            Document doc = k.first();
            String idAlbumFind = doc.getString(FIELD_ALBUM);
            if (idAlbum.equals(idAlbumFind)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<TSong> getSongsSearchByName(String name) {
        ArrayList<TSong> songs = new ArrayList<>();
        
        Document regQuery = new Document();
        regQuery.append("$regex", "(?)" + Pattern.quote(name));
        regQuery.append("$options", "i");
        
        Document findQuery = new Document();
        findQuery.append("name", regQuery);
        
        FindIterable<Document> docs = collectionSongs.find(findQuery);
        
        if (docs != null) {
            for (Document doc : docs) {
                TSong song = new TSong();
                song.id = doc.getString("id");
                song.name = doc.getString("name");
                List<Document> kind_docs = (List<Document>) doc.get("kinds");
                List<Document> singer_docs = (List<Document>) doc.get("singers");
                song.singers = new ArrayList<>();
                song.kinds = new ArrayList<>();
                for (Document docSinger : singer_docs) {
                    String sId = docSinger.getString("id");
                    String sName = docSinger.getString("name");
                    TReferencer singer = new TReferencer(sId, sName);
                    song.singers.add(singer);
                }
                
                for (Document docKind : kind_docs) {
                    String sId = docKind.getString("id");
                    String sName = docKind.getString("name");
                    TReferencer kind = new TReferencer(sId, sName);
                    song.kinds.add(kind);
                }
                song.image = doc.getString("image");
                song.views = doc.getLong("views");
                
                songs.add(song);
            }
        }
        return songs;
    }

    @Override
    public List<TSong> getAllSongs() {
        ArrayList<TSong> songs = new ArrayList<>();
        FindIterable<Document> docs = collectionSongs.find();
        
        if (docs != null) {
            for (Document doc : docs) {
                TSong song = new TSong();
                TModelInitiation.initTSong(song);
                song.id = doc.getString(FIELD_ID);
                song.name = doc.getString(FIELD_NAME);
                song.lyrics = (String) doc.get(FIELD_LYRIC);
                song.composers = (List<String>) doc.get(FIELD_COMPOSERS);
                song.views = doc.getLong(FIELD_VIEWS);
                song.image = doc.getString(FIELD_IMAGE);
                song.kara = doc.getString(FIELD_KARA);
                song.duration = doc.getInteger(FIELD_DURATION).shortValue();
                song.comment = doc.getString(FIELD_COMMENT);
                song.singers = new ArrayList<>();
                song.kinds = new ArrayList<>();
                List<Document> kind_docs = (List<Document>) doc.get(FIELD_KINDS);
                List<Document> singer_docs = (List<Document>) doc.get(FIELD_SINGERS);
                Document album_doc = (Document) doc.get(FIELD_ALBUM);
                
                song.album = new TReferencer(album_doc.getString("id"), album_doc.getString("name"));
                
                for (Document docSinger : singer_docs) {
                    String sId = docSinger.getString("id");
                    String sName = docSinger.getString("name");
                    TReferencer singer = new TReferencer(sId, sName);
                    song.singers.add(singer);
                }
                
                for (Document docKind : kind_docs) {
                    String sId = docKind.getString("id");
                    String sName = docKind.getString("name");
                    TReferencer kind = new TReferencer(sId, sName);
                    song.kinds.add(kind);
                }
                songs.add(song);
            }
        }
        return songs;
    }

    @Override
    public long getTotalDocumentInDB() {
        return collectionSongs.count();
    }

    @Override
    public void removeAllRecords() {
        Document filter = new Document();
        collectionSongs.deleteMany(filter);
    }

}
