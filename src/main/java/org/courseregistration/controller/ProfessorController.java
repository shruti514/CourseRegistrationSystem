package org.courseregistration.controller;

import java.util.List;

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
	public Professor findProfessorById(@PathParam("id") Long id) {
		return professorService.findProfessorById(id);
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
	public Response deleteProfessorById(@PathParam("id") Long id) {
		professorService.deleteProfessorById(id);
		return Response.ok().entity(id).build();
	}

	@PUT
	@Path("{id}/password/update/{password}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateProfessorPass(@PathParam("id") Long id,
			@PathParam("password") String password) {
		professorService.updateProfessorPass(id, password);
		return Response.ok("Password Successfully changed").build();
	}

	@PUT
	@Path("{id}/phone/update/{phone_number}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateProfessorPhone(@PathParam("id") Long id,
			@PathParam("phone_number") String phone_number) {
		professorService.updateProfessorPhone(id, phone_number);
		return Response.ok("Phone number updated successfully").build();
	}

	@PUT
	@Path("{id}/faculty/update/{faculty}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateProfessorFaculty(@PathParam("id") Long id,
			@PathParam("faculty") String faculty) {
		professorService.updateProfessorFaculty(id, faculty);
		return Response.ok("Faculty type changed successfully").build();
	}
}
