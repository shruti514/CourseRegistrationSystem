package org.courseregistration.rest;

import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Component
@Path("/user")
public class UserResource {


    public Response registerUser(){
        return Response.ok().build();
    }

    @Path("login")
    public Response login(){
        return Response.ok().build();
    }

}
