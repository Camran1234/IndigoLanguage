/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.formats;

import com.mycompany.handlers.ErrorCommands;
import com.mycompany.indigo.Analysis;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author camran1234
 */
public class Form implements ErrorHandler{
    private String id;
    private String tittle;
    private String name;
    private String topic;
    private String type;
    private String userCreator;
    private String visibility="PUBLIC";
    private Date date;
    private String token;
    private int line;
    private int column;
    private boolean activated=false;

    
    
    /**
     * new for new Forms
     * modify for modifying Forms
     * String of the token created, the line and column putted
     * @param type 
     */
    public Form(String token, int line, int column, String type){
        activated = true;
        this.type = type;
        this.token = token;
        this.line = line;
        this.column = column;
    }
    
    /**
     * Close the entry of data of this object
     */
    public void close(String token, int line, int column){
        this.token =  token;
        this.line = line;
        this.column = column;
        activated = false;
        if(date==null){
            date = new Date();
        }
        if(userCreator==null){
            userCreator = new Analysis().getLoggedUser();
        }
    }
    
    public void setId(String id) {
        if(activated){
                this.id = id;
        }
    }

    public void setTittle(String tittle) {
        if(activated){
                this.tittle = tittle;
        }
    }

    public void setName(String name) {
        if(activated){
                this.name = name;
        }
    }

    public void setUser(String userCreator){
        if(activated){
           this.userCreator = userCreator;   
        }
    }
    
    public void setTopic(String topic) {
        if(activated){
            this.topic = topic;
        }
    }

    public void setDate(Date date) {
        if(activated){
            this.date = date;   
        }
    }
    
    public void setVisibility(String visibility){
        if(activated){
            this.visibility = visibility;
        }
    }
    
    
    public void setMode(boolean mode){
        activated = true;
    }

    public String getId() {
        return id;
    }

    public String getTittle() {
        return tittle;
    }

    public String getName() {
        return name;
    }

    public String getTopic() {
        return topic;
    }

    public String getType() {
        return type;
    }

    public String getUserCreator() {
        return userCreator;
    }

    public Date getDate() {
        return date;
    }

    public String getToken() {
        return token;
    }

    public String getVisibility(){
        return visibility;
    }
    
    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public boolean isActivated() {
        return activated;
    }

    
    
    
    @Override
    public void checkForSemanticErrors(){
        String errorMessage="";
        if(!activated){
            if(type.equalsIgnoreCase("new")){
                if(id==null || tittle==null||name==null||topic==null){
                    if(id==null){
                        errorMessage = " no se establecio el id del formulario";
                    }
                    if(tittle==null){
                        errorMessage = " no se establecio el titulo del formulario";
                    }
                    if(name==null){
                        errorMessage = " no se establecio el nombre del formulario";
                    }
                    if(topic==null){
                        errorMessage = " no se establecio el tema del formulario";
                    }
                    addSemanticError(new ErrorIndigo(errorMessage,token,line,column));
                }
            }else{
                if(id==null || tittle==null||name==null||topic==null){
                    if(id==null){
                        errorMessage = " no se establecio el id del formulario";
                    }
                    if(tittle==null){
                        errorMessage = " no se establecio el titulo del formulario";
                    }
                    if(name==null){
                        errorMessage = " no se establecio el nombre del formulario";
                    }
                    if(topic==null){
                        errorMessage = " no se establecio el tema del formulario";
                    }
                    addSemanticError(new ErrorIndigo(errorMessage,token,line,column));
                }
            }
        }else{
            errorMessage = "Error Fatal no se cerro correctamente el formulario";
            addSemanticError(new ErrorIndigo(errorMessage,token,line,column));
        }
        
    }
    
    private void addSemanticError(ErrorIndigo newError){
        ErrorCommands.errors.add(newError);
    }
}
