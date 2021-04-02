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
    public ArrayList<Integer> doOperation(String operator, String symbol, String logicOperator){
        ArrayList<Integer> positions = new ArrayList<>();
        if(logicOperator==null){
            logicOperator="";
        }
        for(Answer answer:answers){
            String result = answer.getResult();
            //Operate as numbers
            if(isNumber(result) && isNumber(symbol)){
                int resultNumber = Integer.parseInt(result);
                int symbolNumber = Integer.parseInt(symbol);
                switch(operator){
                    case ">=":
                        if(logicOperator.equalsIgnoreCase("NOT")){
                            if(resultNumber <= symbolNumber){
                                positions.add(Integer.valueOf(answers.indexOf(answer)));
                            }
                        }else{
                            if(resultNumber >= symbolNumber){
                                positions.add(Integer.valueOf(answers.indexOf(answer)));
                            }
                        }
                        break;
                    case "<=":
                        if(logicOperator.equalsIgnoreCase("NOT")){
                            if(resultNumber >= symbolNumber){
                                positions.add(Integer.valueOf(answers.indexOf(answer)));
                            }
                        }else{
                            if(resultNumber <= symbolNumber){
                                positions.add(Integer.valueOf(answers.indexOf(answer)));
                            }
                        }
                        break;
                    case ">":
                        if(logicOperator.equalsIgnoreCase("NOT")){
                            if(resultNumber < symbolNumber){
                                positions.add(Integer.valueOf(answers.indexOf(answer)));
                            }
                        }else{
                            if(resultNumber > symbolNumber){
                                positions.add(Integer.valueOf(answers.indexOf(answer)));
                            }
                        }
                        break;
                    case "<":
                        if(logicOperator.equalsIgnoreCase("NOT")){
                            if(resultNumber > symbolNumber){
                                positions.add(Integer.valueOf(answers.indexOf(answer)));
                            }
                        }else{
                            if(resultNumber < symbolNumber){
                                positions.add(Integer.valueOf(answers.indexOf(answer)));
                            }
                        }
                        //Here
                        break;
                    case "=":
                        if(logicOperator.equalsIgnoreCase("NOT")){
                            if(resultNumber != symbolNumber){
                                positions.add(Integer.valueOf(answers.indexOf(answer)));
                            }
                        }else{
                            if(resultNumber == symbolNumber){
                                positions.add(Integer.valueOf(answers.indexOf(answer)));
                            }
                        }
                        break;
                    case "!=":
                        if(logicOperator.equalsIgnoreCase("NOT")){
                            if(resultNumber == symbolNumber){
                                positions.add(Integer.valueOf(answers.indexOf(answer)));
                            }
                        }else{
                            if(resultNumber != symbolNumber){
                                positions.add(Integer.valueOf(answers.indexOf(answer)));
                            }
                        }
                        break;
                }
            }else if(!isNumber(result) && !isNumber(symbol)){
                switch(operator){
                    case "=":
                    //Apply for String
                        if(logicOperator.equalsIgnoreCase("NOT")){
                            if(!result.equalsIgnoreCase(operator)){
                                positions.add(Integer.valueOf(answers.indexOf(answer)));
                            }
                        }else{
                            if(result.equalsIgnoreCase(symbol)){
                                System.out.println("Result: "+result + " SYmbolo: "+symbol+"Numero: "+answers.indexOf(answer));
                                positions.add(Integer.valueOf(answers.indexOf(answer)));
                            }
                        }
                        break;
                    case "!=":
                    //Apply for Strings
                        if(logicOperator.equalsIgnoreCase("NOT")){
                            if(result.equalsIgnoreCase(operator)){
                                positions.add(Integer.valueOf(answers.indexOf(answer)));
                            }
                        }else{
                            if(!result.equalsIgnoreCase(symbol)){
                                positions.add(Integer.valueOf(answers.indexOf(answer)));
                            }
                        }
                        break;
                }
            }else{
                System.out.println("They are not the same types");
            }
        }
        return positions;
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
