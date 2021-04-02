/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parameter;
import java.util.ArrayList;
/**
 *
 * @author camran1234
 */
public class BlockParameter {
    private String type;
    private ArrayList<Parameter> parameters = new ArrayList<>();
    private String error=null;
    
    public void addParameterList(ArrayList<Parameter> parameters){
        boolean blockCorrupted=false;
        
        System.out.println("Entrando metoo addParameterList");
        if(parameters.size()!=0){
            System.out.println("Obteniendo el tipo");
            type = parameters.get(0).getType();
        }
        
        for(Parameter parameter:parameters){
            System.out.println("Entrando ciclo");
            if(!parameter.getType().equalsIgnoreCase(type)){
                System.out.println("Dierente tipo se esperaba:"+parameter.getType()+", pero se enccontro: "+type);
                blockCorrupted=true;
            }
        }
        
        if(blockCorrupted){
            System.out.println("Error Lectura");
            error = "Error Lectura: Parametros no son del mismo tipo no se puede continuar Leyendo";
            error += "\n Parametros: ";
            for(Parameter parameter:parameters){
                error += "\n{ "+parameter.getType()+" : "+parameter.getType()+" }";
            }
        }else{
            System.out.println("Agregando parametros");
            this.parameters = parameters;
            error=null;
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
    public ArrayList<Parameter> getParameters(){
        ArrayList<Parameter> parameter = new ArrayList<>();
        for(int index=parameters.size()-1; index>=0; index--){
            parameter.add(parameters.get(index));
        }
        return parameter;
    }
    
    public String getType(){
        if(parameters.get(0)!=null){
            return parameters.get(0).getType();
        }
        return "";
    }
    
}
