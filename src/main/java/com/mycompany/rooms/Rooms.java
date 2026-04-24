/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rooms;
import java.util.*;

/**
 * Coursework: 5COSC022W Client Server Architectures
 * @author Jia Bei Lu
 * Westminster ID: w2115801
 */

// Part 2 creating the rooms
public class Rooms {
    private String id;
    public String getId(){return id;}
    public void setId(String id){this.id = id;}
    
    private String name;
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    
    private int capacity;
    public int getCapacity(){return capacity;}
    public void setCapacity(int capacity){this.capacity = capacity;}

    public Rooms(){}
    
    public Rooms(String id, String name, int capacity){
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    private List<String> sensorIds = new ArrayList<>();
    public List<String> getSensorIds(){return sensorIds;}
    public void setSensorIds(List<String> sensorIds){this.sensorIds = sensorIds;}
}
