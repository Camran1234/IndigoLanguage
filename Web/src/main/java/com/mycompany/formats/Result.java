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
