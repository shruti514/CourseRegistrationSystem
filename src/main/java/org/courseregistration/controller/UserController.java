package org.courseregistration.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Component
@Path("/user")
public class UserController {


    public Response registerUser(){
        return Response.ok().build();
    }

    @Path("login")
    public Response login(){
        return Response.ok().build();
    }

}
