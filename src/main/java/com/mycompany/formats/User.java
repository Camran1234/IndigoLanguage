/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.formats;

import com.mycompany.handlers.ErrorCommands;
import com.mycompany.formats.Error;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private String token;
    private int line;
    private int column;
    private String pastUser;
    private String newUser;
    private String type;
    
    public User(String token, int line, int column, String type){
        activated = true;
        this.token = token;
        this.type = type;
        this.line = line;
        this.column = column;
    }
    
    public void close(){
        activated = false;
        if(date==null){
                date = new Date();
        }
    }
    
    public boolean getUser(String user){
        if(activated){
            this.user = user;
            return true;
        }
        return false;
    }
    public boolean getPassword(String password){
        if(activated){
            this.password = password;
            return true;
        }
        return false;
    }
    
    public boolean getDate(Date date){
        if(activated){
            this.date = date;
            return true;
        }
        return false;
    }

    public boolean getPastUser(String name) {
        if(activated){
            this.pastUser = name;
            return true;
        }
        return false;
    }
    
    public boolean getNewUser(String name) {
        if(activated){
            this.newUser = name;
            return true;
        }
        return false;
    }
    
    /**
     * Method to find semantic errors
     * @return 
     */
    public void checkForSemanticErrors(){
        String message;
        String errorMessage = "";
        boolean alreadyCheck=false;
        if(!activated){            
            // Start of mistakes of info obligatory
            if(type.equalsIgnoreCase("nuevo")){
                if((user==null || password==null)){
                    errorMessage = "Error Semantico";
                    if(user==null){
                        errorMessage += " no contiene usuario";
                    }
                    if(password==null){
                        errorMessage += " no contiene password";
                    }
                    addSemanticError(new Error(errorMessage,token,line,column));
                }
            }else if(type.equalsIgnoreCase("modificado")){
                if((newUser==null || pastUser==null || password==null)){
                    errorMessage = "Error Semantico ";
                    if(newUser==null){
                        errorMessage += " no contiene NEW_USER";
                    }
                    if(password==null){
                        errorMessage += " no contiene password";
                    }
                    if(pastUser==null){
                        errorMessage += " no contiene PAST_USER";
                    }
                    addSemanticError(new Error(errorMessage,token,line,column));
                }   
            }
            // END INFO OBLIGATORY
        }else{
            errorMessage = "Error Fatal no se cerro correctamente el usuario";
            addSemanticError(new Error(errorMessage,token,line,column));
        }
    }
    
    private void addSemanticError(Error newError){
        ErrorCommands.errors.add(newError);
    }
}
