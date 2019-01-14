/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.models;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mp3.me.contracts.ServerConfig;
import mp3.utils.cache.clients.SessionRedisClient;
import mp3.utils.cache.clients.SongRedisClient;
import mp3.utils.entities.Session;
import mp3.utils.thrift.clients.TSongClient;
import mp3.utils.thrift.clients.TUserClient;
import org.apache.log4j.Logger;
import org.javasimon.SimonManager;
import org.javasimon.Split;
import org.javasimon.Stopwatch;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import javax.servlet.http.Cookie;
import static mp3.me.contracts.ServerConfig.SERVER_NAME;
import mp3.utils.impl.FormatPureString;

/**
 *
 * @author chungnt
 */
public class LoginModel extends BaseModel {

    public static final LoginModel Instance = new LoginModel();

    private static final Logger LOGGER = Logger.getLogger(LoginModel.class);
    private static final SessionRedisClient cacheClient = new SessionRedisClient();
    private static final String KEY_HEADER_CACHE = "session:";
    private static final TUserClient tUserClient = new TUserClient(LoginModel.class.getName());
    private static Stopwatch stopwatch = SimonManager.getStopwatch(ServerConfig.STOP_WATCH_LOGIN_SERVLET);
    private static Stopwatch otherStopwatch = SimonManager.getStopwatch("other" + ServerConfig.STOP_WATCH_LOGIN_SERVLET);

    private static String messageForLog = "Login ";

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) {
        String methodHttp = req.getMethod();
        prepareHeaderHtml(resp);
        if (methodHttp.equals("GET")) {

        } else if (methodHttp.equals("POST")) {
            login(req, resp);
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) {
        Split split = stopwatch.start();
        String messageLog = "";

        System.out.println("Login - POST");

        resp.setContentType("text/plain");
        resp.setStatus(HttpServletResponse.SC_OK);

        String decodedData = this.getDataAjax(req);

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + " : " + cookie.getValue());
            }
        }

        if (!decodedData.isEmpty()) {

            Map<String, String> dataParse = this.parseFromQueryString(decodedData);
            String username = dataParse.get("username");
            String password = dataParse.get("password");

            System.out.println(username);
            System.out.println(password);

            String c_user = tUserClient.login(username, password);

            System.out.println("C_USER" + c_user);
            
            if (!c_user.isEmpty()) {
                Cookie cookie = new Cookie("c_user", c_user);
                cookie.setMaxAge(Session.MAX_AGE);
                cookie.setPath("/");
                resp.addCookie(cookie);
                
                Session session = new Session();
                session.setUsername(username);
                session.setType(0);
                session.setSessionID(c_user);
                session.setLastAccess(new Date());
                session.setExpires(new Date(System.currentTimeMillis() + session.getMaxAge()));
                cacheClient.putNewCacheSession(session);
            }
            outAndClose(req, resp, c_user);

            split.stop();
            messageLog = FormatPureString.formatStringMessageLogs(ServerConfig.SERVER_NAME, split.runningFor(),
                    messageForLog + "username=" + username + " password=" + password + " user_id=" + c_user);
            LOGGER.info(messageLog);

        } else {
            split.stop();
            messageLog = FormatPureString.formatStringMessageLogs(SERVER_NAME, split.runningFor(),
                    messageForLog + "error: Not data received!");
            LOGGER.warn(messageLog);
            outAndClose(req, resp, "Not data received!");
        }
        
    }

    private String getDataAjax(HttpServletRequest req) {
        InputStream is = null;
        try {
            is = req.getInputStream();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] buf = new byte[32];
            int r = 0;
            while (r >= 0) {
                r = is.read(buf);
                if (r >= 0) {
                    os.write(buf, 0, r);
                }
            }   String s = new String(os.toByteArray(), "UTF-8");
            String decoded = URLDecoder.decode(s, "UTF-8");
            return decoded;
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "";
    }

    private Map<String, String> parseFromQueryString(String query) {
        Map<String, String> data = new HashMap<>();
        String[] arr = query.split("&");
        for (String ele : arr) {
            String[] eles = ele.split("=");
            data.put(eles[0], eles[1]);
        }
        return data;
    }
}

