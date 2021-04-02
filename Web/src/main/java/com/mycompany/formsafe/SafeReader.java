/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.formsafe;

import com.mycompany.formats.BlockParameter;
import com.mycompany.formats.Component;
import com.mycompany.formats.ErrorIndigo;
import com.mycompany.formats.Form;
import com.mycompany.formats.Parameter;
import com.mycompany.formats.User;
import java.io.FileInputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import com.mycompany.handlers.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author camran1234
 */
public class SafeReader {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Form> forms = new ArrayList<>();
    private ArrayList<Component> components = new ArrayList<>();
    private ResultCommands resultCommands;
    private boolean errors=false;
    private final String relativePathForm="Resources/form.sqf";
    private final String relativePathUser="Resources/user.sqf";

    public ArrayList<User> getUsers() {
        return users;
    }

    public boolean haveErrors(){
        return errors;
    }
    
    public ArrayList<Form> getForms() {
        return forms;
    }
    
    public ArrayList<Component> getComponents(){
        return components;
    }

    public ResultCommands getResultCommands() {
        return resultCommands;
    }
    
    
    
    /**
     * To grab all the info from the Components
     */
    public void readData(PrintWriter out){
        errors=false;
        this.readForms(out);
        this.readUsers(out);
    }
    
    /**
     * To grab all the info from the Components
     */
    public void readData(ArrayList<BlockParameter> block){
        errors=false;
        this.readForms(block);
        this.readUsers(block);
    }
    
