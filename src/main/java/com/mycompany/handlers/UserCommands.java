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
    ArrayList<User> modifyList = new ArrayList<>();
    ArrayList<String> deleteList = new ArrayList<>();
    ArrayList<Login> loginList = new ArrayList<>();
    ArrayList<Error> errors = new ArrayList<>();
    User user;
    User userModify;
    Login login;
    
    /**
     * Method to get Semantic errors of the list getted
     */
    public void checkForErrors(){
        for(int indexUser=0; indexUser<userList.size(); indexUser++){
            userList.get(indexUser).checkForSemanticErrors();
        }
        for(int indexModify=0; indexModify<modifyList.size(); indexModify++){
            modifyList.get(indexModify).checkForSemanticErrors();
        }
    }
    
    public void startLogin(String token, int line, int column){
        login = new Login(token, line, column);
    }
    public void loginUser(String user){
         login.getUser(user);
    }
    public void loginPassword (String password){
        login.getPassword(password);
    }
    public void closeLogin(){
        loginList.add(login);
        login.closeLogin();
    }
    /* Init for user commands*/
    public void close(){
        userList.add(user);
        user.close();
    }
    public void open(String token, int line, int column){
        user = new User(token, line, column, "nuevo");
    }
    public void addUser(String name){
        if(!user.getUser(name)){
            userModify.getUser(name);
        }
    }
    public void addPassword(String password){
        if(!user.getPassword(password)){
            userModify.getPassword(password);
        }
    }
    
    public void addDate(Date date){
        if(!user.getDate(date)){
            userModify.getDate(date);
        }
    }
    
    public void delete(String id){
        deleteList.add(id);
    }
    /* Init of Modify */
    public void openModify(String token, int line, int column){
        userModify = new User(token, line, column,"modificado");
    }
    public void addPastUser(String name){
        if(!user.getPastUser(name)){
            userModify.getPastUser(name);
        }
    }
    public void addNewUser(String name){
        if(!user.getNewUser(name)){
            userModify.getNewUser(name);
        }
    }
    public void closeModify(){
        modifyList.add(userModify);
        userModify.close();
    }
}
