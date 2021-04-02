/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.formats;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 *
 * @author camran1234
 */
public class BlockParameter {
    private String type;
    private ArrayList<Parameter> parameters = new ArrayList<>();
    private String error=null;
    
    public BlockParameter(){
        
    }
    
    public BlockParameter(String type, String text){
        parameters.add(new Parameter(type, text));
    }
    
    public BlockParameter(ArrayList<Parameter> parameter){
        this.parameters = parameter;
        System.out.println("START");
        for(int index=0; index<parameter.size(); index++){
            System.out.println(parameter.get(index).getContent());
        }
        System.out.println("END");
    }
    
    /**
     * Print the list of parameters
     * @param out 
     */
    public void printParameters(PrintWriter out){
        for(int indexParameter=0; indexParameter<parameters.size(); indexParameter++){
            out.println(parameters.get(indexParameter).getAsBlock() + ",");
        }
    }
    
    public void addParameterList(ArrayList<Parameter> parameters){
        boolean blockCorrupted=false;
        
        
        if(parameters.size()!=0){
            type = parameters.get(0).getContent();
        }
        
        for(Parameter parameter:parameters){
            if(!parameter.getType().equalsIgnoreCase(type)){
                blockCorrupted=true;
            }
        }
        
        if(blockCorrupted){
            error = "Error Lectura: Parametros no son del mismo tipo no se puede continuar Leyendo";
            error += "\n Parametros: ";
            for(Parameter parameter:parameters){
                error += "\n{ "+parameter.getType()+" : "+parameter.getType()+" }";
            }
        }else{
            this.parameters = parameters;
        }
    }
    
    /**
     * Check if the parameters are corrupted if yes pleas use a 
     * Return error method to get the info of the error
     * Return true if the error exists
     * Return false if the error dont exist
     * @return 
     */
    public boolean isCorrupted(){
        if(error==null){
            return false;
        }
        return true;
    }
    
    public String getError(){
        return error;
    }
    
}
