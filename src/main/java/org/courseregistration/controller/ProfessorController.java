package org.courseregistration.controller;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.stereotype.Service;

@Service
@Path("professors")
public class ProfessorController {
	@Autowired
	private ProfessorService professorService;

	/**
	 * Get details of a specific professor
	 *
	 * @param id
	 *            professor identifier of the required professor
	 * @return details of a professor
	 * @response.representation.200.doc Details of professor
	 * @response.representation.200.mediaType application/json
	 * @response.representation.404.doc Requested professor with id not found
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


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllProfessors() {
		List<Professor> allProfessors = professorService.findAllProfessors();
		return Response.ok().entity(allProfessors).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProfessor(Professor p) {
		professorService.addProfessor(p);
		return Response.ok(201).entity(p).build();
	}

	@DELETE
	@Path("{id}")
	public Response deleteProfessorById(@PathParam("id") Long professor_id) {
		professorService.deleteProfessorById(professor_id);
		return Response.ok(200).entity(professor_id).build();
	}

	@PUT
	@Path("{id}/password/update/{password}")
	public Response updateProfessorPassword(@PathParam("id") Long id,
			@PathParam("password") String password) {
		professorService.updateProfessorPassword(id, password);
		return Response.ok(200).build();
	}

	@PUT
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
	}
}
