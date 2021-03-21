/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.indigo;

import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author camran1234
 */
public class Analysis {
    
    /**
     * Compile the Indigo Language
     * @param entryText 
     */
    public void readText(String entryText){
        try {
            Reader reader = new StringReader(entryText);
            IndigoLex scanner = new IndigoLex(reader);
            IndigoSyntax parser = new IndigoSyntax(scanner);
            parser.parse();
            
        } catch (Exception ex) {
            System.out.println("ERROR: No se puede seguir traduciendo\n "+ex.getMessage());
            System.out.println("CAUSAS: ");
            ex.printStackTrace();
        }
        
    }
    
}
