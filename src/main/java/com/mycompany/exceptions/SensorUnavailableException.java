/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exceptions;

import javax.ws.rs.ext.*;

/**
 * Coursework: 5COSC022W Client Server Architectures
 * @author Jia Bei Lu
 * Westminster ID: w2115801
 */

@Provider
public class SensorUnavailableException extends RuntimeException{
    public SensorUnavailableException(String errorMessage){
        super(errorMessage);
    }
}
