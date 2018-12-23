/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.crawler.models;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mp3.utils.thrift.initiation.TModelInitiation;
import mp3.utils.thrift.models.TAlbum;
import mp3.utils.thrift.models.TDataLyric;
import mp3.utils.thrift.models.TLyric;
import mp3.utils.thrift.models.TReferencer;
import mp3.utils.thrift.models.TSinger;
import mp3.utils.thrift.models.TSong;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author chungnt
 */
public class Mp3Crawler implements ICrawler {

    @Override
    public void crawlSongByName(String songName) {
        try {
            System.out.println("\n**** Crawling song : " + songName + "\n");
            String eSong = songName.trim();
            eSong = eSong.replace(" ", "+");
            String query = "https://mp3.zing.vn/tim-kiem/bai-hat.html?q=" + eSong;

            Document doc = Jsoup.connect(query).get();
            Elements item_songs = doc.getElementsByClass("item-song");
            System.out.println(item_songs.size());
            int i = 0;
            for (Element ele : item_songs) {
                String id = ele.attr("id");
                System.out.println(id);
                Element titleE = ele.select("a").first();
                String urlSong = titleE.attr("href");
                urlSong = "https://mp3.zing.vn" + urlSong;
                System.out.println(urlSong);

                TSong song = new TSong();
                TModelInitiation.initTSong(song);

                boolean success;
                success = crawlSongByUrl(urlSong);
                if (success == true) {
                    i++;
                }
                if (i == 3) {
                    break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Mp3Crawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Mp3Crawler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean crawlSongByUrl(String url) throws IOException, ParseException {
        TSong song = new TSong();
        List<TSinger> singers = new ArrayList<>();
        //List<Kind> kinds = new ArrayList<>();
        TAlbum album = null;
        TLyric lyric = new TLyric();
        TModelInitiation.initTSong(song);

        TModelInitiation.initTLyric(lyric);

        Document docSong = Jsoup.connect(url).
                header("Accept-Encoding", "gzip, deflate")
                .userAgent("Mozilla/5.0(X11; Ubuntu; Linu...)Gecko/20100101 Firefox/60.0")
                .maxBodySize(0)
                .ignoreHttpErrors(true)
                .timeout(600000)
                .get();

        Element data_json = docSong.select("#zplayerjs-wrapper").first();
        String url_data_json = CrawlerContracts.HOST + "/xhr" + data_json.attr("data-xml");
        if (url_data_json.indexOf("type=video") > -1) {
            System.out.println("\n***Not implement crawl Video!!\n");
            return false;
        }
        System.out.println(url_data_json);

        String dataJson = Jsoup.connect(url_data_json).get().body().html();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(dataJson);
        String dJSONIDSong = "", dJSONNameSong = "", dJSONLinkDataMP3 = "",
                dJSONKaraLink = "", dJSONAlbumID = "", dJSONAlbumName = "",
                dJSONAlbumImg = "", dJSONSongImg;
        long dJSONDuration = 0;

        JSONObject dataJsonObj = (JSONObject) jsonObject.get("data");
        System.out.println(dataJsonObj.toJSONString());
        dJSONIDSong = (String) dataJsonObj.get("id");
        System.out.println(dJSONIDSong);
        dJSONNameSong = (String) dataJsonObj.get("name");
        System.out.println(dJSONNameSong);
        dJSONSongImg = (String) dataJsonObj.get("thumbnail");

        dJSONKaraLink = (String) dataJsonObj.get("lyric");
        System.out.println(dJSONKaraLink);
        dJSONDuration = (long) dataJsonObj.get("duration");
        System.out.println(dJSONDuration);
        dJSONLinkDataMP3 = (String) ((JSONObject) dataJsonObj.get("source")).get("128");
        if (dJSONLinkDataMP3.indexOf("//") == 0) {
            dJSONLinkDataMP3 = dJSONLinkDataMP3.replace("//", "");
        }
        System.out.println(dJSONLinkDataMP3);

        JSONObject albumJsonObj = (JSONObject) dataJsonObj.get("album");
        if (albumJsonObj != null) {
            dJSONAlbumID = (String) albumJsonObj.get("id");
            System.out.println(dJSONAlbumID);
            dJSONAlbumName = (String) albumJsonObj.get("name");
            System.out.println(dJSONAlbumName);
            dJSONAlbumImg = (String) albumJsonObj.get("thumbnail_medium");

            album = new TAlbum();
            TModelInitiation.initTAlbum(album);

            album.id = dJSONAlbumID;
            album.name = dJSONAlbumName;
            album.image = album.id + ".jpg";
            album.songs.add(new TReferencer(song.id, song.name));
        }

        // Set value from json
        song.setId(dJSONIDSong);
        song.setName(dJSONNameSong);

        //return;
        Element songInfor = docSong.select("div.info-top-play").first();
        if (songInfor != null) {
            Elements singersI = songInfor.child(0).getElementsByTag("h2");
            for (Element ele : singersI) {
                TSinger singer = new TSinger();
                TModelInitiation.initTSinger(singer);
                //System.out.println(ele.attr("data-id"));
                singer.setId(ele.attr("data-id"));
                String href = ele.child(0).attr("href");
                singer.setName(ele.text());
                singer.imgCover = singer.imgAvatar = singer.id + ".jpg";
                //Craw data singer
                //------------------------------------
                String linkProfile = "https://mp3.zing.vn" + href + "/tieu-su";
                crawlSinger(linkProfile, singer);

                TReferencer singerRef = new TReferencer(singer.id, singer.name);

                song.getSingers().add(singerRef);

                singer.songs.add(new TReferencer(song.id, song.name));
                if (album != null) {
                    singer.albums.add(new TReferencer(singer.id, singer.name));
                }
                singers.add(singer);
            }
        }

        if (songInfor != null) {
            Elements composersI = songInfor.child(1).getElementsByTag("div");
            for (Element ele : composersI) {
                if (ele.hasAttr("id")) {
                    if (ele.attr("id").equals("composer-container")) {
                        String composer = ele.child(0).text();
                        song.getComposers().add(composer);
                    }
                }
            }
        }

        if (songInfor != null) {
            Elements kindsI = songInfor.child(2).getElementsByTag("a");
            for (Element ele : kindsI) {
                String id = ele.attr("data-id");
                String name = ele.text();
                //Kind kind = new Kind();
                //ModelInitiation.initKind(kind);
                //kind.setId(id);
                //kind.setName(name);
                song.getKinds().add(new TReferencer(id, name));
                //kind.songs.add(new TReferencer(song.id, song.name));
                //kinds.add(kind);
            }
        }

        Element lyricE = docSong.select("div#lyrics-song").first();
        if (lyricE != null) {
            Elements lyricsE = lyricE.child(0).child(0).child(0).children();
            lyric.id = song.id;
            song.lyrics = lyric.id;
            for (Element ele : lyricsE) {
                Element content = ele.getElementsByTag("p").first();
                Element lyricsU = ele.getElementsByClass("fn-user").first();
                TDataLyric dataLyric = new TDataLyric();
                TModelInitiation.initTDataLyric(dataLyric);
                dataLyric.contributor = lyricsU.text();
                dataLyric.content = content.html();
                lyric.datas.add(dataLyric);
            }
        }

        // Gan data tham chieu
        if (album != null) {
            song.album.id = album.id;
            song.album.name = album.name;
        }

        song.lyrics = lyric.id;

        // Nap data
        song.kara = this.crawlKara(dJSONKaraLink);
        song.duration = (short) dJSONDuration;
        song.comment = song.id;
        song.image = song.id + ".jpg";

        System.out.println("*****Song:");
        System.out.println(song);
        if (album != null) {
            System.out.println("*****Album:");
            System.out.println(album);
        }
        System.out.println("*****Lyric:");
        System.out.println(lyric);
        System.out.println("*****Singers:");
        for (TSinger singer : singers) {
            System.out.println(singer);
        }

        // Chen DB
        /*
        dbSongModel.InsertSong(song);

        if (album != null) {
            dbAlbumModel.InsertAlbum(album);
        }

        dbLyricModel.InsertLyric(lyric);
        dbSingerModel.InsertSingers(singers);

        // crawl resource
        ProducerKafka.send(topicNameResourceDownload, "https://" + dJSONLinkDataMP3, CrawlerContracts.PATH_SONG_DATA + song.id + ".mp3");

        if (album != null) {
            // crawl image album
            ProducerKafka.send(topicNameResourceDownload, dJSONAlbumImg, CrawlerContracts.PATH_ALBUM + album.id + ".jpg");
        }
        // crawl image song
        this.crawlAndSaveFile(new URL(dJSONSongImg), CrawlerContracts.PATH_SONG + song.id + ".jpg");

        // index elasctich
        ESESong eseSong = new ESESong();
        eseSong.InsertNewSong(song.id, song.name, FormatJson.convertFromRefsToJSONStringArr(song.singers));
         */
        return true;
    }

    private void crawlSinger(String url, TSinger singer) throws IOException {
        if (singer == null) {
            return;
        }
        Document docSinger = Jsoup.connect(url).
                header("Accept-Encoding", "gzip, deflate")
                .userAgent("Mozilla/5.0(X11; Ubuntu; Linu...)Gecko/20100101 Firefox/60.0")
                .maxBodySize(0)
                .timeout(600000)
                .ignoreHttpErrors(true)
                .get();

        Element infor = docSinger.select("div.entry").first();
        Element imgsDoc = docSinger.select("div.wrapper-page").first();
        String imgCover = imgsDoc.child(0).child(0).child(0).attr("src");
        String imgAVT = imgsDoc.child(0).child(0).child(1).child(0).child(0).child(0).attr("src");

        // CRAWL IMAGE SINGER
        this.crawlAndSaveFile(new URL(imgCover), CrawlerContracts.PATH_SINGER_COVER + singer.id + ".jpg");
        this.crawlAndSaveFile(new URL(imgAVT), CrawlerContracts.PATH_SINGER_AVATAR + singer.id + ".jpg");

        if (infor != null) {
            Elements liE = infor.child(0).children();
            String realName = liE.get(0).text();
            String dob = liE.get(1).text();
            String country = liE.get(2).text();
            singer.setRealname(realName.substring(realName.indexOf(':') + 1));
            singer.setDob(dob.substring(dob.indexOf(':') + 1));
            singer.setCountry(country.substring(country.indexOf(':') + 1));

            String description = infor.text();
            String sdescription = description.substring(realName.length() + dob.length()
                    + country.length() + 3);
            singer.setDescription(sdescription);

        }
    }

    public void crawlAndSaveFile(URL url, String pathSave) throws IOException {
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(pathSave);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        System.out.println("***** Done saved :" + pathSave);
    }

    private String crawlKara(String urlStr) throws IOException {
        String res = "";
        if (!urlStr.isEmpty()) {
            String tempFileSave = "temp.txt";
            URL url = new URL(urlStr);
            this.crawlAndSaveFile(url, tempFileSave);

            FileReader fileReader = new FileReader(tempFileSave);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                res += line;
            }
        }

        return res;
    }
}
