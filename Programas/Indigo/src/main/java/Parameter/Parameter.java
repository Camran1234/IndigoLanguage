/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parameter;

/**
 * Types to recieve in the language response
 * Error
 * Text
 * Warning
 * Row
 * Col
 * @author camran1234
 */
public class Parameter {
    private String type;
    private String content;

    
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
}
