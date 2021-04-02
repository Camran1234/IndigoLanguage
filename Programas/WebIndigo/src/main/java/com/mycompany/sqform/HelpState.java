/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sqform;

/**
 *Class that helps which rows are true or false, all depends in the conditions applied
 * @author camran1234
 */
public class HelpState {
    //Position of the row
    private int position=0;
    //State of the row
    private boolean state=false;
    
    public HelpState(int newPosition){
        position = newPosition;
    }
    
    public void setState(boolean newState){
        this.state = newState;
    }
    
    public boolean getState(){
        return state;
    }
    
    public int getPosition(){
        return position;
    }
    
    
    
}
