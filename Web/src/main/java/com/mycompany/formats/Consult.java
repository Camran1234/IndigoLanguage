/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.formats;

import java.util.ArrayList;

/**
 *
 * @author camran1234
 */
public class Consult {
    private String idForm;
    private ArrayList<String> camps = new ArrayList<>();
    private ArrayList<Comparation> comparations = new ArrayList<>();
    private String consult;
    private boolean activated=false;
    private String token;
    private int line;
    private int column;
    
    public Consult(){
        activated = true;
    }

    public void setIdForm(String idForm) {
        if(activated)
        {
            this.idForm = idForm;
        }
    }
    
    public void setConsult(String consult){
        this.consult = consult;
    }
    
    public void setNewComparation(Comparation comparation){
        if(activated){
            comparations.add(comparation);
        }
    }
    
    public void setNewCamp(String camp){
        if(activated){
            camps.add(camp);
        }
    }
    
    public String getIdForm(){
        return idForm;
    }
    
    public String getConsult(){
        return consult;
    }
    
    public ArrayList<String> getCamps(){
        return camps;
    }
    
    public ArrayList<Comparation> getComparations(){
        return comparations;
    }
    
    public void close(String token,int line,int column){
        this.token = token;
        this.line = line;
        this.column = column;
        activated=false;
    }
    
}
