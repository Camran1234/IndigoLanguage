/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.formsafe;

import com.mycompany.formats.Answer;
import com.mycompany.formats.Component;
import com.mycompany.formats.Form;
import com.mycompany.formats.Result;
import com.mycompany.formats.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author camran1234
 */
public class SafeWriter {
    private String relativePathForm="./Resources/form.sqf";
    private String relativePathUser="./Resources/user.sqf";
    
    /**
     * Write user in JSON format in a sqf file 
     * @param users 
     */
    public void WriteUser(ArrayList<User> users){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        /*String format = formatter.format(date);*/
        String format;
        User user;
        try(FileOutputStream outputStream = new FileOutputStream(relativePathUser)){
                writeString(outputStream, "db.usuarios (\n");
            for(int indexUser=0; indexUser<users.size(); indexUser++){
                user = users.get(indexUser);
                if(indexUser>0){
                    writeString(outputStream, ",\n");
                }
                //Open User
                writeString(outputStream, "\t{\n");
                writeString(outputStream, "\t\t\"USUARIO\" : \""+ user.getUser() +"\",\n");
                if(user.getDate()==null){
                    writeString(outputStream, "\t\t\"PASSWORD\" : \""+ user.getPassword()+"\"\n");
                }else{
                    writeString(outputStream, "\t\t\"PASSWORD\" : \""+ user.getPassword()+"\",\n");
                    writeString(outputStream, "\t\t\"FECHA\" : \""+ formatter.format(user.getDate())+"\" \n");
                }
                //Close User
                writeString(outputStream, "\t}");
            }
                writeString(outputStream, "\n)");
                System.out.println("End of Write");
                outputStream.close();
        }catch(Throwable ex){
            System.out.println("Error: "+ex.getMessage());
            ex.printStackTrace();
        }
        
    }
    
    public void WriteForm(ArrayList<Form> forms, ArrayList<Component> components, ArrayList<Result> results){
        File directory = new File(relativePathForm);
        System.out.println(directory.getAbsolutePath());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String format;
        Form form;
        Component component;
        Result result;
        Answer answer;
        ArrayList<Component> aux = new ArrayList<>();
        try(FileOutputStream outputStream = new FileOutputStream(relativePathForm)){
                writeString(outputStream, "db.formularios (\n");
            for(int indexUser=0; indexUser<forms.size(); indexUser++){
                aux = new ArrayList<>();
                if(indexUser>0){
                    writeString(outputStream, "\t,\n");
                }
                form = forms.get(indexUser);
                writeString(outputStream, "\t{\n");
                //Form Params
                writeString(outputStream, "\t\t\"ID_FORMULARIO\" : \""+form.getId()+"\",\n");
                writeString(outputStream, "\t\t\"TITULO\" : \""+form.getTittle()+"\",\n");
                writeString(outputStream, "\t\t\"NOMBRE\" : \""+form.getName()+"\",\n");
                writeString(outputStream, "\t\t\"TEMA\" : \""+form.getTopic()+"\",\n");
                writeString(outputStream, "\t\t\"USUARIO_CREACION\" : \""+form.getUserCreator()+"\",\n");
                writeString(outputStream, "\t\t\"VISIBILIDAD\" : \""+form.getVisibility()+"\"\n");
                //END PARAMS FORM
                
                for(Component newComponent:components){
                    if(newComponent.getFormName().equals(form.getId())){
                        aux.add(newComponent);
                    }
                }
                
                //OPEN STRUCT COMPONENTS
                writeString(outputStream, "\t\t\"ESTRUCTURA\" : ( \n");
                for(int indexComponent=0; indexComponent<aux.size(); indexComponent++){
                    component = aux.get(indexComponent);
                    if(indexComponent>0){
                        writeString(outputStream, ", \n");
                    }
                    writeString(outputStream, "\t\t\t { \n");
                    //OPEN COMPONENT PARAMS
                    writeString(outputStream, "\t\t\t\t\"ID_COMPONENTE\" : \""+component.getId()+"\", \n");
                    writeString(outputStream, "\t\t\t\t\"NOMBRE_CAMPO\" : \""+component.getCampName()+"\", \n");
                    writeString(outputStream, "\t\t\t\t\"FORMULARIO\" : \""+component.getFormName()+"\", \n");
                    writeString(outputStream, "\t\t\t\t\"CLASE\" : \""+component.getClassName()+"\", \n");
                    writeString(outputStream, "\t\t\t\t\"TEXTO_VISIBLE\" : \""+component.getVisibleText()+"\" ");
                    
                    if(component.getAlign()!=null){
                        writeString(outputStream, ",\n\t\t\t\t\"ALINEACION\" : \""+component.getAlign()+"\" ");
                    }
                    
                    if(component.getRequired()!=null){
                        writeString(outputStream, ",\n\t\t\t\t\"REQUERIDO\" : \""+component.getRequired()+"\" ");
                    }
                    
                    if(component.getOptions()!=null){
                        writeString(outputStream, ",\n\t\t\t\t\"OPCIONES\" : \""+component.getOptions()+"\" ");
                    }
                    
                    if(component.getRows()!=null){
                        writeString(outputStream, ",\n\t\t\t\t\"FILAS\" : \""+component.getRows()+"\" ");
                    }
                    
                    if(component.getCols()!=null){
                        writeString(outputStream, ",\n\t\t\t\t\"COLUMNAS\" : \""+component.getCols()+"\" ");
                    }
                    
                    if(component.getUrl()!=null){
                        writeString(outputStream, ",\n\t\t\t\t\"URL\" : \""+component.getUrl()+"\" ");
                    }
                    
                    //CLOSE COMPONENT PARAMS
                    
                    writeString(outputStream, "\n\t\t\t } ");
                }
                writeString(outputStream, "\n\t\t),\n");
                //END STRUCT COMPONENTS
                
                                
                //START STRUCT ANSWERS
                writeString(outputStream, "\t\tDATOS_RECOPILADOS : (\n");
                
                for(int indexResult=0; indexResult<results.size(); indexResult++){
                    result = results.get(indexResult);
                    if(indexResult>0){
                        writeString(outputStream, ",\n");
                    }
                    writeString(outputStream, "\t\t\t{\n");
                    writeString(outputStream, "\t\t\t\"NOMBRE_CAMPO\" : \""+result.getNameCamp()+"\" ,\n");
                    writeString(outputStream, "\t\t\t\"ID_FORMULARIO\" : \""+result.getNameCamp()+"\" ");
                    
                    for(int indexAnswer=0; indexAnswer<result.getAnswers().size(); indexAnswer++){
                        answer = result.getAnswers().get(indexAnswer);
                        writeString(outputStream, ",\n\t\t\t\""+answer.getIdUser()+"\" : \""+answer.getResult()+"\" \n");
                    }
                    writeString(outputStream, "\t\t\t}");
                }
                
                writeString(outputStream, "\n\t\t)\n");
                //CLOSE STRUCT ANSERS
                
                
                writeString(outputStream, "\t}");
            }
                writeString(outputStream, "\n)");
                outputStream.close();
        }catch(Throwable ex){
            System.out.println("Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }
    

    /**
     * Method to write
     * @param output
     * @param text
     * @throws IOException 
     */
    private void writeString(FileOutputStream output, String text) throws IOException{
        String addText = text;
        output.write(addText.getBytes());
    }
    
    public void writeToFile(String text, String path){
        String context = text;
        if(context==null){
            context="";
        }
        try(FileOutputStream outputStream = new FileOutputStream(path)){
                
                writeString(outputStream, context);
                outputStream.close();
        }catch(Throwable ex){
            System.out.println("Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }

    
}
