package org.courseregistration.controller;

import javax.ws.rs.Path;


import org.courseregistration.model.Professor;
import org.courseregistration.service.ProfessorService;
import org.courseregistration.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Service
@Path("professors")
public class ProfessorController {
    @Autowired
    private ProfessorService professorservice;


    /**
     * Get details of a specific professor
     *
     * @param id professor identifier of the required professor
     *
     * @response.representation.200.doc Details of professor
     * @response.representation.200.mediaType application/json
     *
     * @response.representation.404.doc Requested professor with id not found
     *
     * @return details of a professor
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Professor findProfessorById(@PathParam("id") Long id) {
        return professorservice.findById(id) ;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findProfessors(){
        List<Professor> allProfessors = professorservice.findAllProfessors();
        return Response.ok().entity(allProfessors).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProfessor(Professor p){
         professorservice.addProfessor(p);
        return Response.ok().entity(p).build() ;
    }

}
