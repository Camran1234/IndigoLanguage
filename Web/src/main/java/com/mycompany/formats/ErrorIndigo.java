/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.formats;

/**
 *
 * @author camran1234
 */
public class ErrorIndigo {
    private String messageError;
    private String token;
    private int line;
    private int column;
    
    public ErrorIndigo(String messageError, String token, int line, int column){
        this.messageError = messageError;
        this.token = token;
        this.line = line;
        this.column = column;
    }
    
    public String getMessage(){
        return ("Error en "+messageError+" en "+token+"\n cerca de Linea: "+line+", Columna: "+column);
    }
}
