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
public class PackageResult implements Serializable{
    private ArrayList<PrintResult> results;
    
    public PackageResult(ArrayList<PrintResult> result){
        if(result!=null){
            results = result;
        }else{
            results = new ArrayList<>();
        }
    }
    
    
    public ArrayList<PrintResult> getResults(){
        return results;
    }
    
    public void printInfo(){
       /* System.out.println("Leyendo Archivo");
        ArrayList<String> headers;
        ArrayList<ArrayList<Answer>> row;
        for(PrintResult result:results){
            headers = result.getHeadersList();
            System.out.println("Headers");
            for(String header:headers){
                System.out.print(header+"\t");
            }
            System.out.println("");
            row = result.getRowsList();
            
            if(row.size()==0){
                System.out.println("Es 0");
            }else{
                System.out.println("Entro");
                System.out.println("Row.get(0) = "+row.get(0).size());                    
                System.out.println("Row size = "+row.size());
                
                for(int index=0; index<row.get(0).size();index++){
                    for(ArrayList<Answer> answer:row){
                        System.out.print(answer.get(index).getResult()+"\t");
                    }
                    System.out.println("\t---");
                }
            }
            
        }
        if(results.size()==0){
            System.out.println("No hay Datos");
        }*/
    }
}
/*

<!ini_solicitud:"CONSULTAR_DATOS">
      { "CONSULTAS":[{
            "CONSULTA-1": " SELECT TO FORM  ->  $form1  [ ] ",
           }         
         ]
      }
<fin_solicitud!>
*/