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
public interface ContentCompatibilityChecker {
    public boolean check(Newsfeed news, Gamer gamer);
}
