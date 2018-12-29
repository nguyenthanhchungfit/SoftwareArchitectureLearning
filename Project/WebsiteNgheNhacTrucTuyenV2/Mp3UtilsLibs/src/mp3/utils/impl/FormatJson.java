/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.utils.impl;

import java.util.ArrayList;
import java.util.List;
import mp3.utils.thrift.models.TDataLyric;
import mp3.utils.thrift.models.TReferencer;
import mp3.utils.thrift.models.TSong;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author cpu11165-local
 */
public class FormatJson {
    public static String convertDataLyricsToJSON(ArrayList<TDataLyric> dataLyrics){
        JSONArray entriesArray = new JSONArray();
        
        for(TDataLyric dataLyric : dataLyrics){
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("contributor", dataLyric.contributor);
            jsonObj.put("content", dataLyric.content);
            entriesArray.add(jsonObj);
        }
        
        return entriesArray.toJSONString();
    }
    
    public static String convertFromRefsToJSONStringArr(List<TReferencer> refs){
        String res = "";
        int size = refs.size();
        if(size > 0){
            res += "\""+ refs.get(0).name +"\"";
        }
        for(int i = 1; i < size; i++){
            res += " ," + "\""+ refs.get(i).name +"\"";
        }
        
        return res;
    }
    
    public static JSONArray convertFromSongESEToJSONArray(ArrayList<TSong> songs){
        JSONArray entriesArray = new JSONArray();
        for(TSong song : songs){
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("id", song.id);
            jsonObj.put("name", song.name);
            
            JSONArray jSingers = new JSONArray();
            for(TReferencer ref : song.singers){
                jSingers.add(ref.name);
            }
            jsonObj.put("singers", jSingers);
            
            entriesArray.add(jsonObj);
        }
        return entriesArray;
    }
}
