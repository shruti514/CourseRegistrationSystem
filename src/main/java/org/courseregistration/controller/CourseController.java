package org.courseregistration.controller;

import org.courseregistration.model.Course;
import org.courseregistration.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.core.ControllerEntityLinks;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Service
@Path("courses")
@ExposesResourceFor(Course.class)
public class CourseController {
    @Autowired
	private CourseService courseService;
    @Autowired
    private EntityLinks entityLinks;


	/**
	 * Get details of a specific course
	 * @param id
	 * course identifier of the required course
	 * @response.representation.200.doc Details of course
	 * @response.representation.200.mediaType application/json
	 *
	 * @response.representation.404.doc Requested course with id not found

	 * @return details of a course
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCourseById(@PathParam("id") Long id) {
        Course course = courseService.findById(id);

        Resource<Course>  resource = new Resource<>(course);
        Link selfRel = entityLinks.linkToSingleResource(Course.class, course.getId()).withSelfRel();

        resource.add(selfRel);

        return Response.ok(resource).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCourses() {
		List<Course> allCourses = courseService.findAllCourses();

        Resources<Course> resources = new Resources<>(
            allCourses,
            JaxRsLinkBuilder
                .linkTo(CourseController.class)
                .withSelfRel()
        );

        return Response.ok(resources).build();

	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteCourseById(@PathParam("id") Long id) {
		courseService.deleteById(id);
	}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Course course) {

		String result = "Course saved : " + course;
		courseService.save(course);
		return Response.status(201).entity(result).build();
	}
}
