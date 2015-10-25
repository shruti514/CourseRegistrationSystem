package org.courseregistration.controller;

import org.courseregistration.model.Professor;
import org.courseregistration.model.Student;
import org.courseregistration.service.ProfessorService;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Service
@Path("professor")

public class ProfessorController {
    @Autowired
    private ProfessorService professorsrevice;

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
        return professorsrevice.findById(id) ;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findProfessors(){
        List<Professor> allProfessors = professorsrevice.findAllProfessors();
        return Response.ok().entity(allProfessors).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProfessor(Professor p){
         professorsrevice.addProfessoor(p);
        return Response.ok().entity(p).build() ;
    }

}
