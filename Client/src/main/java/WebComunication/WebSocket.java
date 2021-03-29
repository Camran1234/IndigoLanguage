/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebComunication;

import java.io.IOException;
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
import java.time.Duration;



/**
 *
 * @author camran1234
 */
public class WebSocket {
    private final static String uriSentData = "http://localhost:8080/WebIndigo/resourcesIndigo";
    private final static String uriExitSession = "http://localhost:8080/WebIndigo/exitLogin";
    
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
        String result = response.body();
        return result;
    } 
    
    public void exitSession() throws Exception{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(uriExitSession))
               .GET()
              .build();
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
