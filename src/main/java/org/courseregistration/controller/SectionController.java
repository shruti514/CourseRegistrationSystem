package org.courseregistration.controller;

import java.util.HashMap;
import java.util.List;
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

import org.courseregistration.dao.SearchCriteria;
import org.courseregistration.model.Section;
import org.courseregistration.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Path("section")
public class SectionController {

	@Autowired
	private SectionService sectionService;

	/**
	 * Get details of a specific student
	 *
	 * @param id
	 *            student identifier of the required student
	 *
	 * @response.representation.200.doc Details of student
	 * @response.representation.200.mediaType application/json
	 *
	 * @response.representation.404.doc Requested student with id not found
	 *
	 * @return details of a student
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Section findSectionById(@PathParam("id") Long id) {
		return sectionService.findById(id);
	}

	/**
	 * Find Section By Section's Course Name
	 * @param name
	 * @return
	 */
	@GET
	@Path("/coursename/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSectionByName(@PathParam("name") String name) {
		List<Section> allStudents = sectionService.findByName(name);
		return Response.ok(200).entity(allStudents).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findStudents() {
		List<Section> allStudents = sectionService.findAllSections();
		return Response.ok(200).entity(allStudents).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSection(Section section) {

		boolean isSaved = sectionService.addSection(section);
		if (isSaved) {
			String result = "Section saved : " + section;
			return Response.status(201).entity(result).build();
		} else {
			String result = "Section is not saved" + section;
			return Response.status(400).entity(result).build();
		}
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteSection(@PathParam("id") Long section_id) {
		sectionService.deleteSection(section_id);
		return Response.ok(201).entity(section_id).build();
	}

	@PUT
	@Path("{section_id}/price/update/{price}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateSectionPrice(@PathParam("section_id") Long id,
			@PathParam("price") int price) {
		boolean isUpdated = sectionService.updateSectionPrice(id, price);
		if (isUpdated)
			return Response.ok(Response.Status.OK)
					.entity("Section Price updated").build();
		else
			return Response.ok(Response.Status.NOT_FOUND)
					.entity("Section Price is not updated").build();
	}

	@PUT
	@Path("{section_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateSection(@PathParam("section") long id, Section current) {
		boolean isUpdated = sectionService.updateSection(id, current);
		if (isUpdated)
			return Response.ok(Response.Status.OK)
					.entity("Section Price updated").build();
		else
			return Response.ok(Response.Status.NOT_FOUND)
					.entity("Section Price is not updated").build();
	}

	@GET
	@Path("/coursename:{name}/price:{price}/proflastname:{lastname}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findStudent(@PathParam("name") String name,
			@PathParam("price") int price,
			@PathParam("lastname") String lastname) {

		Map<SearchCriteria, String> criteria = new HashMap<SearchCriteria, String>();
		criteria.put(SearchCriteria.COURSE_NAME_CONTAINS, name);
		criteria.put(SearchCriteria.PROFESSOR_LAST_NAME_CONTAINS, lastname);
		criteria.put(SearchCriteria.SECTION_PRICE_EQUALS, "" + price);

		List<Section> allStudents = sectionService.findByCriteria(criteria);
		return Response.ok(200).entity(allStudents).build();
	}

	@GET
	@Path("/coursename:{name}/proflastname:{lastname}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findStudent(@PathParam("name") String name,
			@PathParam("lastname") String lastname) {

		Map<SearchCriteria, String> criteria = new HashMap<SearchCriteria, String>();
		criteria.put(SearchCriteria.COURSE_NAME_CONTAINS, name);
		criteria.put(SearchCriteria.PROFESSOR_LAST_NAME_CONTAINS, lastname);

		List<Section> allStudents = sectionService.findByCriteria(criteria);
		return Response.ok(200).entity(allStudents).build();
	}
}
