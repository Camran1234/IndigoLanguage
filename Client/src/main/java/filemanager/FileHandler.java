/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filemanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.StringReader;
import javax.swing.JOptionPane;

/**
 *
 * @author camran1234
 */
public class FileHandler {
    
    public String readFile(String path){
        String text;
        File directory = new File(path);
        System.out.println(directory.getAbsolutePath());
        StringBuffer fileContent = new StringBuffer();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            //Reading file
            String sCurrentLine;
            while((sCurrentLine=br.readLine())!=null){
                fileContent.append(sCurrentLine).append("\n");
            }
            text = fileContent.toString();
            return text;
        }catch(Throwable e){
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
            return "";
        }
    }
    
    public void writeFile(String path, String text){
        StringBuffer fileContent = new StringBuffer();
        try(BufferedWriter br = new BufferedWriter(new FileWriter(path))){
            //Reading file
            br.write(text);
            JOptionPane.showMessageDialog(null, "Se exporto tu archivo a "+path);
        }catch(Throwable e){
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
    }
}
