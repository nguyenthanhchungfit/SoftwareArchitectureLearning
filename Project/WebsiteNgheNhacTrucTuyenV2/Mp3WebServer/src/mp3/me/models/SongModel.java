/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.models;

import hapax.Template;
import hapax.TemplateDataDictionary;
import hapax.TemplateDictionary;
import hapax.TemplateException;
import hapax.TemplateLoader;
import java.util.ArrayList;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mp3.me.contracts.CrawlerContracts;
import mp3.me.contracts.ServerConfig;
import mp3.utils.cache.clients.SongRedisClient;
import mp3.utils.impl.FormatPureString;
import mp3.utils.thrift.clients.TSongClient;
import mp3.utils.thrift.models.TReferencer;
import mp3.utils.thrift.models.TSong;
import org.javasimon.SimonManager;
import org.javasimon.Split;
import org.javasimon.Stopwatch;

/**
 *
 * @author chungnt
 */
public class SongModel extends BaseModel {

    private static final Logger LOGGER = Logger.getLogger(SongModel.class);
    private static final SongRedisClient cacheClient = new SongRedisClient();
    private static final String KEY_HEADER_CACHE = "song: ";
    private static final TSongClient tSongClient = new TSongClient(SongModel.class.getName());
    private static Stopwatch stopwatch = SimonManager.getStopwatch(ServerConfig.SERVER_NAME);
    private static Stopwatch otherStopwatch = SimonManager.getStopwatch("other" + ServerConfig.STOP_WATCH_SONG_SERVLET);

    private static final String messageHeaderForLog = "GET SONG: id = ";

    public static final SongModel Instance = new SongModel();

    private SongModel() {

    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) {
        String methodHttp = req.getMethod();
        prepareHeaderHtml(resp);
        if (methodHttp.equals("GET")) {
            getSong(req, resp);
        } else if (methodHttp.equals("POST")) {

        }
    }

    private void getSong(HttpServletRequest req, HttpServletResponse resp) {
        Split split = stopwatch.start();
        String id_song = req.getParameter("id");
        
        //boolean isAdmin = this.updateCookie(req, resp);
        boolean isAdmin = true;
        
        // Test consumer log
        String messageLog = "";
        String messageForLog = "";
        try {
            Template tmplSong = templateLoader.getTemplate("song.xtm");
            TemplateDictionary tmplDic = new TemplateDictionary();

            if (id_song.equals("")) {
                _renderHtmlEmptySongId(tmplDic, isAdmin);
                messageForLog = messageHeaderForLog + id_song + " empty id!";
            } else {
                String keySongCache = KEY_HEADER_CACHE + id_song;
                TSong song = cacheClient.getCachedSong(keySongCache);
                boolean getCacheFlag = true;
                if (song == null) {
                    getCacheFlag = false;
                    song = tSongClient.getSongById(id_song);
                }

                if (song == null) {
                    _renderHtmlNullSong(tmplDic, isAdmin);
                    messageForLog = messageHeaderForLog + id_song + " return null!";
                } else {
                    _renderHtmlSuccessSong(tmplDic, isAdmin, song);
                    messageForLog = messageHeaderForLog + id_song + " " + "success";
                    if (getCacheFlag == false) {
                        cacheClient.putNewSongCache(song);
                    }
                }
            }

            // Logging and Monitoring
            split.stop();
            messageLog = FormatPureString.formatStringMessageLogs(ServerConfig.SERVER_NAME,
                    split.runningFor(), messageForLog);
            LOGGER.info(messageLog);
            outAndClose(req, resp, tmplSong.renderToString(tmplDic));

        } catch (TemplateException ex) {
            split.stop();
            messageForLog = messageHeaderForLog + id_song + " " + ex.getMessage();
            messageLog = FormatPureString.formatStringMessageLogs(ServerConfig.SERVER_NAME,
                    split.runningFor(), messageForLog);
            LOGGER.error(messageLog);
        }

    }

    private void _renderHtmlNullSong(TemplateDictionary tmplDic,
            boolean isAdmin) throws TemplateException {
        Template headerTemplate = templateLoader.getTemplate("partial_header.xtm");
        TemplateDictionary tmplDicHeader = new TemplateDictionary();

        // header template render
        _renderAdminHeader(tmplDicHeader, isAdmin);
        tmplDic.setVariable("header", headerTemplate.renderToString(tmplDicHeader));

        // footer
        tmplDic.setVariable("footer", "partial_footer.xtm");
    }

    private void _renderHtmlEmptySongId(TemplateDataDictionary tmplDic,
            boolean isAdmin) throws TemplateException {
        Template headerTemplate = templateLoader.getTemplate("partial_header.xtm");
        TemplateDictionary tmplDicHeader = new TemplateDictionary();

        // header
        _renderAdminHeader(tmplDicHeader, isAdmin);
        tmplDic.setVariable("header", headerTemplate.renderToString(tmplDicHeader));

        // footer
        tmplDic.setVariable("footer", "partial_footer.xtm");
    }

    private void _renderHtmlSuccessSong(TemplateDataDictionary tmplDic,
            boolean isAdmin, TSong song) throws TemplateException {
        Template headerTemplate = templateLoader.getTemplate("partial_header.xtm");
        TemplateDictionary tmplDicHeader = new TemplateDictionary();

        tmplDic.setVariable("song_name", song.name);
        tmplDic.setVariable("id_song", song.id);

        ArrayList<TReferencer> refsSinger = (ArrayList<TReferencer>) song.getSingers();
        for (TReferencer ref : refsSinger) {
            TemplateDataDictionary tempSinger = tmplDic.addSection("singers");
            String link_singer = "../singer?id=" + ref.id;
            String name_singer = ref.name;

            tempSinger.setVariable("name_singer", name_singer);
            tempSinger.setVariable("link_singer", link_singer);
        }

        String formatComposers = FormatPureString.formatStringFromStrings(song.composers);
        tmplDic.setVariable("composers", formatComposers);
        tmplDic.setVariable("album", song.album.name);

        String formatKinds = FormatPureString.formatStringFromRefs(song.kinds);
        tmplDic.setVariable("kinds", formatKinds);

        String link_data_mp3 = "../" + CrawlerContracts.LINK_PATH_SONG_DATA + song.id + ".mp3";
        tmplDic.setVariable("link_data_mp3", link_data_mp3);

        tmplDic.setVariable("views", song.views + "");
        tmplDic.setVariable("id_lyric", song.lyrics);
        // header
        tmplDicHeader.setVariable("href_home", "./");
        tmplDicHeader.setVariable("resource_zamp3_ic", "../static/public/images/zamp3.png");
        if (isAdmin) {
            tmplDicHeader.setVariable("style_display_btnAcc", "display:block;");
            tmplDicHeader.setVariable("style_display_btnLogin", "display:none;");
        } else {
            tmplDicHeader.setVariable("style_display_btnAcc", "display:none;");
            tmplDicHeader.setVariable("style_display_btnLogin", "display:block;");
        }
        tmplDic.setVariable("header", headerTemplate.renderToString(tmplDicHeader));

        // footer
        tmplDic.setVariable("footer", "partial_footer.xtm");
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
