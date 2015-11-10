package org.courseregistration.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.*;

import com.google.common.collect.Maps;
import org.courseregistration.dao.SearchCriteria;
import org.courseregistration.exception.ApplicationException;
import org.courseregistration.hateoas.SectionResourceWrapper;
import org.courseregistration.model.Section;
import org.courseregistration.rest.writters.SectionAssembler;
import org.courseregistration.service.SectionService;
import org.courseregistration.webconfig.CacheControlAnnotation;
import org.courseregistration.webconfig.CacheControlAnnotation.CacheMaxAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.stereotype.Component;

import static org.courseregistration.rest.ResponseHelper.getCacheControl;

@Component
@Path("sections")
@PermitAll
@ExposesResourceFor(Section.class)
public class SectionResource {

	@Autowired
	private SectionService sectionService;
	@Autowired
	private EntityLinks entityLinks;

	/**
	 * Get details of a specific Section
	 *
	 * @param id
	 *            student identifier of the required student
	 *
	 * @response.representation.200.doc Details of Section
	 * @response.representation.200.mediaType application/json
	 * @response.representation.404.doc Requested Section with id not found
	 *
	 * @return details of a Section
	 */
	@GET
	@Path("/{id}")
    @CacheMaxAge(time = 10, unit = TimeUnit.MINUTES, isPrivate=false,noStore=false)
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSectionById(@PathParam("id") Long id,
                                    @Context Request request)
			throws ApplicationException {
		Section section = sectionService.findById(id);
		if (section != null) {
			Resource<Section> resource = new Resource<>(section);
			Link selfRel = entityLinks.linkToSingleResource(Section.class,
					section.getId()).withSelfRel();
			resource.add(selfRel);

            int hashCode = section.hashCode();

            EntityTag tag = new EntityTag(Integer.toString(hashCode));

            Response.ResponseBuilder responseBuilder = request.evaluatePreconditions(tag);

            if (responseBuilder != null) {
                responseBuilder.lastModified(section.getUpdatedAt());
                return responseBuilder.build();
            }
            responseBuilder = Response.ok(resource);
            responseBuilder.lastModified(section.getUpdatedAt());
            responseBuilder.tag(tag);
            return responseBuilder.build();

		}
		return Response.ok(Response.Status.NOT_FOUND).build();
	}

	/**
	 * Find Section By Section's Course Name
	 *
	 * @param name
	 * @return Response.Status.OK with Details of Section
	 */
	@GET
	@Path("/coursename:{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSectionByName(@PathParam("name") String name) {
		List<Section> allSections = sectionService.findByName(name);
		return Response.ok(200).entity(allSections).build();
	}

	/**
	 * Find all Section
	 *
	 * @return Response.Status.OK with List of Sections
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSections() {
		List<Section> allSections = sectionService.findAllSections();

		SectionAssembler sectionAssembler = new SectionAssembler();
		List<SectionResourceWrapper> resources = sectionAssembler
				.toResources(allSections);

		Resources<SectionResourceWrapper> wrapped = new Resources<>(resources);
		wrapped.add(JaxRsLinkBuilder.linkTo(SectionResource.class)
				.withSelfRel());

		return Response.ok(wrapped).cacheControl(getCacheControl()).build();
	}

	/**
	 * Creates a Section
	 *
	 * @param section
	 * @return
	 * @throws ApplicationException
	 */
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({"PROFESSOR","ADMIN"})
	public Response addSection(Section section) throws ApplicationException {

		boolean isSaved = sectionService.addSection(section);
		if (isSaved) {
			String result = "Section saved : " + section;
			return Response.status(Response.Status.CREATED).entity(result)
					.build();
		} else {
			String result = "Section is not saved" + section;
			return Response.status(400).entity(result).build();
		}
	}