    /**
     * Read the Data in the Form file
     */
    private void readForms(PrintWriter out){
        //Reading file
        String text;
        File directory = new File(relativePathUser);
        System.out.println(directory.getAbsolutePath());
        StringBuffer fileContent = new StringBuffer();
        try(BufferedReader br = new BufferedReader(new FileReader(this.relativePathForm))){
            //Reading file
            String sCurrentLine;
            while((sCurrentLine=br.readLine())!=null){
                fileContent.append(sCurrentLine).append("\n");
            }
            text = fileContent.toString();
            Reader reader = new StringReader(text);
            SqFormLexic lexic = new SqFormLexic(reader);
            SqFormSyntax syntax = new SqFormSyntax(lexic);
            syntax.parse();
            //Getting info
            FormCommands formCommands = syntax.getFormCommands();
            ComponentCommands componentCommands = syntax.getComponentCommands();
            ErrorCommands errorCommands = syntax.getErrorCommands();
            ResultCommands resultCommands = syntax.getResultCommands();
            formCommands.checkForErrors();
            componentCommands.checkForErrors();
            if(errorCommands.haveErrors()){
                System.out.println("Hay errores");
                for(ErrorIndigo error:errorCommands.getErrors()){
                    System.out.println("Error: "+error.getMessage());
                    if(out!=null){
                        out.println(error.getMessage());
                    }
                }
                errors=true;
                forms = null;
                components = null;
            }else{
                forms = formCommands.toArrayList();
                
                components = componentCommands.getNewComponents();
                if(resultCommands!=null){
                    this.resultCommands = resultCommands;
                }else{
                    this.resultCommands = new ResultCommands();
                }
            }
        }catch(Throwable e){
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void readForms(ArrayList<BlockParameter> block){
        //Reading file
        String text;
        File directory = new File(relativePathUser);
        System.out.println(directory.getAbsolutePath());
        StringBuffer fileContent = new StringBuffer();
        try(BufferedReader br = new BufferedReader(new FileReader(this.relativePathForm))){
            //Reading file
            String sCurrentLine;
            while((sCurrentLine=br.readLine())!=null){
                fileContent.append(sCurrentLine).append("\n");
            }
            text = fileContent.toString();
            Reader reader = new StringReader(text);
            SqFormLexic lexic = new SqFormLexic(reader);
            SqFormSyntax syntax = new SqFormSyntax(lexic);
            syntax.parse();
            //Getting info
            FormCommands formCommands = syntax.getFormCommands();
            ComponentCommands componentCommands = syntax.getComponentCommands();
            ErrorCommands errorCommands = syntax.getErrorCommands();
            ResultCommands resultCommands = syntax.getResultCommands();
            formCommands.checkForErrors();
            componentCommands.checkForErrors();
            if(errorCommands.haveErrors()){
                System.out.println("Hay errores");
                ArrayList<Parameter> parameters = new ArrayList<>();
                for(ErrorIndigo error:errorCommands.getErrors()){
                    System.out.println("Error: "+error.getMessage());
                    if(block!=null){
                        parameters.add(new Parameter("Error",error.getMessage()));
                    }
                }
                block.add(new BlockParameter(parameters));
                errors=true;
                forms = null;
                components = null;
            }else{
                forms = formCommands.toArrayList();
                
                components = componentCommands.getNewComponents();
                if(resultCommands!=null){
                    this.resultCommands = resultCommands;
                }else{
                    this.resultCommands = new ResultCommands();
                }
            }
        }catch(Throwable e){
            System.out.println("Error: "+e.getMessage());
            block.add(new BlockParameter("Error",e.getMessage()));
            e.printStackTrace();
        }
    }
    
    private void readUsers(ArrayList<BlockParameter> block){
        //Reading file
        String text;
        StringBuffer fileContent = new StringBuffer();
        try(BufferedReader br = new BufferedReader(new FileReader(this.relativePathUser))){
            //Reading file
            String sCurrentLine;
            while((sCurrentLine=br.readLine())!=null){
                fileContent.append(sCurrentLine).append("\n");
            }
            text = fileContent.toString();
            
            //Parsing text
            Reader reader = new StringReader(text);
            SqFormLexic lexic = new SqFormLexic(reader);
            SqFormSyntax syntax = new SqFormSyntax(lexic);
            syntax.parse();
            //Getting info
            UserCommands userCommands = syntax.getUserCommands();
            ErrorCommands errorCommands = syntax.getErrorCommands();
            
            if(errorCommands.haveErrors()){
                System.out.println("Hay errores");
                ArrayList<Parameter> parameters = new ArrayList<>();
                for(ErrorIndigo error:errorCommands.getErrors()){
                    System.out.println("Error: "+error.getMessage());
                    if(block!=null){
                        parameters.add(new Parameter("Error",error.getMessage()));
                    }
                }
                block.add(new BlockParameter(parameters));
                errors=true;
                users = null;
            }else{
                //The ones we create
                users = userCommands.getUserList();
            }
        }catch(Throwable e){
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Read the Data in the user file
     */
    private void readUsers(PrintWriter out){
        //Reading file
        String text;
        StringBuffer fileContent = new StringBuffer();
        try(BufferedReader br = new BufferedReader(new FileReader(this.relativePathUser))){
            //Reading file
            String sCurrentLine;
            while((sCurrentLine=br.readLine())!=null){
                fileContent.append(sCurrentLine).append("\n");
            }
            text = fileContent.toString();
            
            //Parsing text
            Reader reader = new StringReader(text);
            SqFormLexic lexic = new SqFormLexic(reader);
            SqFormSyntax syntax = new SqFormSyntax(lexic);
            syntax.parse();
            //Getting info
            UserCommands userCommands = syntax.getUserCommands();
            ErrorCommands errorCommands = syntax.getErrorCommands();
            
            if(errorCommands.haveErrors()){
                System.out.println("Hay errores");
                for(ErrorIndigo error:errorCommands.getErrors()){
                    System.out.println("Error: "+error.getMessage());
                    if(out!=null){
                        out.println(error.getMessage());
                    }
                }
                errors=true;
                users = null;
            }else{
                //The ones we create
                users = userCommands.getUserList();
            }
        }catch(Throwable e){
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
    }
    
}
