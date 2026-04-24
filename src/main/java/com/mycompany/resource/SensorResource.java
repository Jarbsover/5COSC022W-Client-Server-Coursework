/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.resource;

import com.mycompany.exceptions.LinkedResourceNotFoundException;
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

//Part 3 Subtask 1
@Path("/rooms/{roomId}/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {
    //storing sensors (roomid/sensorid/sensor)
    public static final Map<String, Map<String, Sensor>> sensors = new HashMap<>();

    private static final Map<String, Rooms> rooms = SensorRoom.rooms;

    //getters for sensors
    @GET
    public Response getSensors(@PathParam("roomId") String roomId,@QueryParam("type") String type){
        //check room exists
        if (!rooms.containsKey(roomId)){
            return Response.status(Response.Status.NOT_FOUND).entity("Room not found").build();
        }
        Collection<Sensor> sensorList = sensors.getOrDefault(roomId, new HashMap<>()).values();

        
        //Part 3 Subtask 2, filtering
        if (type != null){
            List<Sensor> filtered = new ArrayList<>();

            for (Sensor s : sensorList){
                if (type.equalsIgnoreCase(s.getType())){
                    filtered.add(s);
                }
            }
            return Response.ok(filtered).build();
        }
        return Response.ok(sensorList).build();
    }
    

    
    //post for sensors
    @POST
    public Response addSensor(@PathParam("roomId") String roomId, Sensor sensor){
        //check room exists
        if (!rooms.containsKey(roomId)){
            throw new LinkedResourceNotFoundException("Room not found");
        }
        
        //validate sensor ID
        if (sensor.getId() == null || sensor.getId().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Sensor ID required").build();
        }
        
        //map
        sensors.putIfAbsent(roomId, new HashMap<>());

        
        //check if duplicate sensor exists
        if (sensors.get(roomId).containsKey(sensor.getId())){
            return Response.status(Response.Status.CONFLICT).entity("Sensor already exists").build();
        }
        sensors.get(roomId).put(sensor.getId(), sensor);
        // Add sensor ID to room
        rooms.get(roomId).getSensorIds().add(sensor.getId());
        return Response.status(Response.Status.CREATED).entity(sensor).build();
    }
    
    //sub resource locator, sends off the requests to SensorReadingResource if the URI matches the path bellow
    @Path("/{sensorId}/readings")
    public SensorReadingResource getReadingResource() {
        //go into the SensorReadingResource for GET and POST methods
        return new SensorReadingResource();
    }
   
}
