/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author chungnt
 */
public abstract class CObject {
    protected int handle;
    
    void attach(int handle){
        this.handle = handle;
    }
    
    int dettach(){
        int handle = this.handle;
        this.handle = 0;
        return handle;
    }
    
}
