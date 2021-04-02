/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.indigo;

import WebSocket.dBIndigo;
import com.mycompany.formats.ErrorIndigo;
import com.mycompany.formats.Parameter;
import com.mycompany.formsafe.SafeWriter;
import com.mycompany.formsafe.SqFormLexic;
import com.mycompany.formsafe.SqFormSyntax;
import com.mycompany.handlers.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import com.mycompany.formats.BlockParameter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author camran1234
 */
public class Analysis {
    private final String pathLog="Resources/session.txt";
    
    private void checkIfExist() throws IOException{
        File file = new File("Resources");
        if(!file.exists() && !file.isDirectory()){
            file.mkdir();
        }
        file = new File(pathLog);
        if(!file.exists()){
            file.createNewFile();
        }
    }
    
    /**
     * Function to get the actual loged User
     * @return 
     */
    public String getLoggedUser(){
        try {
            checkIfExist();
        } catch (IOException ex) {
            System.out.println("Error en getLoggedUser, Analysis class, Error: "+ex.getMessage());
            ex.printStackTrace();
        }
        String text=null;
        StringBuffer fileContent = new StringBuffer();
        try(BufferedReader br = new BufferedReader(new FileReader(this.pathLog))){
            //Reading file
            String sCurrentLine;
            while((sCurrentLine=br.readLine())!=null){
                fileContent.append(sCurrentLine).append("\n");
            }
            text = fileContent.toString();
        }catch(Throwable e){
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
        if(text.equalsIgnoreCase("")){
            return null;
        }
        text = text.replace("\n", "");
        return text;
    }
    
    /**
     * Method to write a new Logged user
     * @param newUser 
     */
    public void setLoggedUser(String newUser){
        try {
            checkIfExist();
        } catch (IOException ex) {
            System.out.println("Error en getLoggedUser, Analysis class, Error: "+ex.getMessage());
            ex.printStackTrace();
        }
        System.out.println("ENTRANDO A CAMBIAR EL ARCHIVO");
        new SafeWriter().writeToFile(newUser, pathLog);
    }
    
    /**
     * Compile the Indigo Language
     * true for quit actual login
     * false for keep login
     * @param entryText 
     */
    public void readText(String entryText, ArrayList<BlockParameter> block){
        try {
            
            ArrayList<Parameter> parameter = new ArrayList<>();
            ArrayList<Parameter> parameterWar = new ArrayList<>();
            parameter.add(new Parameter("Text","Leyendo Archivos\n"));
            Reader reader = new StringReader(entryText);
            IndigoLex scanner = new IndigoLex(reader);
            IndigoSyntax parser = new IndigoSyntax(scanner);
            parameter.add(new Parameter("Text","Traduciendo Archivos\n"));
            parser.parse();
            parameter.add(new Parameter("Text","Obteniendo Componentes\n"));
            ErrorCommands errorCommands = parser.getErrorCommands();
            UserCommands userCommands = parser.getUserCommands();
            FormCommands formCommands = parser.getFormCommands();
            ComponentCommands componentCommands = parser.getComponentCommands();
            parameter.add(new Parameter("Text","Comprobando componentes\n"));
            formCommands.checkForErrors();
            userCommands.checkForErrors();
            componentCommands.checkForErrors();
            //if we have errors then we return the messages
            if(!errorCommands.haveErrors()){
                dBIndigo dbIndigo = new dBIndigo(block);
                parameter.add(new Parameter("Text","Verificando Usuario\n"));
                //add the login
                if(userCommands.getLoginList().size()>0){
                    String user = userCommands.getLoginList().get(userCommands.getLoginList().size()-1).getUser();
                    String password = userCommands.getLoginList().get(userCommands.getLoginList().size()-1).getPassword();
                    
                    if(user!=null && password!=null){
                        if(!dbIndigo.singUp(user,password,block)){
                        //throw new Exception("El nombre de usuario o la contrasena no coinciden, vuelva intentar logearse");
                        parameterWar.add(new Parameter("Warning","El nombre de usuario:"+user+" o la contrasena:"+password+" no coinciden, intento de iniciar sesion fallido\n"));
                        block.add(new BlockParameter(parameterWar));
                        }
                    }
                }
                //add the new Data
                //We upload the new Data
                parameter.add(new Parameter("Text","Cagando Archivos\n"));
                dbIndigo.newRequest(formCommands, userCommands,componentCommands, block);
                parameter.add(new Parameter("Text","Actualizando Archivos\n"));
                dbIndigo.uploadNewDate(null);
                parameter.add(new Parameter("Text","Fin Archivo\n"));
                block.add(new BlockParameter(parameter));
            }else{
                System.out.println("Hay errores");
                ArrayList<Parameter> parameterAux = new ArrayList<>();
                for(ErrorIndigo error:errorCommands.getErrors()){
                    System.out.println("Error: "+error.getMessage());
                    parameterAux.add(new Parameter("Error",error.getMessage()+"\n"));
                }
                block.add(new BlockParameter(parameter));
                 block.add(new BlockParameter(parameterAux));
            }
        } catch (Exception ex) {
            System.out.println("ERROR:\n en Analysis"+ex.getMessage());
            ex.printStackTrace();
            block.add(new BlockParameter("Error: ",ex.getMessage()));
        }
        
    }
    
}
