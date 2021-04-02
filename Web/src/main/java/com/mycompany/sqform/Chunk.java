/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sqform;

import com.mycompany.formats.Answer;
import java.util.ArrayList;

/**
 *
 * @author camran1234
 */
public class Chunk {
    private ArrayList<ArrayList<Answer>> answers = new ArrayList<>();
    
    public Chunk(ArrayList<ArrayList<Answer>> note){
        if(note.size()!=0){
            this.answers = note;
        System.out.println("New Chunk");
        System.out.println("Size chunk: "+note.size());
        System.out.println("Answers Size"+note.get(0).size());
        }
    }
    
    public ArrayList<ArrayList<Answer>> compareTo(Chunk chunk){
        ArrayList<ArrayList<Answer>> cleanList = new ArrayList<>();
        ArrayList<ArrayList<Answer>> compareList = chunk.getAnswers();
        
        //Getting the similars rows
        for(Answer answer:answers.get(0)){
            ArrayList<Answer> auxiliarList = new ArrayList<>();
            for(Answer compareAnswer:compareList.get(0)){
                if(answer.equals(compareAnswer)){
                    auxiliarList.add(answer);
                    break;
                }
            }
            cleanList.add(auxiliarList);
        }
        
        return cleanList;
    }
    
    public ArrayList<ArrayList<Answer>> cleanResults(Chunk chunk){
        ArrayList<ArrayList<Answer>> compareList = chunk.getAnswers();
        ArrayList<ArrayList<Answer>> cleanList = new ArrayList<>();
        ArrayList<Answer> answersA = new ArrayList<>();
        //Getting the similars rows+
        
        for(int indexArray=0; indexArray<answers.size(); indexArray++){
            System.out.println("Ciclo: "+indexArray);
            answersA = new ArrayList<>();
            ArrayList<Answer> oldAnswers = answers.get(indexArray);
            ArrayList<Answer> newAnswers = compareList.get(indexArray);
            for(int indexOld=0; indexOld<oldAnswers.size(); indexOld++){
                System.out.println("Entro 1 "+oldAnswers.get(indexOld).getResult());
            }
            for(int indexNew=0; indexNew<newAnswers.size(); indexNew++){
                System.out.println("Entro 2: "+newAnswers.get(indexNew).getResult());
                if(!answersA.contains(newAnswers.get(indexNew))){
                    
                    answersA.add(newAnswers.get(indexNew));
                }
            }
            cleanList.add(answersA);
        }
                
        System.out.println("Retirado");
        return cleanList;
    }
    
    
    public ArrayList<ArrayList<Answer>> getAnswers(){
        return answers;
    }
    
}
