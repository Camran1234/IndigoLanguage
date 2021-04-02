/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sqform;

import com.mycompany.formats.Answer;
import com.mycompany.formats.BlockParameter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author camran1234
 */
public class PackageResult implements Serializable{
    private ArrayList<PrintResult> results;
    //An ArrayLisy inside the array list contains the cols not the rows
    public PackageResult(ArrayList<PrintResult> result){
        if(result!=null){
            System.out.println("Agregando resultados, tamano: "+result.size());
            results = result;
        }else{
            System.out.println("NUEVA LISTA RESULTADOS");
            results = new ArrayList<>();
        }
    }
    
    public PackageResult(){
        results = new ArrayList<>();
    }
    
    
    public ArrayList<PrintResult> getResults(){
        return results;
    }
    
    /**
     * print the elements of the List BLock parameter into String to read
     */
    public void printInfoAsBlock(ArrayList<BlockParameter> block, PrintWriter out){
        System.out.println("MARCA 2! + "+block.size());
        out.println("< !ini_respuesta : \"SqlPeticion\">");
        out.println("\t{\"bloque_parametros\":[");  
        for(int index=0; index<block.size(); index++){
            if(block.get(index)!=null){
                System.out.println("1");
                BlockParameter newBlock = block.get(index);
                if(index>0){
                    out.println(",");
                }
                System.out.println("2");
                out.println("{");
               newBlock.printParameters(out);
               //printRowsAndCols(out);
                out.println("}");
                System.out.println("3");
            }
        }
        
        
        out.println("]\n}");
        out.println("< fin_respuesta!>");
        System.out.println("MARCA 3!");
    }
    
    private void printRowsAndCols(PrintWriter out){
        if(this.results.size()>0){
            System.out.println("SIZE IN: "+results.size());
            for(int indexPrint=0; indexPrint<results.size(); indexPrint++){
                System.out.println("4");
                
                System.out.println("5");
            }
        }
    }
    
    public void printInfo(){
        System.out.println("Leyendo Archivo");
        ArrayList<String> headers;
        ArrayList<ArrayList<Answer>> row;
        for(PrintResult result:results){
            headers = result.getHeadersList();
            System.out.println("Headers");
            for(String header:headers){
                System.out.print(header+"\t");
            }
            System.out.println("");
            row = result.getRowsList();
            
            if(row.size()==0){
                System.out.println("Es 0");
            }else{
                System.out.println("Entro");
                System.out.println("Row.get(0) = "+row.get(0).size());                    
                System.out.println("Row size = "+row.size());
                
                for(int index=0; index<row.get(0).size();index++){
                    for(ArrayList<Answer> answer:row){
                        System.out.print(answer.get(index).getResult()+"\t");
                    }
                    System.out.println("\t---");
                }
            }
            
        }
        if(results.size()==0){
            System.out.println("No hay Datos");
        }
    }
}
