/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exceptions;

import com.mycompany.resource.ErrorResponse;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.*;

/**
 * Coursework: 5COSC022W Client Server Architectures
 * @author Jia Bei Lu
 * Westminster ID: w2115801
 */

@Provider
public class SensorUnavailableExceptionMapper implements ExceptionMapper<SensorUnavailableException>{

    @Override
    public Response toResponse(SensorUnavailableException ex){
        return Response.status(Response.Status.FORBIDDEN).entity(new ErrorResponse(ex.getMessage(), 403)).build();
    }
}
