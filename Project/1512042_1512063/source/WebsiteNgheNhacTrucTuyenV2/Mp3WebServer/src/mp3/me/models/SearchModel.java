/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.models;

import hapax.Template;
import hapax.TemplateDataDictionary;
import hapax.TemplateDictionary;
import hapax.TemplateLoader;
import hapax.TemplateResourceLoader;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mp3.elasticsearch.client.ElasticSearchSongClient;
import mp3.me.contracts.CrawlerContracts;
import mp3.me.contracts.ServerConfig;
import static mp3.me.contracts.ServerConfig.SERVER_NAME;
import mp3.utils.cache.clients.SessionRedisClient;
import mp3.utils.cache.clients.SongRedisClient;
import mp3.utils.entities.Session;
import mp3.utils.impl.FormatJson;
import mp3.utils.impl.FormatPureString;
import mp3.utils.thrift.clients.TElasticSearchClient;
import mp3.utils.thrift.clients.TSongClient;
import mp3.utils.thrift.models.TReferencer;
import mp3.utils.thrift.models.TSong;
import org.apache.log4j.Logger;
import org.javasimon.SimonManager;
import org.javasimon.Split;
import org.javasimon.Stopwatch;
import org.json.simple.JSONArray;

/**
 *
 * @author chungnt
 */
public class SearchModel extends BaseModel {

    public static final SearchModel Instance = new SearchModel();
    
    private static final Logger LOGGER = Logger.getLogger(SearchModel.class);
    private static final ElasticSearchSongClient esClient = new ElasticSearchSongClient(SearchModel.class.getName());
    private static final TSongClient tSongClient = new TSongClient(SearchModel.class.getName());
    private static Stopwatch stopwatch = SimonManager.getStopwatch(ServerConfig.STOP_WATCH_SEARCH_SERVLET);
    private static Stopwatch otherStopwatch = SimonManager.getStopwatch("other" + ServerConfig.STOP_WATCH_SEARCH_SERVLET);
    private static final SessionRedisClient cacheSessionClient = new SessionRedisClient();
    private static final String KEY_HEADER_CACHE_SESSION = "session:";
    
