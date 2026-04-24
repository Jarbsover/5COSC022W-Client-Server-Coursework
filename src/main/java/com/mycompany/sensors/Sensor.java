/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sensors;

import java.util.*;

/**
 * Coursework: 5COSC022W Client Server Architectures
 * @author Jia Bei Lu
 * Westminster ID: w2115801
 */
public class Sensor {

    private String id;
    public String getId(){return id;}
    public void setId(String id){this.id = id;}
    
    private String type;
    public String getType(){return type;}
    public void setType(String type){this.type = type;}
    
    private String unit;
    public String getUnit(){return unit;}
    public void setUnit(String unit){this.unit = unit;}
    
    private String status = "ACTIVE";
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Sensor(){}
 
    public Sensor(String id, String type, String unit, String status){
        this.id = id;
        this.type = type;
        this.unit = unit;
        this.status = status;
    }
    
    private List<Double> readings = new ArrayList<>();
    public List<Double> getReadings(){
        return readings;
    }
    
    public void setReadings(List<Double> readings){
        this.readings = readings;
    }
   
    
    
}
