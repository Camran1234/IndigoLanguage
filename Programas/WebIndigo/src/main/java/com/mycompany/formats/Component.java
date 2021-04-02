/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.formats;

import com.mycompany.handlers.ErrorCommands;
import java.util.ArrayList;

/**
 *
 * @author camran1234
 */
public class Component implements ErrorHandler{
    private String type;
    private String id;
    private String campName;
    //formName==idName
    private String formName;
    private String className;
    private String visibleText;
    private String align;
    private String required="NO";
    private String options;
    private String rows;
    private String cols;
    private String url;
    private String index;
    private boolean activated=false;
    private String token;
    private int line;
    private int column;
    
    
    
    public Component(String token, int line, int column, String type){
        this.token = token;
        this.line = line;
        this.column = column;
        this.type = type;
        this.activated=true;
    }
    
    public void close(String token, int line, int column){
        activated=false;
        this.token = token;
        this.line = line;
        this.column = column;
    }
    
    public String getIndex(){
        return index;
    }

    public void setIndex(String index){
        this.index = index;
    }
    
    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getCampName() {
        return campName;
    }

    public String getFormName() {
        return formName;
    }

    public String getClassName() {
        return className;
    }

    public String getVisibleText() {
        return visibleText;
    }

    public String getAlign() {
        return align;
    }

    public String getRequired() {
        return required;
    }

    public String getOptions() {
        return options;
    }

    public String getCols() {
        return cols;
    }
    
    public String getRows() {
        return cols;
    }

    public String getUrl() {
        return url;
    }

    public int getColumn() {
        return column;
    }
    
    
    
    public void checkForClassErros(){
        String errorMessage="";
        if(className.equalsIgnoreCase("CAMPO_TEXTO")){
            if( url!=null || options!=null||rows!=null||cols!=null){
                if(url!=null){
                    errorMessage+=" se agrego una url cuando es la clase CAMPO_TEXTO";
                }
                if(options!=null){
                    errorMessage+=" se agrego opciones cuando la clase es CAMPO_TEXTO";
                }
                if(rows!=null){
                    errorMessage+=" se agrego filas cuando la clase es CAMPO_TEXTO";
                }
                if(cols!=null){
                    errorMessage+=" se agrego columnas cuando la clase es CAMPO_TEXTO";
                }
                this.addSemanticError(new ErrorIndigo(errorMessage, token, line, column));
            }
        }else if(className.equalsIgnoreCase("AREA_TEXTO")){
            if(rows==null||cols==null || url!=null || options!=null){
                if(rows==null){
                    errorMessage+=" no se agregaron filas al AREA_TEXTO, agrega un numero superior a 0";
                }
                if(cols==null){
                    errorMessage+=" no se agregaron columnas al AREA_TEXTO, agrega un numero superior a 0";
                }
                if(url!=null){
                    errorMessage+=" se agrego una URL cuando la clase es AREA_TEXTO";
                }
                if(options!=null){
                    errorMessage+=" se agregaron opciones cuando la clase es AREA_TEXTO";
                }
                
                this.addSemanticError(new ErrorIndigo(errorMessage, token, line, column));
            }
        }else if(className.equalsIgnoreCase("CHECKBOX")){
            if(url!=null|| rows!=null || cols!=null|| options==null){
                if(url!=null){
                    errorMessage+=" se agrego una URL cuando la clase es CHECKBOX, favor de quitarla";
                }
                if(rows!=null){
                    errorMessage+=" se agrego FILAS cuando la clase es CHECKBOX, retirar FILAS";
                }
                if(cols!=null){
                    errorMessage+=" se agrego COLUMNAS cuando la clase es CHECKBOX, retirar COLUMNAS";
                }
                if(options==null){
                    errorMessage+=" no se agregaron las opciones para el CHECKBOX, favor de agregar opciones";
                }
                this.addSemanticError(new ErrorIndigo(errorMessage, token, line, column));
            }
        }else if(className.equalsIgnoreCase("RADIO")){
            if(options==null||rows!=null||cols!=null||url!=null){
                if(options==null){
                    errorMessage+=" no se agregaron opciones para la clase RADIO, agregar OPCIONES";
                }
                if(rows!=null){
                    errorMessage+=" se agregaron FILAS cuando la clase es RADIO, quitar las FILAS";
                }
                if(cols!=null){
                    errorMessage+=" se agregaron COLUMNAS cuando la clase es RADIO, quitar las COLUMNAS";
                }
                if(url!=null){
                    errorMessage+=" se agrego una URL cuando la clase es RADIO, quitar la URL";
                }
                this.addSemanticError(new ErrorIndigo(errorMessage, token, line, column));
            }
            
        }else if(className.equalsIgnoreCase("FICHERO")){
            if( url!=null || options!=null||rows!=null||cols!=null){
                if(url!=null){
                    errorMessage+=" se agrego una url cuando la clase es FICHERO";
                }
                if(options!=null){
                    errorMessage+=" se agrego opciones cuando la clase es FICHERO";
                }
                if(rows!=null){
                    errorMessage+=" se agregaron filas cuando la clase es FICHERO";
                }
                if(cols!=null){
                    errorMessage+=" se agregaron columnas cuando la clase es FICHERO";
                }
                
                this.addSemanticError(new ErrorIndigo(errorMessage, token, line, column));
            }
        }else if(className.equalsIgnoreCase("IMAGEN")){
            if( url==null || options!=null||rows!=null||cols!=null){
                if(url==null){
                    errorMessage+=" no se agrego una url para colocar la IMAGEN";
                }
                if(options!=null){
                    errorMessage+=" se agregaron OPCIONES a la clase IMAGEN";
                }
                if(rows!=null){
                    errorMessage+=" se agregaron filas cuando la clase es IMAGEN";
                }
                if(cols!=null){
                    errorMessage+=" se agregaron columnas cuando la clase es IMAGEN";
                }
                this.addSemanticError(new ErrorIndigo(errorMessage, token, line, column));
            }
        }else if(className.equalsIgnoreCase("COMBO")){
            if(options==null||rows!=null||cols!=null||url!=null){
                if(options==null){
                    errorMessage+=" no se agrego OPCIONES a la clase COMBO";
                }
                if(rows!=null){
                    errorMessage+=" se agrego filas cuando la clase  es COMBO";
                }
                if(cols!=null){
                    errorMessage+=" se agrego columnas cuando la clase es COMBO";
                }
                if(url!=null){
                    errorMessage+=" se agrego una url cuando la clase es COMBO";
                }
                this.addSemanticError(new ErrorIndigo(errorMessage, token, line, column));
            }            
        }else if(className.equalsIgnoreCase("BOTON")){
            if( url!=null || options!=null||rows!=null||cols!=null){
                if(url!=null){
                    errorMessage+=" se agrego una url cuando es la clase BOTON";
                }
                if(options!=null){
                    errorMessage+=" se agrego opciones cuando la clase es BOTON";
                }
                if(rows!=null){
                    errorMessage+=" se agrego filas cuando la clase es BOTON";
                }
                if(cols!=null){
                    errorMessage+=" se agrego columnas cuando la clase es BOTON";
                }
                this.addSemanticError(new ErrorIndigo(errorMessage, token, line, column));
            }
        }else if(this.id==null || this.formName==null){
            if(id==null){
                errorMessage+=" no se agrego id en el componente";
            }
            if(formName==null){
                errorMessage+=" no se agrego el nombre del formulario perteneciente en el componente";
            }
            this.addSemanticError(new ErrorIndigo(errorMessage, token, line, column));
        }
    }
    
