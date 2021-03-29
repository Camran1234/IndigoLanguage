/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.indigo;

import WebSocket.dBIndigo;
import com.mycompany.formats.ErrorIndigo;
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
import java.util.logging.Level;
import java.util.logging.Logger;

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
        new SafeWriter().writeToFile(newUser, pathLog);
    }
    
    /**
     * Compile the Indigo Language
     * true for quit actual login
     * false for keep login
     * @param entryText 
     */
    public void readText(String entryText, PrintWriter out){
        try {
            out.println("Leyendo Archivos");
            Reader reader = new StringReader(entryText);
            IndigoLex scanner = new IndigoLex(reader);
            IndigoSyntax parser = new IndigoSyntax(scanner);
            out.println("Traduciendo Archivos");
            parser.parse();
            out.println("Obteniendo Componentes");
            ErrorCommands errorCommands = parser.getErrorCommands();
            UserCommands userCommands = parser.getUserCommands();
            FormCommands formCommands = parser.getFormCommands();
            ComponentCommands componentCommands = parser.getComponentCommands();
            out.println("Comprobando componentes");
            formCommands.checkForErrors();
            userCommands.checkForErrors();
            componentCommands.checkForErrors();
            //if we have errors then we return the messages
            if(!errorCommands.haveErrors()){
                dBIndigo dbIndigo = new dBIndigo(out);
                out.println("Verificando Usuario");
                //add the login
                if(userCommands.getLoginList().size()>0){
                    String user = userCommands.getLoginList().get(userCommands.getLoginList().size()-1).getUser();
                    String password = userCommands.getLoginList().get(userCommands.getLoginList().size()-1).getPassword();
                    if(user!=null && password!=null){
                        if(!dbIndigo.singUp(user,password,out)){
                        //throw new Exception("El nombre de usuario o la contrasena no coinciden, vuelva intentar logearse");
                        out.println("WARNING: El nombre de usuario:\""+user+"\" o la contrasena:\""+password+"\" no coinciden, intento de iniciar sesion fallido");
                        }
                    }
                }
                //add the new Data
                //We upload the new Data
                out.println("Cargando Archivos");
                dbIndigo.newRequest(formCommands, userCommands,componentCommands, out);
                out.println("Actualizando Archivos");
                dbIndigo.uploadNewDate(null);
                out.println("Fin Archivos");
            }else{
                System.out.println("Hay errores");
                for(ErrorIndigo error:errorCommands.getErrors()){
                    System.out.println("Error: "+error.getMessage());
                    out.println(error.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println("ERROR:\n en Analysis"+ex.getMessage());
            ex.printStackTrace();
            out.println("ERROR: \n"+ex.getMessage());
        }
        
    }
    
}
