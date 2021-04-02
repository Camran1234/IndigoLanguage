/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.handlers;

import com.mycompany.formats.Component;
import com.mycompany.formats.ErrorIndigo;
import com.mycompany.formats.Form;
import com.mycompany.indigo.Analysis;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author camran1234
 */
public class FormCommands {
    ArrayList<Form> createList = new ArrayList<>();
    ArrayList<Form> modifyList = new ArrayList<>();
    ArrayList<String> deleteList = new ArrayList<>();
    Form form;

    public ArrayList<Form> getCreateList() {
        return createList;
    }

    public ArrayList<Form> getModifyList() {
        return modifyList;
    }

    public ArrayList<String> getDeleteList() {
        return deleteList;
    }
    
    
    

    
    public void checkForErrors(){
        for(Form form2:createList){
            form2.checkForSemanticErrors();
        }
        for(Form form1:modifyList){
            form1.checkForSemanticErrors();
        }
    }
    
    
    public void delete(String id){
        deleteList.add(id);
    }
    public void openModify(String token, int line, int column){
        form = new Form(token, line, column,"modify");
    }
    public void closeModify(String token, int line, int column){
        modifyList.add(form);
        form.close(token, line, column);
    }
    public void start(String token, int line, int column){
        form = new Form(token, line, column,"new");
    }
    public void close(String token, int line, int column){
        createList.add(form);
        form.close(token, line, column);
    }
    public void addId(String id){
        form.setId(id);
    }
    public void addTittle(String tittle){
        form.setTittle(tittle);
    }
    public void addName(String name){
        form.setName(name);
    }
    public void addTopic(String topic){
        form.setTopic(topic);
    }
    public void addUser(String user){
        form.setUser(user);
    }
    public void addVisibleText(String visibility){
        form.setVisibility(visibility);
    }
    
    public void addUserCreator(){
        for(Form form:createList){
            form.setUser(new Analysis().getLoggedUser());
        }
    }
    
    public void addDate(String date){
        try{
            Date actualDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            form.setDate(actualDate);
        }catch(Exception ex){
            String error = ex.getMessage();
            ErrorCommands.errors.add(new ErrorIndigo(error, form.getToken(), form.getLine(), form.getColumn()));
	}
    }
    
    public ArrayList<Form> toArrayList(){
        return createList;
    }
}
