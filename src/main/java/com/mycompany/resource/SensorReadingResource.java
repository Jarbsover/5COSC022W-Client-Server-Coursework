/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.resource;

import com.mycompany.exceptions.LinkedResourceNotFoundException;
import com.mycompany.exceptions.SensorUnavailableException;
import com.mycompany.rooms.Rooms;
import com.mycompany.sensors.Sensor;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

/**
 * Coursework: 5COSC022W Client Server Architectures
 * @author Jia Bei Lu
 * Westminster ID: w2115801
 */

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorReadingResource {
    //storing sensors (roomid/sensorid/sensor)
    private static final Map<String, Map<String, Sensor>> sensors = new HashMap<>();

    private static final Map<String, Rooms> rooms = SensorRoom.rooms;
    
    //GET for sensor readings
    @GET
    public Response getReadings(@PathParam("roomId") String roomId, @PathParam("sensorId") String sensorId){
        //check room exist
        if (!rooms.containsKey(roomId)){
            throw new LinkedResourceNotFoundException("Room not found");
        }
        //check sensor exist
        if (!sensors.get(roomId).containsKey(sensorId)||!sensors.containsKey(roomId)){
            return Response.status(Response.Status.NOT_FOUND).entity("Sensor not found").build();
        }
        return Response.ok(
            sensors.get(roomId).get(sensorId).getReadings()
        ).build();
    }
    
    //POST for sensor readings
    
    @POST
    public Response addReadings(@PathParam("roomId") String roomId, @PathParam("sensorId") String sensorId, Double value){
        //check room exist
        if (!rooms.containsKey(roomId)){
            throw new LinkedResourceNotFoundException("Room does not exist");
        }
        //check sensor exist
        if (!sensors.containsKey(roomId) || !sensors.get(roomId).containsKey(sensorId)){
            return Response.status(Response.Status.NOT_FOUND).entity("Sensor not found").build();
        }
        //is there a reading
        if (value == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Reading value required").build();
        }
        
        Sensor sensor = sensors.get(roomId).get(sensorId);
        
        //if sensor is under maintenance ignore updates
        if ("MAINTENANCE".equalsIgnoreCase(sensor.getStatus())) {
            throw new SensorUnavailableException("Sensor is under maintenance");
        }
        
        sensor.getReadings().add(value);

        return Response.status(Response.Status.CREATED).entity(value).build();
    }
}
