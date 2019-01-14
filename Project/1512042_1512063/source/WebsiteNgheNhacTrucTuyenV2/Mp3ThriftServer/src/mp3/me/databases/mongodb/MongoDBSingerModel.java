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
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import mp3.me.contracts.DBMongoContract;
import mp3.me.databases.ISingerDB;
import mp3.utils.thrift.initiation.TModelInitiation;
import mp3.utils.thrift.models.TReferencer;
import mp3.utils.thrift.models.TSinger;
import mp3.utils.thrift.models.TSingerResult;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author chungnt
 */
public class MongoDBSingerModel implements ISingerDB {

    private static MongoClient mongo = null;
    private static MongoDatabase mongo_db = null;
    private static MongoCollection<Document> collectionSingers = null;
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_REALNAME = "realname";
    private static final String FIELD_DOB = "dob";
    private static final String FIELD_COUNTRY = "country";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_SONGS = "songs";
    private static final String FIELD_VIDEOS = "videos";
    private static final String FIELD_ALBUMS = "albums";
    private static final String FIELD_IMG_AVT = "img_avatar";
    private static final String FIELD_IMG_COVER = "img_cover";

    static {
        mongo = new MongoClient(DBMongoContract.HOST, DBMongoContract.PORT);
        mongo_db = mongo.getDatabase(DBMongoContract.DATABASE_NAME);
        collectionSingers = mongo_db.getCollection(DBMongoContract.COLLECTION_SINGERS);

    }

    @Override
    public TSingerResult getSinger(String idSinger) {
        TSingerResult res = new TSingerResult();
        TSinger singer = getSingerInformation(idSinger);
        if(singer != null){
            res.error = 0;
            res.singer = singer;
        }else{
            res.error = -1;
            res.singer = null;
        }
        return res;
    }

    @Override
    public TSinger getSingerInformation(String idSinger) {
        TSinger singer = null;
        FindIterable<Document> docs = collectionSingers.find(new Document("id", idSinger));
        Document doc = docs.first();
        if (null != doc) {
            singer = new TSinger();
            TModelInitiation.initTSinger(singer);
            singer.id = doc.getString("id");
            singer.name = doc.getString("name");
            singer.realname = doc.getString("realname");
            singer.dob = doc.getString("dob");
            singer.country = doc.getString("country");
            singer.description = doc.getString("description");
            List<Document> song_docs = (List<Document>) doc.get("songs");
            List<Document> video_docs = (List<Document>) doc.get("videos");
            List<Document> album_docs = (List<Document>) doc.get("albums");

            for (Document docSong : song_docs) {
                String sID = docSong.getString("id");
                String sName = docSong.getString("name");
                singer.songs.add(new TReferencer(sID, sName));
            }

            for (Document docVideo : video_docs) {
                String sID = docVideo.getString("id");
                String sName = docVideo.getString("name");
                singer.songs.add(new TReferencer(sID, sName));
            }

            for (Document docAlbum : album_docs) {
                String sID = docAlbum.getString("id");
                String sName = docAlbum.getString("name");
                singer.songs.add(new TReferencer(sID, sName));
            }

            singer.imgAvatar = doc.getString("img_avatar");
            singer.imgCover = doc.getString("img_cover");
        }
        return singer;
    }

    @Override
    public boolean isExistedSinger(String id) {
        FindIterable<Document> k = collectionSingers.find(new Document("id", id));
        if (k.iterator().hasNext()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean insertSinger(TSinger singer) {
        if (!isExistedSinger(singer.id)) {
            ArrayList<Document> song_docs = DBMongoContract.getReferencers((ArrayList<TReferencer>) singer.songs);
            ArrayList<Document> album_docs = DBMongoContract.getReferencers((ArrayList<TReferencer>) singer.albums);
            ArrayList<Document> video_docs = DBMongoContract.getReferencers((ArrayList<TReferencer>) singer.videos);
            Document document = new Document(FIELD_ID, singer.getId())
                    .append(FIELD_NAME, singer.getName())
                    .append(FIELD_REALNAME, singer.getRealname())
                    .append(FIELD_DOB, singer.getDob())
                    .append(FIELD_COUNTRY, singer.getCountry())
                    .append(FIELD_DESCRIPTION, singer.getDescription())
                    .append(FIELD_SONGS, song_docs)
                    .append(FIELD_ALBUMS, album_docs)
                    .append(FIELD_VIDEOS, video_docs)
                    .append(FIELD_IMG_AVT, singer.imgAvatar)
                    .append(FIELD_IMG_COVER, singer.imgCover);

            collectionSingers.insertOne(document);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertSingers(List<TSinger> list) {
        boolean isSuccess = true;
        for (TSinger singer : list) {
            isSuccess = insertSinger(singer);
        }
        return isSuccess;
    }

    @Override
    public boolean isExistedSongInSinger(String idSinger, String idSong) {
        FindIterable<Document> k = collectionSingers.find(new Document(FIELD_ID, idSinger));
        if (k != null) {
            Document doc = k.first();
            ArrayList<Document> song_refs = (ArrayList<Document>) doc.get(FIELD_SONGS);
            for (Document ref : song_refs) {
                String idFind = ref.getString("id");
                if (idSong.equals(idFind)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void insertNewAlbumToAlbumSinger(String idSinger, TReferencer newAlbum) {
        if (!isExistedSongInSinger(idSinger, newAlbum.id)) {
            Document updateDoc = new Document("id", newAlbum.id).append("name", newAlbum.name);
            Document modifiedObject = new Document("$push", new Document(FIELD_ALBUMS.concat(".albums"), updateDoc));
            Bson filter = eq(FIELD_ID, idSinger);
            //Bson change = $push("Subscribed Topics", "Some Topic");
            collectionSingers.updateOne(filter, modifiedObject);

        }
    }

    @Override
    public List<TSinger> getAllSingers() {
        ArrayList<TSinger> singers = new ArrayList<>();
        FindIterable<Document> docs = collectionSingers.find();
        TSinger singer;
        if (docs != null) {
            for (Document doc : docs) {
                singer = new TSinger();
                TModelInitiation.initTSinger(singer);
                singer.id = doc.getString("id");
                singer.name = doc.getString("name");
                singer.realname = doc.getString("realname");
                singer.dob = doc.getString("dob");
                singer.country = doc.getString("country");
                singer.description = doc.getString("description");
                List<Document> song_docs = (List<Document>) doc.get("songs");
                List<Document> video_docs = (List<Document>) doc.get("videos");
                List<Document> album_docs = (List<Document>) doc.get("albums");

                for (Document docSong : song_docs) {
                    String sID = docSong.getString("id");
                    String sName = docSong.getString("name");
                    singer.songs.add(new TReferencer(sID, sName));
                }

                for (Document docVideo : video_docs) {
                    String sID = docVideo.getString("id");
                    String sName = docVideo.getString("name");
                    singer.songs.add(new TReferencer(sID, sName));
                }

                for (Document docAlbum : album_docs) {
                    String sID = docAlbum.getString("id");
                    String sName = docAlbum.getString("name");
                    singer.songs.add(new TReferencer(sID, sName));
                }

                singer.imgAvatar = doc.getString("img_avatar");
                singer.imgCover = doc.getString("img_cover");

                singers.add(singer);
            }
        }
        return singers;
    }

    @Override
    public long getTotalDocumentInDB() {
        return collectionSingers.count();
    }

    @Override
    public void removeAllRecords() {
        Document filter = new Document();
        collectionSingers.deleteMany(filter);
    }

}
