/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clientserverapi;


import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import java.net.URI;

/**
 * Coursework: 5COSC022W Client Server Architectures
 * @author Jia Bei Lu
 * Westminster ID: w2115801
 */

public class ClientServerAPI {
    //the base URI for client requests
    public static final String BASE_URI = "http://localhost:8080/api/v1/";

    public static HttpServer serverSTART(){
        //config
        final ResourceConfig config = new ResourceConfig().packages("com.mycompany");
        
        //start server (baseURI + config)
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);
        
    }
    
    //start server and comfirmation message
    public static void main(String[] args){
        serverSTART();
        System.out.println("Server is running");
    }
}