/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.handlers;

import java.util.ArrayList;
import com.mycompany.formats.ErrorIndigo;

/**
 *
 * @author camran1234
 */
public class ErrorCommands {
    public static ArrayList<ErrorIndigo> errors = new ArrayList<>();
    
    /**
     * True for open a new ErrorCommands
     * False for open an already existin static ErrorCommands
     * @param type 
     */
    public ErrorCommands(boolean type){
        if(type){
            errors = new ArrayList<>();
        }
    }
    
    public void addError(ErrorIndigo error){
        System.out.println("Error Agregado: "+error.getMessage());
        errors.add(error);
    }
    
    /**
     * Method to get a list of the errors
     * Ideal to stablish error tables
     * @return 
     */
    public String[] getInfoErrors(){
        String[] thusErrors = new String[errors.size()];
        for(int indexError=0; indexError<errors.size(); indexError++){
            thusErrors[indexError] = errors.get(indexError).getMessage();
        }
        return thusErrors;
    }
    
    public ArrayList<ErrorIndigo> getErrors(){
        return errors;
    }
    
    /**
     * Method to know if exists errors
     * @return 
     */
    public boolean haveErrors(){
        System.out.println("Tamano errores: "+errors.size());
        if(errors.size()==0){
            return false;
        }else{
            return true;
        }
    }
}
