/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.resource;

import com.mycompany.exceptions.RoomNotEmptyException;
import com.mycompany.rooms.Rooms;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

/**
 * Coursework: 5COSC022W Client Server Architectures
 * @author Jia Bei Lu
 * Westminster ID: w2115801
 */

//Part 2: Subtask 1
@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class SensorRoom {
    //global storage for rooms
    public static Map<String, Rooms> rooms = new HashMap<>();
    
    //get list of all the rooms
    @GET
    public Response getAllRooms() {
        return Response.ok(new ArrayList<>(rooms.values())).build();
    }
    
    //get the list of specific rooms
    @GET
    @Path("/{roomId}")
    public Response getRoom(@PathParam("roomId") String roomId){
        Rooms room = rooms.get(roomId);

        if (room == null){
            return Response.status(Response.Status.NOT_FOUND).entity("Room not found").build();
        }
        return Response.ok(new ArrayList<>(rooms.values())).build();
    }

    //create new rooms
    @POST
    public Response setRoom(Rooms room){
        // Check if ID is missing
        if (room.getId() == null || room.getId().isEmpty()){
            return Response.status(Response.Status.BAD_REQUEST).entity("Room ID is required").build();
        }

        //check duplicate
        if (rooms.containsKey(room.getId())){
            return Response.status(Response.Status.CONFLICT).entity("Room already exists").build();
        }
        
        //save and return
        rooms.put(room.getId(), room);
        return Response.status(Response.Status.CREATED).entity(room).build();
    }
    
    //room deletion
    @DELETE
    @Path("/{roomId}")
    public Response deleteRoom(@PathParam("roomId") String roomId) {

        Rooms room = rooms.get(roomId);
        //check room exists
        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse("Room not found", 404)).build();
        }
        //dont delete rooms that have active sensors
        if (!room.getSensorIds().isEmpty()) {
            throw new RoomNotEmptyException("Room has active sensors");
        }

        rooms.remove(roomId);
        return Response.noContent().build();
    }   

    
}
    