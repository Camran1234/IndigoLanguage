/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.formats;

import java.util.Date;

/**
 *
 * @author camran1234
 */
public class User {
    private boolean activated = false;
    private String user;
    private String password;
    private Date date;
    
    
    
    public User(){
        activated = true;
    }
    
    public void close(){
        activated = false;
    }
    
    public void getUser(String user){
        this.user = user;
    }
    public void getPassword(String password){
        this.password = password;
    }
}
