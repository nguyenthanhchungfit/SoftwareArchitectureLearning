/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.models;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mp3.utils.cache.clients.SessionRedisClient;
import mp3.utils.entities.Session;

/**
 *
 * @author chungnt
 */
public class LogoutModel extends BaseModel {

    private static final SessionRedisClient cacheSessionClient = new SessionRedisClient();
    private static final String KEY_HEADER_CACHE_SESSION = "session:";

    public static final LogoutModel Instance = new LogoutModel();
    
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) {
        String methodHttp = req.getMethod();
        prepareHeaderHtml(resp);
        if (methodHttp.equals("GET")) {
            removeCookie(req, resp);
        } else if (methodHttp.equals("POST")) {
            removeCookie(req, resp);
        }
        try {
            resp.sendRedirect("/");
        } catch (IOException ex) {
            Logger.getLogger(LogoutModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void removeCookie(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String c_user_key = cookie.getName();
                if ("c_user".equals(c_user_key)) {
                    String c_user = cookie.getValue();
                    if (cacheSessionClient.isExistedKey(KEY_HEADER_CACHE_SESSION + c_user)) {
                        cookie.setMaxAge(0);
                        resp.addCookie(cookie);
                        cacheSessionClient.deleteCacheSessionAt(KEY_HEADER_CACHE_SESSION + c_user);
                        return;
                    }
                }
            }
        }
    }

}
