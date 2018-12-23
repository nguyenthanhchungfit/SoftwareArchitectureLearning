/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.me.models;

import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chungnt
 */
public class SongModel extends BaseModel{
    private static final Logger LOGGER = Logger.getLogger(SongModel.class.getName());
    
    public static final SongModel Instance = new SongModel();
    
    private SongModel(){
    
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) {
        String methodHttp = req.getMethod();
        if(methodHttp.equals("GET")){
            doGet(req, resp);
        }else if(methodHttp.equals("POST")){
            doPost(req, resp);
        }
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp){
    
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp){
    
    }
    
}
