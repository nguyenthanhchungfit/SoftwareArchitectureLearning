/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import server.ServerObjectManager;

/**
 *
 * @author chungnt
 */
public class ClientObjectManager {
    static String getObjectAttribute(int handle, String strAttributeName){
        return ServerObjectManager.getObjectAttribute(handle, strAttributeName);
    }
    
    static int createObject(String strClassName){
        return ServerObjectManager.createObject(strClassName);
    }
    
    static boolean setObjectAttribute(int handle, String strAttributeName, String newValue){
        return ServerObjectManager.setObjectAttribute(handle, strAttributeName, newValue);
    }
}
