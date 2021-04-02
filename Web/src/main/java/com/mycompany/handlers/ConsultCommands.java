/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.handlers;

import com.mycompany.formats.Comparation;
import com.mycompany.formats.Consult;
import java.util.ArrayList;

/**
 *
 * @author camran1234
 */
public class ConsultCommands {
    ArrayList<Consult> consults = new ArrayList<>();
    Consult consult;
    Comparation comparation;
    
    
    
    public void addNewConsult(){
        System.out.println("Opening new Consult");
         consult = new Consult();
    }
    
    public void close(String token, int line,int column){
        consult.close(token, line, column);
        consults.add(consult);
    }
    
    public void addIdForm(String idForm){
        System.out.println("In IdForm adding "+idForm);
        
        consult.setIdForm(idForm);
    }
    
    public void addCamp(String camp){
        System.out.println("Adding new COnsult: "+camp);
        consult.setNewCamp(camp);
    }    
    
    public void addConsult(String consult){
        this.consult.setConsult(consult);
    }
    
    public void addComparation(String camp, String operator, String symbol){
        System.out.println("Adding normal Comparation");
        comparation = new Comparation();
        comparation.setCamporId(camp);
        comparation.setOperator(operator);
        comparation.setCompareSym(symbol);
        System.out.println("Symbol IN CONSULT COMMANDS: "+symbol);
        consult.setNewComparation(comparation);
    }
    public void addLogicComparation(String logicOperator,String camp, String operator, String symbol){
        System.out.println("Adding logical Comparation");
        comparation = new Comparation();
        comparation.setLogiOperator(logicOperator);
        comparation.setCamporId(camp);
        comparation.setOperator(operator);
        comparation.setCompareSym(symbol);
        consult.setNewComparation(comparation);
    }
    
    public ArrayList<Consult> getConsults(){
        return this.consults;
    }
}
