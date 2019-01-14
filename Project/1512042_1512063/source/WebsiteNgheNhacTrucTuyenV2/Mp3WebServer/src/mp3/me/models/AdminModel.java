/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.models;

import hapax.Template;
import hapax.TemplateDictionary;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mp3.utils.cache.clients.SessionRedisClient;
import mp3.utils.entities.Session;
import mp3.utils.thrift.clients.TAlbumClient;
import mp3.utils.thrift.clients.TLyricClient;
import mp3.utils.thrift.clients.TSingerClient;
import mp3.utils.thrift.clients.TSongClient;
import mp3.utils.thrift.clients.TUserClient;

/**
 *
 * @author chungnt
 */
public class AdminModel extends BaseModel {

    public static final AdminModel Instace = new AdminModel();
    
    private static final TSongClient tSongClient = new TSongClient(AdminModel.class.getName());
    private static final TSingerClient tSingerClient = new TSingerClient(AdminModel.class.getName());
    private static final TAlbumClient tAlbumClient = new TAlbumClient(AdminModel.class.getName());
    private static final TLyricClient tLyricClient = new TLyricClient(AdminModel.class.getName());
    private static final TUserClient tUserClient = new TUserClient(AdminModel.class.getName());
    private static final SessionRedisClient cacheSessionClient = new SessionRedisClient();
    private static final String KEY_HEADER_CACHE_SESSION = "session:";

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) {
        String methodHttp = req.getMethod();
        prepareHeaderHtml(resp);
        if (methodHttp.equals("GET")) {
            getAdmin(req, resp);
        } else if (methodHttp.equals("POST")) {

        }
    }

    private void getAdmin(HttpServletRequest req, HttpServletResponse resp) {
        if (_updateCookie(req, resp)) {
            long amountSong = tSongClient.getTotalNumberSongs();
            long amountUser = 1;
            long amountSinger = tSingerClient.getTotalNumberSingers();
            long amountAlbum = tAlbumClient.getTotalNumberAlbums();
            long amountLyric = tLyricClient.getTotalNumberLyrics();

            try {
                Template tmpl = templateLoader.getTemplate("admin.xtm");
                TemplateDictionary tmplDic = new TemplateDictionary();
                // Asign Value
                tmplDic.setVariable("amount_users", amountUser + "");
                tmplDic.setVariable("amount_songs", amountSong + "");
                tmplDic.setVariable("amount_singers", amountSinger + "");
                tmplDic.setVariable("amount_albums", amountAlbum + "");
                tmplDic.setVariable("amount_lyrics", amountLyric + "");

                outAndClose(req, resp, tmpl.renderToString(tmplDic));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            outAndClose(req, resp, "<h1>NOT FOUND</h1>");
        }
    }

    private boolean _updateCookie(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        boolean flag_user = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String c_user_key = cookie.getName();
                if ("c_user".equals(c_user_key)) {
                    String c_user = cookie.getValue();
                    Session session = cacheSessionClient.getCacheSession(KEY_HEADER_CACHE_SESSION + c_user);
                    if (session != null) {
                        cookie.setMaxAge(Session.MAX_AGE);
                        resp.addCookie(cookie);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
