/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sqform;

import WebSocket.dBIndigo;
import com.google.gson.Gson;
import com.mycompany.formats.BlockParameter;
import com.mycompany.formats.Component;
import com.mycompany.formats.ErrorIndigo;
import com.mycompany.formats.Form;
import com.mycompany.formats.Parameter;
import com.mycompany.handlers.ConsultCommands;
import com.mycompany.handlers.ErrorCommands;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

/**
 *
 * @author camran1234
 */
public class AnalysisPetition {
    private ArrayList<Form> forms;
    private ArrayList<Component> components;
    
    
    /**
     * Method to read the sq code sent it
     */
    public void readText(String entryText, ArrayList<BlockParameter> block, PrintWriter out){
        ErrorCommands errors;
        ConsultCommands consultCommands;
        ArrayList<Parameter> parameters = new ArrayList<>();
        try {
            parameters.add(new Parameter("Text","Leyendo"));
            Reader reader = new StringReader(entryText);
            parameters.add(new Parameter("Text","Analizador Lexico"));
            SqLexic lexic = new SqLexic(reader);
            parameters.add(new Parameter("Text","Analizador Sintactico"));
            SqSyntax syntax = new SqSyntax(lexic);
            syntax.parse();
            parameters.add(new Parameter("Text","Ya parseado"));
            errors = syntax.getErrorCommands();
            consultCommands = syntax.getConsultCommands();
            if(errors.haveErrors()){
                //Handle error
                System.out.println("Error Station:");
                ArrayList<ErrorIndigo> messages = errors.getErrors();
                ArrayList<Parameter> newParameter = new ArrayList<>();
                for(ErrorIndigo error:errors.getErrors()){
                    System.out.println("Error: "+error.getMessage());
                    if(block!=null){
                        newParameter.add(new Parameter("Error",error.getMessage()));
                    }
                }
                block.add(new BlockParameter(parameters));
            }else{
                parameters.add(new Parameter("Text","Ya parseado"));
                System.out.println("No ERRORS AHAHAHAHHA");
            dBIndigo db = new dBIndigo(block);
            OperatorForm operator = new OperatorForm(db.getComponents(), consultCommands, db.getResults(), block);
            if(operator==null){
                System.out.println("EL OPERATOR ES NULL");
            }
            PackageResult results = new PackageResult(operator.getPrintResults());
                System.out.println("MARCA!1");
            //adding the results
            results.printInfoAsBlock(block, out);
            System.out.println("Is HERE");
            //results.printInfo();   
            }
        } catch (Exception e) {
            parameters.add(new Parameter("Error",e.getMessage()));
            System.out.println("Error Fatal: "+e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    
}
