package org.courseregistration.controller;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.courseregistration.model.Professor;
import org.courseregistration.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import static com.google.common.collect.Lists.newArrayList;

@Component
@Path("professors")
@PermitAll
public class ProfessorController {
	@Autowired
	private ProfessorService professorService;

	/**
	 * Get details of a specific professor
	 * @response.representation.200.doc Details of professor
	 * @response.representation.200.mediaType application/json
	 * @response.representation.404.doc Requested professor with id not found
     * @return details of a professor
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findProfessorById(@PathParam("id") Long id) {
		Professor p = professorService.findProfessorById(id);
        if (p != null)
            return Response.ok(Response.Status.OK).entity(p).build();
        return Response.ok(Response.Status.NOT_FOUND).build();
    }

    /**
     * Get details of all professors in the system
     * @return details of professor
     */
	@GET
    @Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllProfessors() {
		List<Professor> allProfessors = professorService.findAllProfessors();
		return Response.ok().entity(allProfessors).build();
	}

    /**
    Adding a new professor's entry in the system
     */

	@POST
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"professor","admin"})
	public Response addProfessor(Professor p) {
		professorService.addProfessor(p);
		return Response.ok(201).entity(p).build();
	}

    /**
     * Addiding multiple professors' entry in the system
     */
    @POST
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    public Response addProfessor(List<Professor> professors) {
         professorService.addProfessors(professors);
        return Response.ok(200) .entity(professors).build();
    }

    /**
     * Deleting single professor entry
     * @param professor_id
     */
    @DELETE
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"professor","admin"})
	public Response deleteProfessorById(@PathParam("id") Long professor_id) {
		professorService.deleteProfessorById(professor_id);
		return Response.ok(200).entity(professor_id).build();
	}

    /**
     * Deleting list of professors
     * @param ids
     */
    @DELETE
    @Path("list/{ids}")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"admin"})
    public Response deleteAllProfessors(@PathParam("ids") String ids){
        String [] splitIds = ids.split(",");
        List<Long> toDelete = newArrayList();

        for(String str:splitIds){
            toDelete.add(new Long(str)) ;
        }
        professorService.deleteAllProfessors(toDelete);
        return Response.ok(200).entity("Deleted professors" + ids).build();

    }

    /**
     * Update professors details
     * @param id
     * @param p
     */
	@PUT
	@Path("update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"professor","admin"})
	public Response updateProfessor(@PathParam("id") Long id,Professor p) {
		professorService.updateProfessor(id, p);
		return Response.ok(200).entity("Professor details updated").build();
	}

/*	@PUT
	@Path("{id}/phone/update/{phone_number}")
	public Response updateProfessorPhone(@PathParam("id") Long id,
			@PathParam("phone_number") String phone_number) {
		professorService.updateProfessorPhone(id, phone_number);
		return Response.ok(200).build();
	}

	@PUT
	@Path("{id}/faculty/update/{faculty_type}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateProfessorFacultyType(@PathParam("id") Long id,
			@PathParam("faculty_type") String facultyType) {
		professorService.updateProfessorFacultyType(id, facultyType);
		return Response.ok(200).build();
	}    */
}

