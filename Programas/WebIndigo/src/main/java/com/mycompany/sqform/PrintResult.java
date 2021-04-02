/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sqform;

import com.mycompany.formats.Answer;
import com.mycompany.formats.BlockParameter;
import com.mycompany.formats.Parameter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author camran1234
 */
public class PrintResult implements Serializable{
    private String idForm;
    private String nameConsult;
    private ArrayList<String> headers = new ArrayList<>();
    private ArrayList<ArrayList<Answer>> rows= new ArrayList<>();
    
    /**
     * Write the content as block
     * @param out 
     */
    public void writeAsBlock(PrintWriter out){
        ArrayList<Parameter> parameterRows = new ArrayList<>();
        ArrayList<Parameter> parameterCols = new ArrayList<>();
        for(String header:headers){
            parameterRows.add(new Parameter("Row",header));
        }
        for(int indexRows=0; indexRows<rows.size(); indexRows++){
            ArrayList<Answer> answer = rows.get(indexRows);
            for(int indexAnswer=0; indexAnswer<answer.size(); indexAnswer++){
                parameterCols.add(new Parameter("Col",answer.get(indexAnswer).getResult()));
            }
        }
        BlockParameter block;
        if(parameterRows.size()>0){
            block = new BlockParameter(parameterRows);
            block.printParameters(out);
        }
        if(parameterCols.size()>0){
            block=new BlockParameter(parameterCols);
            block.printParameters(out);
        }
    }
    
    public void setNewHeader(String header){
        headers.add(header);
    }
    
    public void setIdForm(String idForm){
        this.idForm = idForm;
    }
    
    public void setNameConsult(String name){
        this.nameConsult = name;
    }
    
    /**
     * Add a new Element to the actual row
     * @param row 
     */
    public void setRowList(ArrayList<ArrayList<Answer>> row){
        this.rows = row;
    }
    
    
    public ArrayList<String> getHeadersList(){
        return headers;
    }
    
    public ArrayList<ArrayList<Answer>> getRowsList(){
        return rows;
    }
    
    public int getCols(){
        return headers.size();
    }
    
    public int getRows(){
        return rows.size();
    }
    
}
