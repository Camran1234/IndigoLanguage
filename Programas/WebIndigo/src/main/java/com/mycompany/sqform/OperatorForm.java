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
import com.mycompany.formats.Consult;
import com.mycompany.formats.Parameter;
import com.mycompany.formats.Result;
import com.mycompany.handlers.ComponentCommands;
import com.mycompany.handlers.ConsultCommands;
import java.util.ArrayList;

/**
 *
 * @author camran1234
 */
public class OperatorForm {
    private ArrayList<Component> components;
    private ArrayList<PrintResult> printResults = new ArrayList<>();
    private ArrayList<Result> results = new ArrayList<>();
    private ArrayList<Consult> consults;
    private ArrayList<String> rows = new ArrayList<>();
    private ArrayList<BlockParameter> block = new ArrayList<>();
    
    public OperatorForm(ArrayList<Component> components, ConsultCommands consultCommands, ArrayList<Result> results, ArrayList<BlockParameter> block){
        this.components = components;
        this.consults = consultCommands.getConsults();
        this.results = results;
        this.block = block;
        createSqResults();
    }
    
    public ArrayList<PrintResult> getPrintResults(){
        System.out.println("VERIFICANDO SI ES NULO");
        if(printResults!=null){
            System.out.println("No hay problema");
        }else{
            System.out.println("HAY PROBLEMAS AHAHHAHA");
        }
        
        ArrayList<Parameter> parameters = new ArrayList<>();
        System.out.println("SIZE: "+printResults.size());
        for(int indexResult=0; indexResult<printResults.size(); indexResult++){
            parameters = new ArrayList<>();
            ArrayList<String> headers = printResults.get(indexResult).getHeadersList();
            ArrayList<ArrayList<Answer>> rows = printResults.get(indexResult).getRowsList();
            System.out.println("ANTES DEL PRIMER FOR");
            for(int indexHeaders=0; indexHeaders<headers.size(); indexHeaders++){
                parameters.add(new Parameter("Row",headers.get(indexHeaders)));
            }
            System.out.println("DESPUES DEL PRIMER FOR");
            block.add(new BlockParameter(parameters));
            System.out.println("ANTES DEL SEGUNDO FOR");
            for(int indexRows=0; indexRows<rows.size(); indexRows++){
                parameters = new ArrayList<>();
                ArrayList<Answer> answer = rows.get(indexRows);
                for(int indexAnswer=0; indexAnswer<answer.size(); indexAnswer++){
                    parameters.add(new Parameter("Col", answer.get(indexAnswer).getResult()));
                }
                block.add(new BlockParameter(parameters));
            }
            System.out.println("DESPUES DEL SEGUNDO FOR");
        }
        return printResults;
    }
    
    /**
     * Method to generate the results to print to the table
     */
    private void createSqResults(){
        PrintResult printResult;
        String idForm;
        ArrayList<Component> auxComponents = new ArrayList<>();
        for(Consult consult:consults){
            //Initialiaze all variables
            idForm = consult.getIdForm();
            printResult = new PrintResult();
            auxComponents = new ArrayList<>();
            //Initialize the components of the form
            for(Component component:components){
                if(component.getFormName().equals(idForm)){
                    auxComponents.add(component);
                }
            }
            if(auxComponents.size()>0){
                ArrayList<String> camps = consult.getCamps();
                if(consult.getCamps().size()==0){
                    getCampNames(auxComponents, camps);
                }
                System.out.println("Before consult.getCamps().size()=="+camps.size());
                //Adding the headers
                for(String campName:camps){
                    printResult.setNewHeader(campName);
                }
                //Getting just the camps names
                ArrayList<String> campsParsed = new ArrayList<>();
                for(Component component:components){
                    for(String camp: camps){
                        if(component.getCampName().equals(camp) || component.getId().equals(camp)){
                            campsParsed.add(component.getCampName());
                        }
                    }
                }

                ArrayList<Result> resultsTo = new ArrayList<>();
                for(String name:campsParsed){
                    //Getting camps results
                    for(Result result:results){
                        if(result.getNameCamp().equals(name) && result.getIdForm().equals(idForm)){
                            resultsTo.add(result);
                        }

                    }
                }
                if(campsParsed.size()==0){
                    for(Result result:results){
                        if(result.getIdForm().equals(idForm)){
                            resultsTo.add(result);
                        }
                    }
                }
                this.addRows(printResult, consult, resultsTo, auxComponents, camps);
                printResults.add(printResult);
            }else{
                block.add(new BlockParameter("Warning","Warning: No se encontro ningun campo para el formulario "+idForm));
            }
        }
        
        
    }
    
    
    /**
     * Method to get the comparations forms
     * @param resultPrint
     * @param consult
     * @param results
     * @param components
     * @param names 
     */
    private void addRows(PrintResult resultPrint, Consult consult, ArrayList<Result> results, ArrayList<Component> components, ArrayList<String> names){
        LogicParser logicParser = new LogicParser();
        if(consult.getComparations().size()==0){
            System.out.println("Comparaciones son 0");
            logicParser.setAllResults(results,names);
        }else{
            System.out.println("Comparaciones no son 0");
            logicParser.stablishRows( results, consult.getComparations(), components, names, block);
        }
        resultPrint.setRowList(logicParser.finalResult());
    }
    
    private ArrayList<String> getCampNames(ArrayList<Component> components, ArrayList<String> camps){
        for(Component component:components){                       
            System.out.println("Adding campName: "+camps);
            switch(component.getClassName()){                
                case "CAMPO_TEXTO":
                    camps.add(component.getCampName());
                    break;                        
                case "AREA_TEXTO":                    
                    camps.add(component.getCampName());
                    break;                                            
                case "CHECKBOX":                  
                    camps.add(component.getCampName());
                    break;                                            
                case "RADIO":                    
                    camps.add(component.getCampName());
                    break;                                            
                case "COMBO":                    
                    camps.add(component.getCampName());
                    break;                       
                }        
        }
        return camps;
    }
            
    
}
