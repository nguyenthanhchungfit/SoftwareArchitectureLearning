/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3.utils.impl;

/**
 *
 * @author chungnt
 */
public class DatatypeParserHelper {
    public static int parseStringToInt(String number){
        try{
            return Integer.parseInt(number);
        }catch(NumberFormatException ex){
            return 0;
        } 
    }
    
    public static long parseStringToLong(String number){
        try{
            return Long.parseLong(number);
        }catch(NumberFormatException ex){
            return 0;
        } 
    }
}
