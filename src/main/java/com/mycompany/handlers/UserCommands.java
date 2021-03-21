/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.handlers;

import com.mycompany.formats.Login;
import com.mycompany.formats.User;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author camran1234
 */
public class UserCommands {
    
    ArrayList<User> userList = new ArrayList<>();
    ArrayList<Login> loginList = new ArrayList<>();
    User user;
    Login login;
    
    
    public void startLogin(){
        login = new Login();
    }
    
    public void loginUser(String user){
           /* Do Something */
    }
    public void loginPassword (String password){
        /* Do Something */
    }
    public void closeLogin(){
        /* Do Something */
    }
    /* Init for user commands*/
    public void close(){
        /* Do Something */
    }
    public void open(){
        /* Do Something */
    }
    public void addUser(String name){
        /* Do Something */
    }
    public void addPassword(String password){
        /* Do Something */
    }
    
    public void addDate(Date date){
        /* Do Something */
    }
    
    public boolean state(){
        /* Do Something */
        return false;
    }
    public void delete(String id){
        /* Do Something */
    }
    /* Init of Modify */
    public void openModify(){
        /* Do Something */
    }
    public void addPastUser(String name){
        /* Do Something */
    }
    public void addNewUser(String name){
        /* Do Something */
    }
    public void closeModify(){
        /* Do Something */
    }

    
    
    
}
