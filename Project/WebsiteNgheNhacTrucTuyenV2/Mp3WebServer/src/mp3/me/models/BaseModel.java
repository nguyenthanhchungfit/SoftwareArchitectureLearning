/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.models;

import hapax.TemplateLoader;
import hapax.TemplateResourceLoader;
import java.io.PrintWriter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author chungnt
 */
public abstract class BaseModel {

    private static final Logger LOGGER = Logger.getLogger(BaseModel.class);
    protected static final TemplateLoader templateLoader = TemplateResourceLoader.create("public/hapax/");

    public abstract void process(HttpServletRequest req, HttpServletResponse resp);

    protected boolean outAndClose(HttpServletRequest req, HttpServletResponse resp, Object content) {
        boolean result = false;
        PrintWriter out = null;
        try {
            out = resp.getWriter();
            out.print(content);
            result = true;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage() + " while processing URI \"" + req.getRequestURI() + "?" + req.getQueryString() + "\"");
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return result;
    }

    protected void prepareHeaderHtml(HttpServletResponse resp) {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");
    }

    protected void prepareHeaderJs(HttpServletResponse resp) {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/javascript; charset=UTF-8");
    }

    protected String _getParam(HttpServletRequest req, String key) {
        String ret = req.getParameter(key);
        if (ret != null) {
            return ret;
        }
        return "";
    }

    protected String[] _getParamValue(HttpServletRequest req, String key) {
        String[] ret = req.getParameterValues(key);
        if (ret != null) {
            return ret;
        }
        return null;
    }

    protected int _getIntParam(HttpServletRequest req, String key, int val) {
        String ret = req.getParameter(key);
        if (ret == null) {
            return val;
        }
        try {
            ret = ret.trim();
            return Integer.parseInt(ret);
        } catch (Exception e) {
        }
        return val;
    }

    protected long _getLongParam(HttpServletRequest req, String key, long val) {
        String ret = req.getParameter(key);
        if (ret == null) {
            return val;
        }
        try {
            ret = ret.trim();
            return Long.parseLong(ret);
        } catch (Exception e) {
        }
        return val;
    }

    protected String _getCookie(HttpServletRequest req, String key) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie.getValue();
                }
            }
        }

        return "";
    }

    protected void _response(HttpServletResponse resp, String content) {
        PrintWriter out = null;
        try {
            resp.setContentType("text/html;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            out = resp.getWriter();
            out.println(content);
            resp.setHeader("Connection", "close");
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception ex) {
            LOGGER.error(null, ex);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
