/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.models;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mp3.me.contracts.ServerConfig;
import static mp3.me.contracts.ServerConfig.SERVER_NAME;
import mp3.utils.cache.clients.LyricRedisClient;
import mp3.utils.cache.clients.SongRedisClient;
import mp3.utils.impl.FormatJson;
import mp3.utils.impl.FormatPureString;
import mp3.utils.thrift.clients.TLyricClient;
import mp3.utils.thrift.clients.TSongClient;
import mp3.utils.thrift.models.TDataLyric;
import org.apache.log4j.Logger;
import org.javasimon.SimonManager;
import org.javasimon.Split;
import org.javasimon.Stopwatch;

/**
 *
 * @author chungnt
 */
public class LyricModel extends BaseModel{

    private static final Logger LOGGER = Logger.getLogger(LyricModel.class);
    private static final LyricRedisClient cacheClient = new LyricRedisClient();
    private static final String KEY_HEADER_CACHE = "lyric: ";
    private static final TLyricClient tLyricClient = new TLyricClient(LyricModel.class.getName());
    private static Stopwatch stopwatch = SimonManager.getStopwatch(ServerConfig.STOP_WATCH_LYRIC_SERVLET);
    private static Stopwatch otherStopwatch = SimonManager.getStopwatch("other" + ServerConfig.STOP_WATCH_LYRIC_SERVLET);
    private static String messsageForLog = "GET LYRIC ";
    
    public static final LyricModel Instance = new LyricModel();
    
    private LyricModel(){}
    
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) {
        String methodHttp = req.getMethod();
        prepareHeaderHtml(resp);
        if (methodHttp.equals("GET")) {
            getLyric(req, resp);
        } else if (methodHttp.equals("POST")) {

        }
    }
    
    private void getLyric(HttpServletRequest req, HttpServletResponse resp){
        Split split = stopwatch.start();
        String id = req.getParameter("id");
        resp.setContentType("text/plain;charset=UTF-8");
        List<TDataLyric> dataLyrics = tLyricClient.getDataLyricsById(id);
        
        split.stop();
        String messageLog = FormatPureString.formatStringMessageLogs(SERVER_NAME, split.runningFor(), 
                messsageForLog + "id=" +id + " : lyric_count=" + dataLyrics.size());
        LOGGER.info(messageLog);
        
        outAndClose(req, resp, FormatJson.convertDataLyricsToJSON((ArrayList<TDataLyric>) dataLyrics));
    }
    
}
