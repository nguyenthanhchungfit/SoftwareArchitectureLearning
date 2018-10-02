/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author chungnt
 */
public class ServerObjectManager {

    private static int NextAvailableHandle = 1;

    private static Map<Integer, SObject> objects = new HashMap<Integer, SObject>();

    public static int registerObject(SObject sObject) {
        sObject.handle = NextAvailableHandle++;
        objects.put(sObject.handle, sObject);
        return sObject.handle;
    }

    public static int createObject(String strClassName) {
        SObject sObject = null;
        switch (strClassName) {
            case "CProduct":
                sObject = new SProduct();
                break;
        }
        if (sObject != null) {
            registerObject(sObject);
            return sObject.handle;
        }
        return -1;
    }

    public static SObject findObjectByHandle(int handle) {
        if (objects.containsKey(handle)) {
            return objects.get(handle);
        }
        return null;
    }

    public static boolean setObjectAttribute(int handle, String sAttributeName, String newValue) {
        try {
            SObject sObject = findObjectByHandle(handle);
            return sObject.setAttribute(sAttributeName, newValue);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }
    
    public static String getObjectAttribute(int handle, String sAttribute){
        try{
            SObject sObject = findObjectByHandle(handle);
            return sObject.getAttribute(sAttribute);
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            return "";
        }
    }
}