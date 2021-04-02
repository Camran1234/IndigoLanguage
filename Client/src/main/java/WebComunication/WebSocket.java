/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebComunication;

import Parameter.ParameterHandler;
import Tables.PackageResult;
import Tables.PrintResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import filemanager.FileHandler;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.io.Reader;
import java.io.StringReader;
import filemanager.*;


/**
 *
 * @author camran1234
 */
public class WebSocket {
    private final static String uriSentData = "http://localhost:8080/WebIndigo/resourcesIndigo";
    private final static String uriSentPetition = "http://localhost:8080/WebIndigo/sqlPetition";
    private final static String uriExitSession = "http://localhost:8080/WebIndigo/exitLogin";
    private final static String uriForms = "http://localhost:8080/WebIndigo/GetForms";
    private final static String uriFileCreate = "http://localhost:8080/WebIndigo/CreateFile";
    /**
     * Sent the Indigo Code to the server
     * @param textIndigo
     * @return
     * @throws Exception 
     */
    public String sentData(String textIndigo) throws Exception{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(uriSentData))
               .POST(BodyPublishers.ofString(textIndigo))
              .build();
        
        HttpResponse<String> response =
              client.send(request, BodyHandlers.ofString());
        
        System.out.println(response.body());
        String responseCode = response.body();
        Reader reader = new StringReader(responseCode);
        SqLexic lexic = new SqLexic(reader);
        ResponseSyntax syntax = new ResponseSyntax(lexic);
        syntax.parse();
        ArrayList<ParameterHandler> handler = syntax.getParametersHandlers();
        String result="";
        for(ParameterHandler parameter:handler){
            result += parameter.getAsStringLine();
        }
        
        return result;
    } 
    
    /**
     * Sent Sql code to the server
     * @param textoSql
     * @return
     * @throws Exception 
     */
    public ArrayList<PrintResult> sentPetitionSql(String textoSql) throws Exception{
        System.out.println("Enviando");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(uriSentPetition))
               .POST(BodyPublishers.ofString(textoSql))
              .build();
        
         HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting(); 
      
        String responseCode = response.body();
        System.out.println(responseCode);
        Reader reader = new StringReader(responseCode);
        SqLexic lexic = new SqLexic(reader);
        ResponseSyntax syntax = new ResponseSyntax(lexic);
        syntax.parse();
        ArrayList<ParameterHandler> handler = syntax.getParametersHandlers();
        ArrayList<PrintResult> result = new ArrayList<>();
        for(ParameterHandler parameter:handler){
            System.out.println("Agregando parametros sql");
            ArrayList<String> headers = new ArrayList<>();
            ArrayList<ArrayList<String>> cols = new ArrayList<>();
            parameter.getAsStringLine();
            headers = parameter.getRows();
            cols = parameter.getCol();
            System.out.println("Cols Size: "+cols.size());
            System.out.println("COLS size in: "+cols.get(0).size());
            result.add(new PrintResult(headers, cols));
        }
        return result;
    }
    
    public String[] getFormIds() throws Exception{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(uriForms))
               .GET()
              .build();
         HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        String responseCode = response.body();
        Reader reader = new StringReader(responseCode);
        SqLexic lexic = new SqLexic(reader);
        ResponseSyntax syntax = new ResponseSyntax(lexic);
        syntax.parse();
        ArrayList<ParameterHandler> handler = syntax.getParametersHandlers();
        ArrayList<String> forms = new ArrayList<>();
        for(ParameterHandler parameter:handler){
            System.out.println("Agregando parametros");
            parameter.getAsStringLine();
            forms = parameter.getText();
        }
         
        String[] answers = new String[forms.size()];
        for(int index=0; index<forms.size(); index++){
            System.out.println("FOrm: "+forms.get(index));
            answers[index] = forms.get(index);
        }
        
        return answers;
    }
    
    /**
     * Create a json file of a form with forms and components
     * @param path
     * @throws Exception 
     */
    public void createFile(String path) throws Exception{
        FileHandler file = new FileHandler();
        String text;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(uriFileCreate))
                .GET()
              .build();
         HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        String responseCode = response.body();
        file.writeFile(path, responseCode);
    }
    
    /**
     * Upload a file with json file of form
     * @param path
     * @throws Exception 
     */
    public void uploadFile(String path) throws Exception {
        
    }
    
    public void exitSession() throws Exception{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(uriExitSession))
               .GET()
              .build();
        HttpResponse<String> response =
              client.send(request, BodyHandlers.ofString());
    }
    
    public String getSession() throws Exception{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(uriSentData))
               .GET()
              .build();
        HttpResponse<String> response =
              client.send(request, BodyHandlers.ofString());
        return response.body();
    }
    
}
