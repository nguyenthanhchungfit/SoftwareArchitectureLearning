/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.models;

import hapax.Template;
import hapax.TemplateDictionary;
import hapax.TemplateException;
import java.util.logging.Level;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mp3.me.contracts.ServerConfig;
import mp3.utils.cache.clients.SessionRedisClient;
import mp3.utils.cache.clients.SingerRedisClient;
import mp3.utils.entities.Session;
import mp3.utils.impl.FormatPureString;
import mp3.utils.thrift.clients.TSingerClient;
import mp3.utils.thrift.models.TSinger;
import org.apache.log4j.Logger;
import org.javasimon.SimonManager;
import org.javasimon.Split;
import org.javasimon.Stopwatch;

/**
 *
 * @author chungnt
 */
public class SingerModel extends BaseModel {

    public static final SingerModel Instance = new SingerModel();
    private static final Logger LOGGER = Logger.getLogger(SingerModel.class);
    private static final SingerRedisClient cacheClient = new SingerRedisClient();
    private static final String KEY_HEADER_CACHE = "singer: ";
    private static final TSingerClient tSingerClient = new TSingerClient(SongModel.class.getName());
    private static Stopwatch stopwatch = SimonManager.getStopwatch(ServerConfig.STOP_WATCH_SINGER_SERVLET);
    private static Stopwatch otherStopwatch = SimonManager.getStopwatch("other" + ServerConfig.STOP_WATCH_SONG_SERVLET);
    private static final SessionRedisClient cacheSessionClient = new SessionRedisClient();
    private static final String KEY_HEADER_CACHE_SESSION = "session:";
    private static final String messageHeaderForLog = "GET SINGER: id = ";

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) {
        String methodHttp = req.getMethod();
        prepareHeaderHtml(resp);
        if (methodHttp.equals("GET")) {
            getSinger(req, resp);
        } else if (methodHttp.equals("POST")) {
            
        }
    }

    private void getSinger(HttpServletRequest req, HttpServletResponse resp) {
        Split split = stopwatch.start();
        String idSinger = req.getParameter("id");

        // boolean isAdmin = this.updateCookie(req, resp);
        boolean isAdmin = _updateCookie(req, resp);

        String messageForLog = "";
        String messageLog = "";

        try {
            Template tmplSinger = templateLoader.getTemplate("singer.xtm");
            TemplateDictionary tmplDic = new TemplateDictionary();

            String keyCacheSinger = KEY_HEADER_CACHE + idSinger;
            TSinger singer = cacheClient.getCachedSinger(keyCacheSinger);
            boolean getCachedSinger = true;
            if (singer == null) {
                getCachedSinger = false;
                singer = tSingerClient.getSingerById(idSinger);
            }

            if (singer == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                messageForLog = messageHeaderForLog + " id=" + idSinger + " :" + "null";
                outAndClose(req, resp, "404 NOT FOUND!");
            } else {
                _renderHtmlSuccessSinger(tmplDic, isAdmin, singer);
                messageForLog = messageHeaderForLog + " id=" + idSinger + " :" + "name=" + singer.name;
                if(getCachedSinger){
                    cacheClient.putNewSingerCache(singer);
                }
                outAndClose(req, resp, tmplSinger.renderToString(tmplDic));
            }

            split.stop();
            messageLog = FormatPureString.formatStringMessageLogs(ServerConfig.SERVER_NAME, split.runningFor(),
                    messageForLog);
        } catch (TemplateException ex) {
            split.stop();
            messageForLog =  messageHeaderForLog + " id=" + idSinger + " : error=" +  ex.getMessage();
            messageLog = FormatPureString.formatStringMessageLogs(ServerConfig.SERVER_NAME, split.runningFor(), 
                        messageForLog );
            LOGGER.error(messageLog);
        }
    }

    private void _renderHtmlSuccessSinger(TemplateDictionary tmplDic, boolean isAdmin,
            TSinger singer) throws TemplateException {

        Template headerTemplate = templateLoader.getTemplate("partial_header.xtm");
        TemplateDictionary tmplDicHeader = new TemplateDictionary();

        String linkCoverImage = "../static/public/images/singers/cover/" + singer.imgCover;
        String linkAvatarImage = "../static/public/images/singers/avatar/" + singer.imgAvatar;
        tmplDic.setVariable("img_cover", linkCoverImage);
        tmplDic.setVariable("img_avatar", linkAvatarImage);
        tmplDic.setVariable("name", singer.name);
        tmplDic.setVariable("realname", singer.realname);
        tmplDic.setVariable("dob", singer.dob);
        tmplDic.setVariable("country", singer.country);
        tmplDic.setVariable("description", singer.description);

        _renderAdminHeader(tmplDicHeader, isAdmin);

        tmplDic.setVariable("header", headerTemplate.renderToString(tmplDicHeader));

        // footer template render
        // k sử dụng dạng include > trong partial_view
        //tmplDic.setVariable("footer", footerTemplate.renderToString(tmplDicFooter));
        tmplDic.setVariable("footer", "partial_footer.xtm");

    }

    private boolean _updateCookie(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String c_user_key = cookie.getName();
                if ("c_user".equals(c_user_key)) {
                    String c_user = cookie.getValue();
                    if (cacheSessionClient.isExistedKey(KEY_HEADER_CACHE_SESSION + c_user)) {
                        cookie.setMaxAge(Session.MAX_AGE);
                        resp.addCookie(cookie);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private void _renderAdminHeader(TemplateDictionary tmplDicHeader, boolean isAdmin) {
        tmplDicHeader.setVariable("href_home", "./");
        tmplDicHeader.setVariable("resource_zamp3_ic", "../static/public/images/zamp3.png");
        if (isAdmin) {
            tmplDicHeader.setVariable("style_display_btnAcc", "display:block;");
            tmplDicHeader.setVariable("style_display_btnLogin", "display:none;");
        } else {
            tmplDicHeader.setVariable("style_display_btnAcc", "display:none;");
            tmplDicHeader.setVariable("style_display_btnLogin", "display:block;");
        }
    }
}
