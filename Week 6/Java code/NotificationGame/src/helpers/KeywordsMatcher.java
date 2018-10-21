/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import gamer.Gamer;
import server.Newsfeed;

/**
 *
 * @author chungnt
 */
public class KeywordsMatcher implements ContentCompatibilityChecker{

    @Override
    public boolean check(Newsfeed news, Gamer gamer) {
        for(String keyword : gamer.getGameNames()){
            if(news.getContent().toLowerCase().contains(keyword.toLowerCase())){
                return true;
            }
        }
        return false;
    }
    
}
