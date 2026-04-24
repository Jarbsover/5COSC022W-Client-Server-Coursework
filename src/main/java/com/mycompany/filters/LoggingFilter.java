/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filters;

import javax.ws.rs.container.*;
import javax.ws.rs.ext.*;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Coursework: 5COSC022W Client Server Architectures
 * @author Jia Bei Lu
 * Westminster ID: w2115801
 */
@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {
    
    //outputs messages onto the console
    private static final Logger LOGGER = Logger.getLogger(LoggingFilter.class.getName());

    //intercepting requests before they reach the the resource class, logs method and target URI
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        LOGGER.info("Request: " + requestContext.getMethod() + " " + requestContext.getUriInfo().getRequestUri());
    }

    //intercepting requests after the resource class finishes processing, logs status code
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

        LOGGER.info("Response Status: " + responseContext.getStatus());
    }
}
