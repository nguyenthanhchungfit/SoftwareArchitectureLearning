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

/**
 *
 * @author chungnt
 */
public class HomeModel extends BaseModel {

    public static final HomeModel Instance = new HomeModel();
    private static final SessionRedisClient cacheClient = new SessionRedisClient();
    private static final String KEY_HEADER_CACHE = "session:";

    private HomeModel() {
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) {
        String methodHttp = req.getMethod();
        prepareHeaderHtml(resp);
        if (methodHttp.equals("GET")) {
            getHome(req, resp);
        } else if (methodHttp.equals("POST")) {

        }
    }

    private void getHome(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Template tmpl = templateLoader.getTemplate("home.xtm");
            TemplateDictionary tmplDic = new TemplateDictionary();

            Template tmplHeader = templateLoader.getTemplate("partial_header.xtm");
            TemplateDictionary tmplHeaderDic = new TemplateDictionary();

            boolean isAdmin = _updateCookie(req, resp);

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

            tmplDic.setVariable("footer", "partial_footer.xtm");
            outAndClose(req, resp, tmpl.renderToString(tmplDic));
        } catch (Exception e) {
            e.printStackTrace();
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
                    if (cacheClient.isExistedKey(KEY_HEADER_CACHE + c_user)) {
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
