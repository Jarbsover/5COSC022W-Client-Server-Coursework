/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.config;

import com.mycompany.filters.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
/**
 * Coursework: 5COSC022W Client Server Architectures
 * @author Jia Bei Lu
 * Westminster ID: w2115801
 */

//Part 1: Subtask 1
@ApplicationPath("/api/v1")
public class AppConfig extends ResourceConfig{
    public AppConfig() {
        packages("com.mycompany");
        //package converts java to json
        register(org.glassfish.jersey.jackson.JacksonFeature.class);
        //Part 5: Subtask 5
        register(LoggingFilter.class);
    }
}
