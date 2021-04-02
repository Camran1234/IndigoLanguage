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
public class Comparation {
    private String operator;
    private String camporId;
    private String compareSym;
    private String logicOperator;
    private String token;
    private String line;
    private String column;
    public void close(String token, String line, String column){
        this.token = token;
        this.line=line;
        this.column=column;
    }
    
    public String getLogicOperator(){
        return logicOperator;
    }
    
    public void setLogiOperator(String logicOperator){
        this.logicOperator = logicOperator;
    }
    
    public String getOperator() {
        return operator;
    }

    public String getCamporId() {
        return camporId;
    }

    public String getCompareSym() {
        return compareSym;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setCamporId(String camporId) {
        this.camporId = camporId;
    }

    public void setCompareSym(String compareSym) {
        this.compareSym = compareSym;
    }
    
    
}
