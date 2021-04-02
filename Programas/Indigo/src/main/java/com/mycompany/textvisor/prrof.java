/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.textvisor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 *
 * @author camran1234
 */
public class prrof {
    public static void main(String[] args){
        File directory = new File("Resources");
        System.out.println(directory.getAbsolutePath());
        String hola = "Hola mundo\n";
        String como = "Como estas\n";
        String a = "SIComo estas\n";
        try(FileInputStream outPutStream = new FileInputStream("src/main/java/Resources/data.txt")){
            String texto = new String(outPutStream.readAllBytes());
            System.out.println(texto);
            outPutStream.close();
        }catch(Throwable e){
            e.printStackTrace();
        }
    }
}
