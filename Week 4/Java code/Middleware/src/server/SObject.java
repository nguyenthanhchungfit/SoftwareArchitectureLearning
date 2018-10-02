/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author chungnt
 */
public abstract class SObject {
    protected int handle;

    public int getHandle() {
        return handle;
    }

    public void setHandle(int handle) {
        this.handle = handle;
    }

    public abstract boolean setAttribute(String attribute, String newValue);
    
    public abstract String getAttribute(String attribute);
    public SObject(){
        
    }
}
