/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.formats;

import com.mycompany.sqform.HelpState;
import java.util.ArrayList;

/**
 *
 * @author camran1234
 */
public class Result {
    private String nameCamp;
    private String idForm;
    
    private ArrayList<Answer> answers = new ArrayList<>();

    private String token;
    private int row;
    private int column;
    
    public Result(String token, int row, int column){
        this.token = token;
        this.row = row;
        this.column = column;
    }
    
    public Result(String idForm, String nameCamp, String user, String result){
        this.nameCamp = nameCamp;
        this.idForm = idForm;
        Answer answer = new Answer(user,result);
        answers.add(answer);
    }
    
    public Answer getAnswer(int index){
        return answers.get(index);
    }
    
    public boolean equalId(String compare, ArrayList<Component> components){
        String id = "";
        for(Component component:components){
            if(this.nameCamp.equals(component.getCampName())){
                id = component.getId();
                break;
            }
        }
        
        if(compare.equals(nameCamp) || compare.equals(id)){
            return true;
        }
        
        return false;
    }
    
    /**
     * Function to verify if the camp or id is equal to a component
     * @param compare
     * @param components
     * @return 
     */
    public boolean isEqualCampNorId(String compare, ArrayList<Component> components){
        for(Component component:components){
            if(compare.equals(component.getCampName()) || compare.equals(component.getId())){
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Answer> getAnswers(ArrayList<Integer> positions){
        ArrayList<Answer> newAnswers = new ArrayList<>();
        for(int index=0; index<positions.size(); index++){
            newAnswers.add(answers.get(positions.get(index)));
        }
        return newAnswers;
    }
    
    /**
     * Return the positions to grab the answers
     * @param operator
     * @param symbol
     * @return 
     */
    public void doOperation(String operator, String symbol, String logicOperator, ArrayList<HelpState> rowNumbers, ArrayList<Parameter> parameters){
        
        if(logicOperator==null){
            logicOperator="";
        }
        
        for(int indexAnswer=0; indexAnswer<answers.size(); indexAnswer++){
            Answer answer = answers.get(indexAnswer);
            String result = answer.getResult();
            //Operate as numbers
            if(isNumber(result) && isNumber(symbol)){
                int resultNumber = Integer.parseInt(result);
                int symbolNumber = Integer.parseInt(symbol);
                switch(operator){
                    case ">=":
                        if(logicOperator.equalsIgnoreCase("NOT")){
                            if(resultNumber >= symbolNumber){
                                rowNumbers.get(indexAnswer).setState(false);
                            }else{
                                rowNumbers.get(indexAnswer).setState(true);
                            }
                        }else if(logicOperator.equalsIgnoreCase("AND")){
                            if(resultNumber >= symbolNumber){
                                rowNumbers.get(indexAnswer).setState(true);
                            }else{
                                rowNumbers.get(indexAnswer).setState(false);
                            }
                        }else if(logicOperator.equalsIgnoreCase("OR") || logicOperator.equalsIgnoreCase("")){
                            if(resultNumber >= symbolNumber){
                                rowNumbers.get(indexAnswer).setState(true);
                            }
                        }
                        break;
                    case "<=":
                        if(logicOperator.equalsIgnoreCase("NOT")){
                            if(resultNumber <= symbolNumber){
                                rowNumbers.get(indexAnswer).setState(false);
                            }else{
                                rowNumbers.get(indexAnswer).setState(true);
                            }
                        }else if(logicOperator.equalsIgnoreCase("AND")){
                            if(resultNumber <= symbolNumber){
                                rowNumbers.get(indexAnswer).setState(true);
                            }else{
                                rowNumbers.get(indexAnswer).setState(false);
                            }
                        }else if(logicOperator.equalsIgnoreCase("OR") || logicOperator.equalsIgnoreCase("")){
                            if(resultNumber <= symbolNumber){
                                rowNumbers.get(indexAnswer).setState(true);
                            }
                        }
                        break;
                    case ">":
                        if(logicOperator.equalsIgnoreCase("NOT")){
                            if(resultNumber > symbolNumber){
                                rowNumbers.get(indexAnswer).setState(false);
                            }else{
                                rowNumbers.get(indexAnswer).setState(true);
                            }
                        }else if(logicOperator.equalsIgnoreCase("AND")){
                            if(resultNumber > symbolNumber){
                                rowNumbers.get(indexAnswer).setState(true);
                            }else{
                                rowNumbers.get(indexAnswer).setState(false);
                            }
                        }else if(logicOperator.equalsIgnoreCase("OR") || logicOperator.equalsIgnoreCase("")){
                            if(resultNumber > symbolNumber){
                                rowNumbers.get(indexAnswer).setState(true);
                            }
                        }
                        break;
                    case "<":
                        if(logicOperator.equalsIgnoreCase("NOT")){
                            if(resultNumber < symbolNumber){
                                rowNumbers.get(indexAnswer).setState(false);
                            }else{
                                rowNumbers.get(indexAnswer).setState(true);
                            }
                        }else if(logicOperator.equalsIgnoreCase("AND")){
                            if(resultNumber < symbolNumber){
                                rowNumbers.get(indexAnswer).setState(true);
                            }else{
                                rowNumbers.get(indexAnswer).setState(false);
                            }
                        }else if(logicOperator.equalsIgnoreCase("OR") || logicOperator.equalsIgnoreCase("")){
                            if(resultNumber < symbolNumber){
                                rowNumbers.get(indexAnswer).setState(true);
                            }
                        }
                        //Here
                        break;
                    case "=":
                        if(logicOperator.equalsIgnoreCase("NOT")){
                            if(resultNumber == symbolNumber){
                                rowNumbers.get(indexAnswer).setState(false);
                            }
                        }else if(logicOperator.equalsIgnoreCase("AND")){
                            if(resultNumber == symbolNumber){
                                rowNumbers.get(indexAnswer).setState(true);
                            }
                        }else if(logicOperator.equalsIgnoreCase("OR") || logicOperator.equalsIgnoreCase("")){
                            if(resultNumber == symbolNumber){
                                rowNumbers.get(indexAnswer).setState(true);
                            }
                        }
                        break;
                    case "!=":
                        if(logicOperator.equalsIgnoreCase("NOT")){
                            if(resultNumber != symbolNumber){
                                rowNumbers.get(indexAnswer).setState(false);
                            }
                        }else if(logicOperator.equalsIgnoreCase("AND")){
                            if(resultNumber != symbolNumber){
                                rowNumbers.get(indexAnswer).setState(true);
                            }
                        }else if(logicOperator.equalsIgnoreCase("OR") || logicOperator.equalsIgnoreCase("")){
                            if(resultNumber != symbolNumber){
                                rowNumbers.get(indexAnswer).setState(true);
                            }
                        }
                        break;
                }
            }else if(!isNumber(result) && !isNumber(symbol)){
                switch(operator){
                    case "=":
                    //Apply for String
                        if(logicOperator.equalsIgnoreCase("NOT")){
                            if(result.equalsIgnoreCase(symbol)){
                                rowNumbers.get(indexAnswer).setState(false);
                            }
                        }else if(logicOperator.equalsIgnoreCase("AND")){
                            if(result.equalsIgnoreCase(symbol)){
                                rowNumbers.get(indexAnswer).setState(true);
                            }
                        }else if(logicOperator.equalsIgnoreCase("OR") || logicOperator.equalsIgnoreCase("")){
                            if(result.equalsIgnoreCase(symbol)){
                                rowNumbers.get(indexAnswer).setState(true);
                            }
                        }
                        break;
                    case "!=":
                    //Apply for Strings
                        if(logicOperator.equalsIgnoreCase("NOT")){
                            if(!result.equalsIgnoreCase(symbol)){
                                rowNumbers.get(indexAnswer).setState(false);
                            }
                        }else if(logicOperator.equalsIgnoreCase("AND")){
                            if(!result.equalsIgnoreCase(symbol)){
                                rowNumbers.get(indexAnswer).setState(true);
                            }
                        }else if(logicOperator.equalsIgnoreCase("OR") || logicOperator.equalsIgnoreCase("")){
                            if(!result.equalsIgnoreCase(symbol)){
                                rowNumbers.get(indexAnswer).setState(true);
                            }
                        }
                        break;
                }
            }else{
                System.out.println("They are not the same types");
                parameters.add(new Parameter("Error","No se pudo realizar la comparacion cerca de: "+logicOperator+" "+operator+" "+symbol+", no son del mismo tipo"));
            }
        }
    }
    
    /**
     * Check if a String is a number
     * @param symbol
     * @return 
     */
    private boolean isNumber(String symbol){
        try {
            int number = Integer.parseInt(symbol);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getNameCamp() {
        return nameCamp;
    }

    public String getIdForm() {
        return idForm;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }
    
    /**
     * Method to add a list of answers to the already existing
     * @param moreAnswers 
     */
    public void addAnswers(ArrayList<Answer> moreAnswers){
        for(Answer answer:moreAnswers){
            answers.add(answer);
        }
    }

    public void setNameCamp(String nameCamp) {
        this.nameCamp = nameCamp;
    }

    public void setIdForm(String idForm) {
        this.idForm = idForm;
    }

    public void setAnswer(String idUser,String context) {
        this.answers.add(new Answer(idUser, context));
    }
    
    public void setToken(String token){
        this.token = token;
    }
    public void setRow(int row){
        this.row = row;
    }
    public void setColum (int column){
        this.column = column;
    }
}
