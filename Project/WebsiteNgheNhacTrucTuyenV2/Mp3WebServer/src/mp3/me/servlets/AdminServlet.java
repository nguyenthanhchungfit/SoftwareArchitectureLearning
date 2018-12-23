/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.servlets;

import hapax.Template;
import hapax.TemplateDictionary;
import hapax.TemplateLoader;
import hapax.TemplateResourceLoader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chungnt
 */
public class AdminServlet extends HttpServlet {
//    private static final String HOST_DATA_SERVER = DataServerContract.HOST_SERVER;
//    private static final int PORT_DATA_SERVER = DataServerContract.PORT;
//
//    private static final String HOST_USER_SERVER = UserServerContract.HOST_SERVER;
//    private static final int PORT_USER_SERVER = UserServerContract.PORT;
//
//    private static final String SERVER_NAME = MP3ServerContract.SERVRE_NAME;
//
//    private DataCacher dataCacher = DataCacher.getInstance();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        boolean flag_user = this.updateCookie(req, resp);
//        //boolean flag_user = true;
//
//        if (flag_user) {
//            long amountSong = getAmountSongs();
//            long amountUser = getAmountUsers();
//            long amountSinger = getAmountSingers();
//            long amountAlbum = getAmountAlbums();
//            long amountLyric = getAmountLyrics();
//
//            resp.setStatus(HttpServletResponse.SC_OK);
//            resp.setContentType("text/html;charset=UTF-8");
//            PrintWriter out = resp.getWriter();
//            TemplateLoader templateLoader = TemplateResourceLoader.create("public/hapax/");
//
//            try {
//                Template template = templateLoader.getTemplate("admin.xtm");
//                TemplateDictionary templateDictionary = new TemplateDictionary();
//                // Asign Value
//                templateDictionary.setVariable("amount_users", amountUser + "");
//                templateDictionary.setVariable("amount_songs", amountSong + "");
//                templateDictionary.setVariable("amount_singers", amountSinger + "");
//                templateDictionary.setVariable("amount_albums", amountAlbum + "");
//                templateDictionary.setVariable("amount_lyrics", amountLyric + "");
//
//                out.println(template.renderToString(templateDictionary));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }else{
//            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            resp.getWriter().println("<h1>NOT FOUND</h1>");
//        }
//
//    }
//
//    // Amount_User
//    private long getAmountUsersFromUserServer() {
//        long amount = 0;
//        try {
//            TSocket socket = new TSocket(HOST_USER_SERVER, PORT_USER_SERVER);
//            TTransport transport = new TFramedTransport(socket);
//            transport.open();
//
//            TBinaryProtocol protocol = new TBinaryProtocol(transport);
//            TMultiplexedProtocol mpUserServices = new TMultiplexedProtocol(protocol, "UserServices");
//            UserServices.Client userServices = new UserServices.Client(mpUserServices);
//            amount = userServices.getTotalNumberUsers();
//            transport.close();
//        } catch (TException ex) {
//            ex.printStackTrace();
//        }
//        return amount;
//    }
//
//    private long getAmountUsers() {
//        long amount = 0;
//        String keyUser = DataCacher.KEY_AMOUNT_USER;
//        if (dataCacher.isExisted(keyUser)) {
//            amount = dataCacher.getAmount(keyUser);
//        } else {
//            amount = getAmountUsersFromUserServer();
//            dataCacher.insertNewAmount(keyUser, amount);
//        }
//        return amount;
//    }
//
//    // Amount_Songs
//    private long getAmountSongsFromDataServer() {
//        long amount = 0;
//        try {
//            TSocket socket = new TSocket(HOST_DATA_SERVER, PORT_DATA_SERVER);
//            TTransport transport = new TFramedTransport(socket);
//            transport.open();
//
//            TBinaryProtocol protocol = new TBinaryProtocol(transport);
//            TMultiplexedProtocol mpSongServices = new TMultiplexedProtocol(protocol, "SongServices");
//            SongServices.Client songServices = new SongServices.Client(mpSongServices);
//
//            amount = songServices.getTotalNumberSongs();
//            transport.close();
//        } catch (TException ex) {
//            ex.printStackTrace();
//        }
//        return amount;
//    }
//
//    private long getAmountSongs() {
//        long amount = 0;
//        String keySong = DataCacher.KEY_AMOUNT_SONG;
//        if (dataCacher.isExisted(keySong)) {
//            amount = dataCacher.getAmount(keySong);
//        } else {
//            amount = getAmountSongsFromDataServer();
//            dataCacher.insertNewAmount(keySong, amount);
//        }
//        return amount;
//    }
//
//    // Amount_Singers
//    private long getAmountSingersFromDataServer() {
//        long amount = 0;
//        try {
//            TSocket socket = new TSocket(HOST_DATA_SERVER, PORT_DATA_SERVER);
//            TTransport transport = new TFramedTransport(socket);
//            transport.open();
//
//            TBinaryProtocol protocol = new TBinaryProtocol(transport);
//            TMultiplexedProtocol mpSingerServices = new TMultiplexedProtocol(protocol, "SingerServices");
//            SingerServices.Client singerServices = new SingerServices.Client(mpSingerServices);
//            amount = singerServices.getTotalNumberSingers();
//            transport.close();
//
//        } catch (TException ex) {
//            ex.printStackTrace();
//        }
//        return amount;
//    }
//
//    private long getAmountSingers() {
//        long amount = 0;
//        String keySinger = DataCacher.KEY_AMOUNT_SINGER;
//        if (dataCacher.isExisted(keySinger)) {
//            amount = dataCacher.getAmount(keySinger);
//        } else {
//            amount = getAmountSingersFromDataServer();
//            dataCacher.insertNewAmount(keySinger, amount);
//        }
//        return amount;
//    }
//
//    // Amount_Albums
//    private long getAmountAlbumsFromDataServer() {
//        long amount = 0;
//        try {
//            TSocket socket = new TSocket(HOST_DATA_SERVER, PORT_DATA_SERVER);
//            TTransport transport = new TFramedTransport(socket);
//            transport.open();
//
//            TBinaryProtocol protocol = new TBinaryProtocol(transport);
//            TMultiplexedProtocol mpALbumServices = new TMultiplexedProtocol(protocol, "AlbumServices");
//            AlbumServices.Client albumServices = new AlbumServices.Client(mpALbumServices);
//
//            amount = albumServices.getTotalNumberAlbums();
//            transport.close();
//        } catch (TException ex) {
//            ex.printStackTrace();
//        }
//        return amount;
//    }
//
//    private long getAmountAlbums() {
//        long amount = 0;
//        String keyAlbum = DataCacher.KEY_AMOUNT_ALBUM;
//        if (dataCacher.isExisted(keyAlbum)) {
//            amount = dataCacher.getAmount(keyAlbum);
//        } else {
//            amount = getAmountAlbumsFromDataServer();
//            dataCacher.insertNewAmount(keyAlbum, amount);
//        }
//        return amount;
//    }
//
//    // Amount_Lyrics
//    private long getAmountLyricsFromDataServer() {
//        long amount = 0;
//        try {
//            TSocket socket = new TSocket(HOST_DATA_SERVER, PORT_DATA_SERVER);
//            TTransport transport = new TFramedTransport(socket);
//            transport.open();
//
//            TBinaryProtocol protocol = new TBinaryProtocol(transport);
//            TMultiplexedProtocol mpLyricServices = new TMultiplexedProtocol(protocol, "LyricServices");
//            LyricServices.Client lyricServices = new LyricServices.Client(mpLyricServices);
//
//            amount = lyricServices.getTotalNumberLyrics();
//            transport.close();
//        } catch (TException ex) {
//            ex.printStackTrace();
//        }
//        return amount;
//    }
//
//    private long getAmountLyrics() {
//        long amount = 0;
//        String keyLyric = DataCacher.KEY_AMOUNT_LYRIC;
//        if (dataCacher.isExisted(keyLyric)) {
//            amount = dataCacher.getAmount(keyLyric);
//        } else {
//            amount = getAmountLyricsFromDataServer();
//            dataCacher.insertNewAmount(keyLyric, amount);
//        }
//        return amount;
//    }
//
//    private boolean updateCookie(HttpServletRequest req, HttpServletResponse resp){
//        Cookie[] cookies = req.getCookies();
//        boolean flag_user = false;
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                String c_user_key = cookie.getName();
//                if ("c_user".equals(c_user_key)) {
//                    System.out.println("Cookie Path: " + cookie.getPath());
//                    String c_user = cookie.getValue();
//                    flag_user = this.isAdminSession(c_user);
//                    if (flag_user) {
//                        cookie.setMaxAge(Session.MAX_AGE);
//                        resp.addCookie(cookie);
//                    }
//                    break;
//                }
//            }
//        }
//        return flag_user;
//    }
//    
//    // Check Admin
//    private boolean isAdminSession(String c_user) {
//        boolean isAdmin = false;
//        try {
//
//            TSocket socket = new TSocket(HOST_USER_SERVER, PORT_USER_SERVER);
//            TTransport transport = new TFramedTransport(socket);
//            transport.open();
//
//            TBinaryProtocol protocol = new TBinaryProtocol(transport);
//            TMultiplexedProtocol mpUserServices = new TMultiplexedProtocol(protocol, "UserServices");
//            UserServices.Client userServices = new UserServices.Client(mpUserServices);
//            isAdmin = userServices.isAdminSession(c_user);
//
//            transport.close();
//        } catch (TException ex) {
//            ex.printStackTrace();
//        }
//        return isAdmin;
//    }
}
