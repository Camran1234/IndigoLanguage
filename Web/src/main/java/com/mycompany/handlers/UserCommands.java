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
    User user;
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
         login.setUser(user);
    }
    public void loginPassword (String password){
        login.setPassword(password);
    }
    public void closeLogin(String token, int line, int column){
        loginList.add(login);
        login.closeLogin(token, line, column);
    }
    /* Init for user commands*/
    public void close(String token, int line, int column){
            userList.add(user);
            user.close(token, line, column);
    }
    public void open(String token, int line, int column){
        user = new User(token, line, column, "nuevo");
        System.out.println("Open new USER");
    }
    public void addUser(String name){
        if(!user.setUser(name)){
            user.setUser(name);
        }
    }
    public void addPassword(String password){
        if(!user.setPassword(password)){
            user.setPassword(password);
        }
    }
    
    public void addDate(Date date){
        if(!user.setDate(date)){
            user.setDate(date);
        }
    }
    
    public void delete(String id){
        deleteList.add(id);
    }
    /* Init of Modify */
    public void openModify(String token, int line, int column){
        user = new User(token, line, column,"modificado");
        System.out.println("Modify User");
    }
    public void addPastUser(String name){
        if(!user.setPastUser(name)){
            System.out.println("Antiguo Usuario: "+name);
            user.setPastUser(name);
        }
    }
    public void addNewUser(String name){
        if(!user.setNewUser(name)){
            System.out.println("Nuevo Usuario: "+name);
            user.setNewUser(name);
        }
    }
    public void closeModify(String token, int line, int column){
        modifyList.add(user);
        user.close(token, line, column);
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public ArrayList<User> getModifyList() {
        return modifyList;
    }

    public ArrayList<String> getDeleteList() {
        return deleteList;
    }

    public ArrayList<Login> getLoginList() {
        return loginList;
    }
    
    
}