    /**
     * Get as an arrayList the options
     * @return 
     */
    public ArrayList<String> getOptionsAsList(){
        ArrayList<String> optionsList = new ArrayList<>();
        if(options!=null){
            String[] newList = options.split("\\|");
            for(int index=0; index<newList.length; index++){
                optionsList.add(newList[index]);
                System.out.println(newList[index]);
            }
        }
        return optionsList;
    }
    
    @Override
    public void checkForSemanticErrors(){
        String errorMessage="";
        if(!activated){
            if(type.equalsIgnoreCase("new")){
                if(id==null||campName==null||formName==null||className==null||visibleText==null){
                    if(id==null){
                        errorMessage+=" no se agrego un id para el componente formulario";
                    }
                    if(campName==null){
                        errorMessage+=" no se agrego un nombre de campo para el componente";
                    }
                    if(formName==null){
                        errorMessage+=" no se agrego el nombre de formulario perteneciente";
                    }
                    if(className==null){
                        errorMessage+=" no se agrego una clase de componenete especificada";
                    }
                    if(visibleText==null){
                        errorMessage+=" no se agrego un texto visible para el componente";
                    }
                    this.addSemanticError(new ErrorIndigo(errorMessage, token, line, column));
                }
            }else{
                if(id==null||formName==null){
                    if(id==null){
                        errorMessage+=" no se agrego un id para el componente para modificar";
                    }
                    if(formName==null){
                        errorMessage+=" no se agrego un nombre del formulario perteneciente para modificar";
                    }
                    this.addSemanticError(new ErrorIndigo(errorMessage, token, line, column));
                }
            }
        }else{
            errorMessage = "Error Fatal no se cerro correctamente el formulario";
            addSemanticError(new ErrorIndigo(errorMessage,token,line,column));
        }
    }
    
    private void addSemanticError(ErrorIndigo newError){
        ErrorCommands.errors.add(newError);
    }
    
    public void setId(String id) {
        if(activated){
            this.id = id;
        }
    }
    
    public void setMode(boolean etc){
        this.activated = etc;
    }


    public void setCampName(String campName) {
        if(activated){
            this.campName = campName;
        }
    }

    public void setFormName(String formName) {
        if(activated){
            this.formName = formName;
        }
    }

    public void setClassName(String className) {
        if(activated){
            this.className = className;
        }
    }

    public void setVisibleText(String visibleText) {
        if(activated){
            this.visibleText = visibleText;
        }
    }

    public void setAlign(String align) {
        if(activated){
            this.align = align;
        }        
    }

    public void setRequired(String required) {
        if(activated){
            this.required = required;
        }
    }

    public void setOptions(String options) {
        if(activated){
            this.options = options;
        }
    }

    public void setRows(String rows) {
        if(activated){
            try {
                int row = Integer.parseInt(rows);
                this.rows = rows;
            } catch (Exception e) {
                ErrorIndigo error = new ErrorIndigo(e.getMessage(),token, line, column);
                this.addSemanticError(error);
            }
        }        
    }

    public void setCols(String cols) {
        if(activated){
            try {
                int col = Integer.parseInt(cols);
                this.cols = cols;
            } catch (Exception e) {
                ErrorIndigo error = new ErrorIndigo(e.getMessage(),token, line, column);
                this.addSemanticError(error);
            }
        }
    }

    public void setUrl(String url) {
        if(activated){
            this.url = url;
        }
    }
    
    
    
    
}
