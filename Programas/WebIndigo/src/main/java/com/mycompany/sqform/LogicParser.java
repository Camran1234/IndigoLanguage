/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sqform;

import com.mycompany.formats.Answer;
import com.mycompany.formats.Comparation;
import com.mycompany.formats.Component;
import com.mycompany.formats.ErrorHandler;
import com.mycompany.formats.Result;
import com.mycompany.handlers.ErrorCommands;
import java.util.ArrayList;

/**
 *
 * @author camran1234
 */
public class LogicParser {
    private ArrayList<ArrayList<Answer>> rows = new ArrayList<>();
    private ArrayList<Chunk> chunks = new ArrayList<>();
    
    public void stablishRows(PrintResult printer, ArrayList<Result> results, ArrayList<Comparation> comparations, ArrayList<Component> components, ArrayList<String> names){
        rows = new ArrayList<>();
        ArrayList<Chunk> chunksAux = new ArrayList<>();
        ArrayList<Integer> numbers = new ArrayList<>();
        
        ArrayList<Result> auxResults = new ArrayList<>();
        
        //We order the results as the name is putted
        for(String name:names){
            for(Result result:results){
                System.out.println("ES EL CAMPO: "+name+" Result: "+result.getNameCamp());
                if(result.equalId(name, components)){
                    System.out.println("");
                    auxResults.add(result);
                    break;
                }
            }
        }
        
        for(Comparation comparation: comparations){
            numbers = new ArrayList<>();
            String operator = comparation.getOperator();
            String campId = comparation.getCamporId();
            String symbol = comparation.getCompareSym();
            System.out.println("Operator: "+operator);
            System.out.println("Campo: "+campId);
            System.out.println("Symbol: "+symbol);
            for(Result result:auxResults){            
                if(result.equalId(campId, components)){ 
                    numbers = result.doOperation(operator, symbol, comparation.getLogicOperator());                    
                    System.out.println("Agregando nuevo numero Index: "+numbers);
                    break;
                }                
            }
            for(Result result:auxResults){
                rows.add(result.getAnswers(numbers));
            }
            chunksAux.add(new Chunk(rows));
        }
        rows = new ArrayList<>();
        if(chunksAux.size()==1){
            System.out.println("Agregando index==0");
            rows = chunksAux.get(0).getAnswers();
        }
        for(int index=0; index<chunksAux.size(); index++){
            System.out.println("Tamano chunksAux: "+chunksAux.size());
            if(index<chunksAux.size()-1){
                System.out.println("Agregando indexSuperior en stablishRows");
                rows = chunksAux.get(index).cleanResults(chunksAux.get(index+1));
            }
        }
        chunks.add(new Chunk(rows));
    }
    
    /**
     * Set all rows of the camps
     * @param results 
     */
    public void setAllResults(ArrayList<Result> results, ArrayList<String> names){
        ArrayList<ArrayList<Answer>> auxRows= new ArrayList<>();
        ArrayList<Answer> auxiliar;
        ArrayList<Result> auxResults = new ArrayList<>();
        
        //We order the results as the name is putted
        for(String name:names){
            for(Result result:results){
                if(result.getNameCamp().equals(name)){
                    auxResults.add(result);
                    break;
                }
            }
        }
        
        for(Result result:auxResults){
                System.out.println("Adding new Result");
                auxRows.add(result.getAnswers());
            }
        
        chunks.add(new Chunk(auxRows));
    }
    
    public ArrayList<ArrayList<Answer>> finalResult(){
        try {
            rows = new ArrayList<>();
            if(chunks.size()==1){
                System.out.println("Chunks size are 1");
                rows = chunks.get(0).getAnswers();
            }else if(chunks.size()>1){
                System.out.println("Chunks size are more than 1");
                for(int index=0; index<chunks.size(); index++){
                    if(chunks.get(index+1)!=null){
                        rows = chunks.get(index).cleanResults(chunks.get(index+1));
                    }
                }
            }

            return rows;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private boolean isNumber(String symbol){
        try {
            int number = Integer.parseInt(symbol);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
