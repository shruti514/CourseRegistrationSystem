package org.courseregistration.controller;

import org.courseregistration.model.Professor;
import org.courseregistration.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Service
@Path("professors")
public class ProfessorController {
    @Autowired
    private ProfessorService professorsrevice;

    /**
     * Get details of a specific professor
     *
     * @param id professor identifier of the required professor
     * @return details of a professor
     * @response.representation.200.doc Details of professor
     * @response.representation.200.mediaType application/json
     * @response.representation.404.doc Requested professor with id not found
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Professor findProfessorById(@PathParam("id") Long id) {
        return professorsrevice.findProfessorById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllProfessors() {
        List<Professor> allProfessors = professorsrevice.findAllProfessors();
        return Response.ok().entity(allProfessors).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProfessor(Professor p) {
        professorsrevice.addProfessor(p);
        return Response.ok(200).entity(p).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteProfessorById(@PathParam("id") Long id) {
        professorsrevice.deleteProfessorById(id);
        return Response.ok().entity(id).build();
    }

    @PUT
    @Path("{id}/update/{password}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateStudentPass(@PathParam("id") Long id, @PathParam("password") String password) {
        professorsrevice.updateProfessorPass(id, password);
        return Response.ok("Password Successfully changed").build();
    }

    @PUT
    @Path("{id}/update/{phone_number}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateProfessorPhone(@PathParam("id") Long id,@PathParam("phone_number") String phone_number){
        professorsrevice.updateProfessorPhone(id, phone_number);
        return Response.ok("Phone number updated successfully").build();
    }

    @PUT
    @Path("{id}/update/{faculty}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateProfessorFaculty(@PathParam("id")Long id,@PathParam("faculty") String faculty) {
        professorsrevice.updateProfessorFaculty(id,faculty);
        return Response.ok("Faculty type changed successfully").build();
    }
}
