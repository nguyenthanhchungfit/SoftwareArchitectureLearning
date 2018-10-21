/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author chungnt
 */
public class Newsfeed {
    private String title;
    private String content;
    private Date timestamp;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public Newsfeed() {
        timestamp = new Date(System.currentTimeMillis());
    }

    public Newsfeed(String title, String content, Date timestamp) {
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Newsfeed(String title, String content) {
        this.title = title;
        this.content = content;
        this.timestamp = new Date(System.currentTimeMillis());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format("*** %s  - %s\n%s", dateFormat.format(timestamp), title, content);
    }
    
    
}
