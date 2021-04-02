/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sqform;

import com.mycompany.formats.Answer;
import com.mycompany.formats.BlockParameter;
import com.mycompany.formats.Comparation;
import com.mycompany.formats.Component;
import com.mycompany.formats.ErrorHandler;
import com.mycompany.formats.Parameter;
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
    
    public void stablishRows( ArrayList<Result> results, ArrayList<Comparation> comparations, ArrayList<Component> components, ArrayList<String> names
    , ArrayList<BlockParameter> block){
        try {
            rows = new ArrayList<>();
        ArrayList<Parameter> parameters = new ArrayList<>();
        
        //Result is the column
        ArrayList<Result> auxResults = new ArrayList<>();
        ArrayList<HelpState> positionsRows = new ArrayList<>();
        ArrayList<ArrayList<Answer>> answers = new ArrayList<>();
        //We order the results as the name is putted
        for(String name:names){
            for(Result result:results){
                if(result.equalId(name, components)){
                    System.out.println("");
                    auxResults.add(result);
                    answers.add(result.getAnswers());
                    break;
                }
            }
        }
        //Getting the rows numbers
        if(auxResults.size()>0){
            if(auxResults.get(0).getAnswers().size()>0){
                for(int indexRows=0; indexRows<auxResults.get(0).getAnswers().size(); indexRows++){
                    positionsRows.add(new HelpState(indexRows));
                }
            }
        }
        
        if(comparations.size()>0){
            //Making the comparations
            //We make the for from the top one to last one because of the pile that receive the parameters in the syntax analyzer
            for(int indexComparation=comparations.size()-1; indexComparation>=0; indexComparation--){
                Comparation comparation = comparations.get(indexComparation);
                String operator = comparation.getOperator();
                String camp = comparation.getCamporId();
                String symbol = comparation.getCompareSym();
                String logicOperator = comparation.getLogicOperator();
                for(int indexResult=0; indexResult<auxResults.size(); indexResult++){
                    Result result = auxResults.get(indexResult);
                    if(result.equalId(camp, components)){
                        result.doOperation(operator, symbol, logicOperator, positionsRows, parameters);
                    }
                }
            }
        }else{
            //We add all the rows
            for(int indexPositions=0; indexPositions<positionsRows.size(); indexPositions++){
                positionsRows.get(indexPositions).setState(true);
            }
        }
        ArrayList<ArrayList<Answer>> finalAnswers = new ArrayList<>();
        //Adding errors if there are ones
        if(parameters.size()>0){
            block.add(new BlockParameter(parameters));
        }else{
            //We add the rows
            for(int indexRows=0; indexRows<auxResults.size(); indexRows++){
                ArrayList<Answer> auxAnswers = auxResults.get(indexRows).getAnswers();
                ArrayList<Answer> auxiliar = new ArrayList<>();
                for(int indexAnswers=0; indexAnswers<auxAnswers.size(); indexAnswers++){
                    //if its true add
                    if(positionsRows.get(indexAnswers).getState()){
                        auxiliar.add(auxResults.get(indexRows).getAnswer(indexAnswers));
                    }
                }
                finalAnswers.add(auxiliar);
            }
        }
        //adding the final result 
        rows = finalAnswers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        /*
        //We make the comparations
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
        chunks.add(new Chunk(rows));*/
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
        rows = auxRows;
    }
    
    public ArrayList<ArrayList<Answer>> finalResult(){
        if(rows!=null){
            return rows;
        }else{
            rows = new ArrayList<>();
            return rows;
        }
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
