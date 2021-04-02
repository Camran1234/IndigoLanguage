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
import javax.swing.JOptionPane;

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
    
    public String WriteFormAsString(ArrayList<Form> forms, ArrayList<Component> components, ArrayList<Result> results){
        String data="";
        Form form;
        Component component;
        Result result;
        ArrayList<Component> aux = new ArrayList<>();
        ArrayList<Form> auxiliarForms = new ArrayList<>();
        ArrayList<Component> auxiliarComponents = new ArrayList<>();
        //We change the position of the list forms
        for(int index=forms.size()-1; index>=0; index--){
            auxiliarForms.add(forms.get(index));
        }
        //We change the position of the list components
        for(int index=components.size()-1; index>=0; index--){
            auxiliarComponents.add(components.get(index));
        }
        forms = auxiliarForms;
        components = auxiliarComponents;
        try{
                FileOutputStream outputStream = null;
               data += writeString(outputStream, "db.formularios (\n");
            for(int indexUser=0; indexUser<forms.size(); indexUser++){
                aux = new ArrayList<>();
                if(indexUser>0){
                   data+= writeString(outputStream, "\t,\n");
                }
                form = forms.get(indexUser);
                data+=writeString(outputStream, "\t{\n");
                //Form Params
                data+=writeString(outputStream, "\t\t\"ID_FORMULARIO\" : \""+form.getId()+"\",\n");
                data+=writeString(outputStream, "\t\t\"TITULO\" : \""+form.getTittle()+"\",\n");
                data+=writeString(outputStream, "\t\t\"NOMBRE\" : \""+form.getName()+"\",\n");
                data+=writeString(outputStream, "\t\t\"TEMA\" : \""+form.getTopic()+"\",\n");
                data+=writeString(outputStream, "\t\t\"USUARIO_CREACION\" : \""+form.getUserCreator()+"\",\n");
                data+=writeString(outputStream, "\t\t\"VISIBILIDAD\" : \""+form.getVisibility()+"\"\n");
                //END PARAMS FORM
                
                for(Component newComponent:components){
                    if(newComponent.getFormName().equals(form.getId())){
                        aux.add(newComponent);
                    }
                }
                
                //OPEN STRUCT COMPONENTS
                data+=writeString(outputStream, "\t\t\"ESTRUCTURA\" : ( \n");
                if(aux.size()!=0)
                for(int indexComponent=0; indexComponent<aux.size(); indexComponent++){
                    component = aux.get(indexComponent);
                    if(indexComponent>0){
                        writeString(outputStream, ", \n");
                    }
                    data+=writeString(outputStream, "\t\t\t { \n");
                    //OPEN COMPONENT PARAMS
                    data+=writeString(outputStream, "\t\t\t\t\"ID_COMPONENTE\" : \""+component.getId()+"\", \n");
                    data+=writeString(outputStream, "\t\t\t\t\"NOMBRE_CAMPO\" : \""+component.getCampName()+"\", \n");
                    data+=writeString(outputStream, "\t\t\t\t\"FORMULARIO\" : \""+component.getFormName()+"\", \n");
                    data+=writeString(outputStream, "\t\t\t\t\"CLASE\" : \""+component.getClassName()+"\", \n");
                    data+=writeString(outputStream, "\t\t\t\t\"TEXTO_VISIBLE\" : \""+component.getVisibleText()+"\" ");
                    
                    if(component.getAlign()!=null){
                        data+=writeString(outputStream, ",\n\t\t\t\t\"ALINEACION\" : \""+component.getAlign()+"\" ");
                    }
                    
                    if(component.getRequired()!=null){
                        data+=writeString(outputStream, ",\n\t\t\t\t\"REQUERIDO\" : \""+component.getRequired()+"\" ");
                    }
                    
                    if(component.getOptions()!=null){
                        data+=writeString(outputStream, ",\n\t\t\t\t\"OPCIONES\" : \""+component.getOptions()+"\" ");
                    }
                    
                    if(component.getRows()!=null){
                        data+=writeString(outputStream, ",\n\t\t\t\t\"FILAS\" : \""+component.getRows()+"\" ");
                    }
                    
                    if(component.getCols()!=null){
                        data+=writeString(outputStream, ",\n\t\t\t\t\"COLUMNAS\" : \""+component.getCols()+"\" ");
                    }
                    
                    if(component.getUrl()!=null){
                        data+=writeString(outputStream, ",\n\t\t\t\t\"URL\" : \""+component.getUrl()+"\" ");
                    }
                    
                    //CLOSE COMPONENT PARAMS
                    
                    data+=writeString(outputStream, "\n\t\t\t } ");
                }
                data+=writeString(outputStream, "\n\t\t),\n");
                //END STRUCT COMPONENTS
                
                                
                //START STRUCT ANSWERS
                data+=writeString(outputStream, "\t\tDATOS_RECOPILADOS : (\n");
                
                for(int indexResult=0; indexResult<results.size(); indexResult++){
                    result = results.get(indexResult);
                    if(result!=null){
                        System.out.println("RESULT: "+result.getIdForm());
                        System.out.println("FORM: "+form.getId());
                        if(result.getIdForm().equals(form.getId())){
                            if(indexResult>0){
                                data+=writeString(outputStream, ",\n");
                            }
                            data+=writeString(outputStream, "\t\t\t{\n");
                            data+=writeString(outputStream, "\t\t\t\"NOMBRE_CAMPO\" : \""+result.getNameCamp()+"\" ,\n");
                            data+=writeString(outputStream, "\t\t\t\"ID_FORMULARIO\" : \""+result.getIdForm()+"\" ");                           
                            ArrayList<Answer> answers = result.getAnswers();
                            for(Answer answer:answers){
                                data+=writeString(outputStream, ",\n\t\t\t\""+answer.getIdUser()+"\" : \""+answer.getResult()+"\" \n");
                            }
                            data+=writeString(outputStream, "\t\t\t}");
                        }
                    }
                }
                
                data+=writeString(outputStream, "\n\t\t)\n");
                //CLOSE STRUCT ANSERS
                
                
                data+=writeString(outputStream, "\t}");
            }
                data+=writeString(outputStream, "\n)");
                return data;
        }catch(Throwable ex){
            System.out.println("Error: "+ex.getMessage());
            ex.printStackTrace();
            return null;
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
        ArrayList<Component> aux = new ArrayList<>();
        ArrayList<Form> auxiliarForms = new ArrayList<>();
        ArrayList<Component> auxiliarComponents = new ArrayList<>();
        //We change the position of the list forms
        for(int index=forms.size()-1; index>=0; index--){
            auxiliarForms.add(forms.get(index));
        }
        //We change the position of the list components
        for(int index=components.size()-1; index>=0; index--){
            auxiliarComponents.add(components.get(index));
        }
        forms = auxiliarForms;
        components = auxiliarComponents;
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
                if(aux.size()!=0)
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
                
                ArrayList<Result> auxResult = new ArrayList<>();
                
                for(int indexA=0; indexA<results.size(); indexA++){
                    if(results.get(indexA).getIdForm().equals(form.getId())){
                        auxResult.add(results.get(indexA));
                    }
                }
                
                for(int indexResult=0; indexResult<auxResult.size(); indexResult++){
                    result = auxResult.get(indexResult);
                    if(result!=null){
                        System.out.println("RESULT: "+result.getIdForm());
                        System.out.println("FORM: "+form.getId());
                        if(result.getIdForm().equals(form.getId())){
                            if(indexResult>0){
                                System.out.println("Index Result: "+indexResult);
                                writeString(outputStream, ",\n");
                            }
                            writeString(outputStream, "\t\t\t{\n");
                            writeString(outputStream, "\t\t\t\"NOMBRE_CAMPO\" : \""+result.getNameCamp()+"\" ,\n");
                            writeString(outputStream, "\t\t\t\"ID_FORMULARIO\" : \""+result.getIdForm()+"\" ");                           
                            ArrayList<Answer> answers = result.getAnswers();
                            for(Answer answer:answers){
                                writeString(outputStream, ",\n\t\t\t\""+answer.getIdUser()+"\" : \""+answer.getResult()+"\" \n");
                            }
                            writeString(outputStream, "\t\t\t}");
                        }
                    }
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
    private String writeString(FileOutputStream output, String text) throws IOException{
        String addText = text;
        if(output!=null){
            output.write(addText.getBytes());
        }
        return addText;
    }
    
    public void writeToFile(String text, String path){
        String context = text;
        System.out.println("LLEGANDO A WRITETOFILE");
        if(context==null){
            context="";
        }
        System.out.println("MODIFICANDO");
        try(FileOutputStream outputStream = new FileOutputStream(path)){   
                writeString(outputStream, context);
                outputStream.close();
                System.out.println("Archivo Modificado");
                System.out.println("AGREGANDO NULO");
        }catch(Throwable ex){
            System.out.println("Error: "+ex.getMessage());
            ex.printStackTrace();
        }
    }

    
}
