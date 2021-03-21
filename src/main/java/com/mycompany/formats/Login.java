/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.formats;

/**
 *
 * @author camran1234
 */
public class Login {
    private boolean activated=false;
    private String user;
    private String password;
    
    public Login(){
        activated = true;
    }
    public void closeLogin(){
        activated=false;
    }
    public void getUser(String user){
        if(activated=true){
            this.user = user;
        }
    }
    public void getPassword(String password){
        if(activated=true){
            this.password = password;
            print("Se agrego el usuario "+user+" al login, con contrasena: "+password);
        }
    }
    public void print(String message){
        System.out.println("MESSAGE: "+message);
    }
    
}
