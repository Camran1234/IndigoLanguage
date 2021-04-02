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
public class ParameterHandler {
    ArrayList<Parameter> parameters = new ArrayList<>();
    ArrayList<BlockParameter> block = new ArrayList<>();
    ArrayList<String> text = new ArrayList<>();
        ArrayList<String> errors = new ArrayList<>();
        ArrayList<String> rows = new ArrayList<>();
        ArrayList<String> col = new ArrayList<>();
        ArrayList<String> warnings = new ArrayList<>();
        ArrayList<ArrayList<String>> cols = new ArrayList<>();

    public ArrayList<Parameter> getParameters() {
        return parameters;
    }

    public ArrayList<BlockParameter> getBlock() {
        return block;
    }

    public ArrayList<String> getText() {
        return text;
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public ArrayList<String> getRows() {
        return rows;
    }

    public ArrayList<ArrayList<String>> getCol() {
        return cols;
    }

    public ArrayList<String> getWarnings() {
        return warnings;
    }
        
        
        
        
    public void addParameter(String type, String content){
        Parameter parameter = new Parameter();
        parameter.setContent(content);
        parameter.setType(type);
        parameters.add(parameter);
        System.out.println("Parametro Agregado: "+content+", Tipo: "+type);
    }

    public void startNewListParameters(){
        System.out.println("Empezando nueva lista");
        if(parameters.size()!=0){
            System.out.println("Agregando");
            BlockParameter blockParameter = new BlockParameter();
            blockParameter.addParameterList(parameters);
            System.out.println("Enserio agregando");
            block.add(blockParameter);
        }
        parameters = new ArrayList<>();
    }
    
    public String getAsStringLine(){
        String result="";
        System.out.println("Entrando, size: "+block.size());
        for(int indexA=0; indexA<block.size(); indexA++){
            BlockParameter newBlock = block.get(indexA);
            System.out.println("Viendo si esta corrupto");
            if(!newBlock.isCorrupted()){
                System.out.println("No corrupto");
                ArrayList<Parameter> parameters = newBlock.getParameters();                    
                col = new ArrayList<>();
                for(int index=0; index<parameters.size(); index++){
                    Parameter parameter = parameters.get(index);
                    switch(parameter.getType()){
                        case "TEXT":
                            System.out.println("Agregando texto");
                            text.add(parameter.getContent());
                            break;
                        case "WARNING":
                            System.out.println("Agregando Wraning");
                            warnings.add(parameter.getContent());
                            break;
                        case"ERROR":
                            System.out.println("Agregando Error");
                            errors.add(parameter.getContent());
                            break;
                        case"ROW":
                            System.out.println("Agregando Row");
                            rows.add(parameter.getContent());
                            break;                            
                        case "COL":
                            System.out.println("Agregando Col: "+parameter.getContent());
                            col.add(parameter.getContent());
                            break;
                    }
                }
                if(col.size()!=0){
                    cols.add(col);
                }
            }else{
                System.out.println("Es corrupto");
                errors.add(newBlock.getError());
            }
        }
        
        for(String answer:text){
            result+=answer;
        }
        for(String answer:errors){
            result+=answer;
        }
        for(String answer:warnings){
            result+=answer;
        }
        
        return result;
    }
    
}
