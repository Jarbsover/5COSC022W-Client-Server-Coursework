/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

/**
 * Coursework: 5COSC022W Client Server Architectures
 * @author Jia Bei Lu
 * Westminster ID: w2115801
 */

//Task 1: Subtask 2 
//root entry point
@Path("/")
public class Discovery {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getApiInfo(){

        //response data when requested
        Map<String, Object> response = new HashMap<>();
        response.put("version", "v1");

        //submap of the avaliable api endpoints
        Map<String, String> resources = new HashMap<>();
        resources.put("rooms", "/api/v1/rooms");
        resources.put("sensors", "/api/v1/sensors");

        response.put("resources", resources);
        return response;
    }
    
    
    
}
