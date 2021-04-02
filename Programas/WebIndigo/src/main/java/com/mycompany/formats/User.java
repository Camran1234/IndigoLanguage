/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.formats;

import com.mycompany.handlers.ErrorCommands;
import com.mycompany.formats.ErrorIndigo;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author camran1234
 */
public class User implements ErrorHandler{
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
        System.out.println("Token: "+token+", line: "+line+", column: "+column+", type: "+type);
        this.token = token;
        this.type = type;
        this.line = line;
        this.column = column;
    }
    
    public void close(String token, int line, int column){
        activated = false;
        if(date==null){
                date = new Date();
        }
        this.token = token;
        this.line = line;
        this.column = column;
    }

    public boolean isActivated() {
        return activated;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Date getDate() {
        return date;
    }

    public String getToken() {
        return token;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String getPastUser() {
        return pastUser;
    }

    public String getNewUser() {
        return newUser;
    }

    public String getType() {
        return type;
    }
    
    public void setMode(boolean mode){
        this.activated = mode;
    }
    
    public boolean setUser(String user){
        if(activated){
            if(user!=null){
                this.user = user;
                return true;
            }
        }
        return false;
    }
    public boolean setPassword(String password){
        if(activated){
            if(password!=null){
                this.password = password;
                return true;
            }
        }
        return false;
    }
    
    public boolean setDate(Date date){
        if(activated){
            if(date!=null){
                this.date = date;
                return true;
            }
        }
        return false;
    }

    public boolean setPastUser(String name) {
        if(activated){
            if(name!=null){
                this.pastUser = name;
                return true;    
            }
        }
        return false;
    }
    
    public boolean setNewUser(String name) {
        if(activated){
            if(name!=null){
                this.newUser = name;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Method to find semantic errors
     * @return 
     */
    @Override
    public void checkForSemanticErrors(){
        String errorMessage = "";
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
                    addSemanticError(new ErrorIndigo(errorMessage,token,line,column));
                }
            }else if(type.equalsIgnoreCase("modificado")){
                if((newUser==null || pastUser==null || password==null)){
                    errorMessage = "Error Semantico ";
                    if(newUser==null){
                        errorMessage += " no contiene USUARIO_NUEVO";
                    }
                    if(password==null){
                        errorMessage += " no contiene password";
                    }
                    if(pastUser==null){
                        errorMessage += " no contiene USUARIO_ANTIGUO";
                    }
                    addSemanticError(new ErrorIndigo(errorMessage,token,line,column));
                }   
            }
            // END INFO OBLIGATORY
        }else{
            errorMessage = "Error Fatal no se cerro correctamente el usuario";
            addSemanticError(new ErrorIndigo(errorMessage,token,line,column));
        }
    }
    
    private void addSemanticError(ErrorIndigo newError){
        ErrorCommands.errors.add(newError);
    }
}