	/**
	 * Deletes a section by ID
	 *
	 * @param section_id
	 * @return
	 */
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"PROFESSOR","ADMIN"})
	public Response deleteSection(@PathParam("id") Long section_id)
			throws ApplicationException {
		sectionService.deleteSection(section_id);
		return Response.ok(201).entity(section_id).build();
	}

	/**
	 * Updates the price of course from section
	 *
	 * @param id
	 * @param price
	 * @return OK Response with message, or NOT_FOUND response with message
	 */
	@PUT
	@Path("/{section_id}/price/update/{price}")
	@Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"PROFESSOR","ADMIN"})
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

	/**
	 * Updates the Section
	 *
	 * @param id
	 * @param current
	 * @return OK Response with message, or NOT_FOUND response with message
	 */
	@PUT
	@Path("{sectionId}")
	@Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"PROFESSOR","ADMIN"})
	public Response updateSection(@PathParam("sectionId") long id, Section current)
			throws ApplicationException {
		boolean isUpdated = sectionService.updateSection(id, current);
		if (isUpdated)
			return Response.ok(Response.Status.OK)
					.entity("Section Price updated").build();
		else
			return Response.ok(Response.Status.NOT_FOUND)
					.entity("Section Price is not updated").build();
	}

	/**
	 * Get section List by course name and professor name and price for course
	 *
	 * @param name
	 * @param price
	 * @param lastname
	 * @return Ok response with Section List
	 */
	@GET
	@Path("/coursename:{name}/price:{price}/proflastname:{lastname}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSection(@PathParam("name") String name,
			@PathParam("price") int price,
			@PathParam("lastname") String lastname) {

		Map<SearchCriteria, String> criteria = new HashMap<SearchCriteria, String>();
		criteria.put(SearchCriteria.COURSE_NAME_CONTAINS, name);
		criteria.put(SearchCriteria.PROFESSOR_LAST_NAME_CONTAINS, lastname);
		criteria.put(SearchCriteria.SECTION_PRICE_EQUALS, "" + price);

		List<Section> allSections = sectionService.findByCriteria(criteria);

		SectionAssembler sectionAssembler = new SectionAssembler();
		List<SectionResourceWrapper> resources = sectionAssembler
				.toResources(allSections);

		Resources<SectionResourceWrapper> wrapped = new Resources<>(resources);
		wrapped.add(JaxRsLinkBuilder.linkTo(SectionResource.class)
				.withSelfRel());

		return Response.ok(wrapped).build();
	}

	/**
	 * Get section List by course name and professor name
	 *
	 * @param name
	 * @param lastname
	 * @return Ok response with Section List
	 */
	@GET
	@Path("/coursename:{name}/proflastname:{lastname}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSection(@PathParam("name") String name,
			@PathParam("lastname") String lastname) {

		Map<SearchCriteria, String> criteria = new HashMap<SearchCriteria, String>();
		criteria.put(SearchCriteria.COURSE_NAME_CONTAINS, name);
		criteria.put(SearchCriteria.PROFESSOR_LAST_NAME_CONTAINS, lastname);

		List<Section> allSections = sectionService.findByCriteria(criteria);
		SectionAssembler sectionAssembler = new SectionAssembler();
		List<SectionResourceWrapper> resources = sectionAssembler
				.toResources(allSections);

		Resources<SectionResourceWrapper> wrapped = new Resources<>(resources);
		wrapped.add(JaxRsLinkBuilder.linkTo(SectionResource.class)
				.withSelfRel());

		return Response.ok(wrapped).build();
	}

	/**
	 * Using query parameters, search sections using parameters
	 *
	 * @param coursename
	 * @param lastname
	 * @param price
	 * @param dayofweek
	 * @param semester
	 * @param coursecode
	 * @return Response.ok(Response.Status.OK) with allSections
	 */
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSectionsQueryParams(
			@QueryParam("coursename") String coursename,
			@QueryParam("lastname") String lastname,
			@QueryParam("price") int price,
			@QueryParam("gteprice") int gteprice,
			@QueryParam("lteprice") int lteprice,
			@QueryParam("dayofweek") String dayofweek,
			@QueryParam("semester") String semester,
			@QueryParam("coursecode") String coursecode,
            @Context UriInfo uriInfo)
			throws ApplicationException {
		Map<SearchCriteria, String> criteria = new HashMap<SearchCriteria, String>();
		String priceStr = "" + price;
		String gtepriceStr = "" + gteprice;
		String ltepriceStr = "" + lteprice;

        Map<String,Object> queryParams = Maps.newHashMap();

		if (semester != null && !semester.isEmpty()) {
			criteria.put(SearchCriteria.SEMESTER_EQUALS, semester);
            queryParams.put("semester",semester);
		}
		if (dayofweek != null && !dayofweek.isEmpty()) {
			criteria.put(SearchCriteria.DAY_OF_WEEK_EQUALS, dayofweek);
            queryParams.put("dayofweek",dayofweek);
		}
		if (lastname != null && !lastname.isEmpty()) {
			criteria.put(SearchCriteria.PROFESSOR_LAST_NAME_CONTAINS, lastname);
            queryParams.put("lastname",lastname);
		}
		if (coursename != null && !coursename.isEmpty()) {
			criteria.put(SearchCriteria.COURSE_NAME_CONTAINS, coursename);
            queryParams.put("coursename",coursename);
		}
		if (coursecode != null && !coursecode.isEmpty()) {
			criteria.put(SearchCriteria.COURSE_CODE_EQUALS, coursecode);
            queryParams.put("coursecode",coursecode);
		}
		if (!priceStr.trim().isEmpty() && !priceStr.equalsIgnoreCase("null")
            &&!priceStr.equals("0")) {
			criteria.put(SearchCriteria.SECTION_PRICE_EQUALS, "" + price);
            queryParams.put("price",priceStr);
		}
		if (!gtepriceStr.trim().isEmpty()
				&& !gtepriceStr.equalsIgnoreCase("null")
                && !gtepriceStr.equals("0")) {
			criteria.put(SearchCriteria.SECTION_PRICE_GREATER_EQUALS, ""
					+ gtepriceStr);
            queryParams.put("gteprice",gtepriceStr);
		}
		if (!ltepriceStr.trim().isEmpty()
				&& !ltepriceStr.equalsIgnoreCase("null")
                && !ltepriceStr.equals("0")) {
			criteria.put(SearchCriteria.SECTION_PRICE_LESS_EQUALS, ""
					+ ltepriceStr);
            queryParams.put("lteprice",ltepriceStr);
		}
		if (criteria.size() > 0) {

			List<Section> allSections = sectionService.findByCriteria(criteria);

			SectionAssembler sectionAssembler = new SectionAssembler();
			List<SectionResourceWrapper> resources = sectionAssembler
					.toResources(allSections);

			Resources<SectionResourceWrapper> wrapped = new Resources<>(
					resources);

            UriBuilder linkBuilder = uriInfo.getAbsolutePathBuilder();
            for(Map.Entry<String,Object> entry:queryParams.entrySet()){
                linkBuilder.queryParam(entry.getKey(),entry.getValue());
            }

            Link link = new Link(linkBuilder.build().toString(),Link.REL_NEXT);
            wrapped.add(link);

			return Response.ok(wrapped).build();
		}

		throw ApplicationException
				.createNew()
				.withCode(Response.Status.BAD_REQUEST.getStatusCode())
				.withTitle("No Search parameters are defined")
				.withStatus(Response.Status.BAD_REQUEST.getStatusCode())
				.withDetail(
						"The Query Parameters are not provided. Please provide at least one param from following.\n coursename\n lastname\n price\n dayofweek\n semester\n coursecode")
				.build();

	}
}
