/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.indigo;

/**
 *
 * @author camran1234
 */
public class TokenHandler {
    
    
    public String expectedFormIndigo(String token){
        if(token.equalsIgnoreCase("START")){
            return "!ini_solicitudes";
        }else if(token.equalsIgnoreCase("FINAL")){
            return "!fin_solicitudes";
        }else if(token.equalsIgnoreCase("APERTURE")){
            return "!ini_solicitud";
        }else if(token.equalsIgnoreCase("END")){
            return "fin_solicitud!";
        }else if(token.equalsIgnoreCase("CREDENTIAL")){
            return "\"CREDENCIALES_USUARIO\"";
        }else if(token.equalsIgnoreCase("USER")){
            return "\"USUARIO\"";
        }else if(token.equalsIgnoreCase("PASSWORD")){
            return "\"PASSWORD\"";
        }else if(token.equalsIgnoreCase("DATE")){
            return "\"FECHA_CREACION\" o \"FECHA_MODIFICACION\"";
        }else if(token.equalsIgnoreCase("PAST_USER")){
            return "\"USUARIO_ANTIGUO\"";
        }else if(token.equalsIgnoreCase("NEW_USER")){
            return "\"USUARIO_NUEVO\"";
        }else if(token.equalsIgnoreCase("NEW_PASSWORD")){
            return "\"NUEVO_PASSWORD\"";
        }else if(token.equalsIgnoreCase("CREATE_U")){
            return "\"CREAR_USUARIO\"";
        }else if(token.equalsIgnoreCase("MODIFY_U")){
            return "\"MODIFICAR_USUARIO\"";
        }else if(token.equalsIgnoreCase("DELETE_U")){
            return "\"ELIMINAR_USUARIO\"";
        }else if(token.equalsIgnoreCase("LOGIN_U")){
            return "\"LOGIN_USUARIO\"";
        }else if(token.equalsIgnoreCase("NEW_F")){
            return "\"NUEVO_FORMULARIO\"";
        }else if(token.equalsIgnoreCase("DELETE_F")){
            return "\"ELIMINAR_FORMULARIO\"";
        }else if(token.equalsIgnoreCase("MODIFY_F")){
            return "\"MODIFICAR_FORMULARIO\"";
        }else if(token.equalsIgnoreCase("FORM_PARAMETERS")){
            return "\"PARAMETROS_FORMULARIO\"";
        }else if(token.equalsIgnoreCase("ID")){
            return "\"ID\"";
        }else if(token.equalsIgnoreCase("TITTLE")){
            return "\"TITULO\"";
        }else if(token.equalsIgnoreCase("TOPIC")){
            return "\"TEMA\"";
        }else if(token.equalsIgnoreCase("NAME_F")){
            return "\"NOMBRE_FORMULARIO\"";
        }else if(token.equalsIgnoreCase("ADD_C")){
            return "\"AGREGAR_COMPONENTE\"";
        }else if(token.equalsIgnoreCase("DELETE_C")){
            return "\"ELIMINAR_COMPONENTE\"";
        }else if(token.equalsIgnoreCase("MODIFY_C")){
            return "\"MODIFICAR_COMPONENTE\"";
        }else if(token.equalsIgnoreCase("C_PARAMETERS")){
            return "\"PARAMETROS_COMPONENTE\"";
        }else if(token.equalsIgnoreCase("NAME_C")){
            return "\"NOMBRE_CAMPO\"";
        }else if(token.equalsIgnoreCase("FORM")){
            return "\"FORMULARIO\"";
        }else if(token.equalsIgnoreCase("CLASS")){
            return "\"CLASE\"";
        }else if(token.equalsIgnoreCase("TEXTV")){
            return "\"TEXTO_VISIBLE\"";
        }else if(token.equalsIgnoreCase("ALIGNMENT")){
            return "\"ALINEACION\"";
        }else if(token.equalsIgnoreCase("TEXT_CAMP")){
            return "\"CAMPO_TEXTO\"";
        }else if(token.equalsIgnoreCase("TEXT_AREA")){
            return "\"AREA_TEXTO\"";
        }else if(token.equalsIgnoreCase("CHECKBOX")){
            return "\"CHECKBOX\"";
        }else if(token.equalsIgnoreCase("RATIO")){
            return "\"RADIO\"";
        }else if(token.equalsIgnoreCase("FILE")){
            return "\"FICHERO\"";
        }else if(token.equalsIgnoreCase("IMAGE")){
            return "\"IMAGEN\"";
        }else if(token.equalsIgnoreCase("COMBO")){
            return "\"COMBO\"";
        }else if(token.equalsIgnoreCase("BUTTON")){
            return "\"BOTON\"";
        }else if(token.equalsIgnoreCase("REQUIRED")){
            return "\"REQUERIDO\"";
        }else if(token.equalsIgnoreCase("OPTIONS")){
            return "\"OPCIONES\"";
        }else if(token.equalsIgnoreCase("ROWS")){
            return "\"FILAS\"";
        }else if(token.equalsIgnoreCase("COLS")){
            return "\"COLUMNAS\"";
        }else if(token.equalsIgnoreCase("URL")){
            return "\"URL\"";
        }else if(token.equalsIgnoreCase("NUMBER")){
            return "numero";
        }else if(token.equalsIgnoreCase("POSALIGNMENT")){
            return "\"CENTRO\"|\"IZQUIERDO\"|\"DERECHA\"|\"JUSTIFICAR\"";
        }else if(token.equalsIgnoreCase("BOOL")){
            return "\"SI\"|\"NO\"";
        }else if(token.equalsIgnoreCase("LESS")){
            return "<";
        }else if(token.equalsIgnoreCase("GREATER")){
            return ">";
        }else if(token.equalsIgnoreCase("COLON")){
            return ":";
        }else if(token.equalsIgnoreCase("OPEN_CURLY")){
            return "{";
        }else if(token.equalsIgnoreCase("CLOSE_CURLY")){
            return "}";
        }else if(token.equalsIgnoreCase("OPEN_BRACKET")){
            return "[";
        }else if(token.equalsIgnoreCase("CLOSE_BRACEKT")){
            return "]";
        }else if(token.equalsIgnoreCase("TEXTWS")){
            return "\"text_no_space\"";
        }else if(token.equalsIgnoreCase("TEXTWIS")){
            return "\"text with spaces\"";
        }else if(token.equalsIgnoreCase("COMA")){
            return ",";
        }
        
        
        return token;
    }
}
