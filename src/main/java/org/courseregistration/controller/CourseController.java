package org.courseregistration.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.courseregistration.model.Course;
import org.courseregistration.service.CourseService;
import org.springframework.stereotype.Service;

@Service
@Path("courses")
public class CourseController {

	private CourseService courseService;

	/**
	 * Get details of a specific course
	 *
	 * @param id
	 *            course identifier of the required course
	 *
	 *
	 * @response.representation.200.doc Details of course
	 * @response.representation.200.mediaType application/json
	 *
	 * @response.representation.404.doc Requested course with id not found
	 *
	 * @return details of a course
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course findCourseById(@PathParam("id") Long id) {
		return courseService.findById(id);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCourses() {
		List<Course> allCourses = courseService.findAllCourses();
		return Response.ok().entity(allCourses).build();
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