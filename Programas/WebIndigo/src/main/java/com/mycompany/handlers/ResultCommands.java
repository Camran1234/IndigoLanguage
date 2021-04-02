/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.handlers;

import com.mycompany.formats.Result;
import java.util.ArrayList;

/**
 *
 * @author camran1234
 */
public class ResultCommands {
    ArrayList<Result> results = new ArrayList<>();
    Result result;
    
    public void newResult(String token, int row, int column){
        result = new Result(token, row, column);
    }
    
    public void addNameCamp(String name){
        if(result!=null){
            result.setNameCamp(name);
        }
    }
    
    public void addIdForm(String id){
        if(result!=null){
            result.setIdForm(id);
        }
    }
    
    public void addAnswer(String user, String context){
        result.setAnswer(user, context);
    }
    
    public ArrayList<Result> getResults(){
        if(results!=null){
            return results;
        }else{
            return new ArrayList<>();
        }
        
    }
    
    public void close(String token, int row, int column){
        if(result!=null){
            results.add(result);
            result.setToken(token);
            result.setRow(row);
            result.setColum(column);
        }
    }
    
    //To do things, check if the save file is correct and do the form creators and upgrade the UI with interactions of exit session, etc
}
