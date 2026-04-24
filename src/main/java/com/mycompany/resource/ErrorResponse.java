/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.resource;

/**
 * Coursework: 5COSC022W Client Server Architectures
 * @author Jia Bei Lu
 * Westminster ID: w2115801
 */

public class ErrorResponse {
    
    private String error;
    private int status;

    public ErrorResponse(){}
    
    public ErrorResponse(String error, int status){
        this.error = error;
        this.status = status;
    }

    //getters for the errors and status
    public String getError(){return error;}
    public int getStatus(){return status;}
}