    private static final String messageHeaderForLog = "GET SONG: id = ";

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) {
        String methodHttp = req.getMethod();
        prepareHeaderHtml(resp);
        if (methodHttp.equals("GET")) {
            getElasticSearch(req, resp);
        } else if (methodHttp.equals("POST")) {
            getNormalSearch(req, resp);
        }
    }

    private void getElasticSearch(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        if (name != null) {
            List<TSong> songs = esClient.getListSongSearchByName(name);
            resp.setContentType("text/plain;charset=UTF-8");
            JSONArray res = FormatJson.convertFromSongESEToJSONArray(songs);
            outAndClose(req, resp, res);
        } else {
            resp.setContentType("text/html;charset=UTF-8");
            try {
                Template tmpl = templateLoader.getTemplate("home.xtm");
                TemplateDictionary tmplDic = new TemplateDictionary();
                tmplDic.setVariable("footer", "partial_footer.xtm");
                outAndClose(req, resp, tmpl.renderToString(tmplDic));
            } catch (Exception e) {
                e.printStackTrace();
            }
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
    
    private void getNormalSearch(HttpServletRequest req, HttpServletResponse resp) {
        Split split = stopwatch.start();
        String messageLog = "";
        String song_name = req.getParameter("search_text");

        if (song_name == null) {
            split.stop();
            messageLog = FormatPureString.formatStringMessageLogs(SERVER_NAME, split.runningFor(),
                    messageHeaderForLog + "name=null");
            LOGGER.warn(messageLog);
            outAndClose(req, resp, "<h2>Empty Name Search</h2>");
            return;
        }

        boolean isAdmin = _updateCookie(req, resp);

        // Nếu name rỗng thì trả lại giao diện chính 
        if (song_name.equals("")) {
            try {
                Template tmpl = templateLoader.getTemplate("home.xtm");
                Template tmplHeader = templateLoader.getTemplate("partial_header.xtm");
                TemplateDictionary tmplHeaderDic = new TemplateDictionary();

                TemplateDictionary tmplDic = new TemplateDictionary();
                tmplDic.setVariable("search_name", song_name);

                // header template render
                tmplHeaderDic.setVariable("href_home", "/");
                tmplHeaderDic.setVariable("resource_zamp3_ic", "./static/public/images/zamp3.png");
                if (isAdmin) {
                    tmplHeaderDic.setVariable("style_display_btnAcc", "display:block;");
                    tmplHeaderDic.setVariable("style_display_btnLogin", "display:none;");
                } else {
                    tmplHeaderDic.setVariable("style_display_btnAcc", "display:none;");
                    tmplHeaderDic.setVariable("style_display_btnLogin", "display:block;");
                }
                tmplDic.setVariable("header", tmplHeader.renderToString(tmplHeaderDic));

                // footer
                tmplDic.setVariable("footer", "partial_footer.xtm");

                outAndClose(req, resp, tmpl.renderToString(tmplDic));

                // Logger
                split.stop();
                messageLog = FormatPureString.formatStringMessageLogs(SERVER_NAME, split.runningFor(),
                        messageHeaderForLog + "name=" + song_name + " : result=empty name");
                LOGGER.info(messageLog);
            } catch (Exception e) {
                split.stop();
                messageLog = FormatPureString.formatStringMessageLogs(SERVER_NAME, split.runningFor(),
                        messageHeaderForLog + "name=" + song_name + " : result=empty name" + " error=" + e.getMessage());
                LOGGER.error(messageLog);
                e.printStackTrace();
            } finally {
            }
        }else{
            List<TSong> songs = tSongClient.getListSongSearchByName(song_name);
            try {
                Template tmpl = templateLoader.getTemplate("search.xtm");
                TemplateDictionary tmplDic = new TemplateDictionary();

                Template tmplHeader = templateLoader.getTemplate("partial_header.xtm");
                TemplateDictionary tmplHeaderDic = new TemplateDictionary();

                int size = songs.size();
                tmplDic.setVariable("result_mount", songs.size());
                tmplDic.setVariable("search_name", song_name);
                for (int i = 0; i < size; i++) {
                    TSong song = songs.get(i);
                    TemplateDataDictionary tmplDataDic = tmplDic.addSection("item");
                    String link_song = "../song?id=" + song.id;
                    String link_img_song = "../" + CrawlerContracts.LINK_PATH_SONG + song.image;
                    tmplDataDic.setVariable("link_song", link_song);
                    tmplDataDic.setVariable("link_image", link_img_song);
                    tmplDataDic.setVariable("name_song", song.name);

                    ArrayList<TReferencer> refsSinger = (ArrayList<TReferencer>) song.getSingers();
                    for (TReferencer ref : refsSinger) {
                        TemplateDataDictionary tmplSingerDic = tmplDataDic.addSection("singers");
                        String link_singer = "../singer?id=" + ref.id;
                        String name_singer = ref.name;

                        tmplSingerDic.setVariable("name_singer", name_singer);
                        tmplSingerDic.setVariable("link_singer", link_singer);
                    }

                    String kind = FormatPureString.formatStringFromRefs(song.kinds);
                    tmplDataDic.setVariable("kinds", kind);

                    String views = ((Long) song.views).toString();
                    tmplDataDic.setVariable("views", views);

                }

                // header template render
                tmplHeaderDic.setVariable("href_home", "./");
                tmplHeaderDic.setVariable("resource_zamp3_ic", "../static/public/images/zamp3.png");
                if (isAdmin) {
                    tmplHeaderDic.setVariable("style_display_btnAcc", "display:block;");
                    tmplHeaderDic.setVariable("style_display_btnLogin", "display:none;");
                } else {
                    tmplHeaderDic.setVariable("style_display_btnAcc", "display:none;");
                    tmplHeaderDic.setVariable("style_display_btnLogin", "display:block;");
                }
                tmplDic.setVariable("header", tmplHeader.renderToString(tmplHeaderDic));

                // footer
                tmplDic.setVariable("footer", "partial_footer.xtm");

                outAndClose(req, resp, tmpl.renderToString(tmplDic));
                
                // Logger
                split.stop();
                messageLog = FormatPureString.formatStringMessageLogs(SERVER_NAME, split.runningFor(),
                        messageHeaderForLog + "name=" + song_name + " : result=" + songs.size() + " songs");
                LOGGER.info(messageLog);
            } catch (Exception e) {
                split.stop();
                messageLog = FormatPureString.formatStringMessageLogs(SERVER_NAME, split.runningFor(),
                        messageHeaderForLog + "name=" + song_name + " : error:" + e.getMessage());
                LOGGER.error(messageLog);
                e.printStackTrace();

            } finally {
            }
        }
        
    }
}