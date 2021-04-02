/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author camran1234
 */
public class PrintResult implements Serializable{
    private String idForm;
    private String nameConsult;
    private ArrayList<String> headers = new ArrayList<>();
    private ArrayList<ArrayList<String>> rows= new ArrayList<>();
    
    /*<!ini_solicitud:"CONSULTAR_DATOS">
      { "CONSULTAS":[{
            "CONSULTA-1": " SELECT TO FORM  ->  $form1  [ ] ",
           }         
         ]
      }
<fin_solicitud!>*/
    
    public PrintResult (){ }
    
    public PrintResult(ArrayList<String> headers, ArrayList<ArrayList<String>> rows){
        this.headers = headers;
        
        this.rows = rows;
    }
    
    public void setNewHeader(String header){
        headers.add(header);
    }
    
    public void setIdForm(String idForm){
        this.idForm = idForm;
    }
    
    public void setNameConsult(String name){
        this.nameConsult = name;
    }
    
    /**
     * Add a new Element to the actual row
     * @param row 
     */
    /*public void setRowList(ArrayList<ArrayList<Answer>> row){
        this.rows = row;
    }*/
    
    
    public String[] getHeadersList(){
        String[] object = new String[headers.size()];
        
        for(int index=0; index<headers.size(); index++){
            object[index] = headers.get(index);
        }
        
        return object;
    }
    
    public String[][] getRowsList(){
        String[][] object = new String[rows.get(0).size()][rows.size()];                
        System.out.println("SIZE ROW: "+rows.size());
        System.out.println("SIZEEE: "+rows.get(0).size());
        for(int indexA=0; indexA<rows.get(0).size(); indexA++){
            for(int indexB=0; indexB<rows.size(); indexB++){
                object[indexA][indexB] = rows.get(indexB).get(indexA);
            }
        }
        
        return object;
    }
    
    public int getCols(){
        return headers.size();
    }
    
    public int getRows(){
        return rows.size();
    }
    
}
