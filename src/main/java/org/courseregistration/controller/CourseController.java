package org.courseregistration.controller;

<<<<<<< aa862c766f205259a243c0ada848db50b83b6437

import org.courseregistration.model.Course;
import org.courseregistration.service.CourseService;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

=======
import org.courseregistration.model.Course;
import org.courseregistration.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.DELETE;
>>>>>>> neha Course service
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
<<<<<<< aa862c766f205259a243c0ada848db50b83b6437
import java.awt.*;

@Service
@Path("courses")

public class CourseController {
    @Autowired
     private CourseService courseService;
    /**
     * Get details of a course
     *
     * @param id course identifier of the required course
     *
     * @response.representation.200.doc Details of course
     * @response.representation.200.mediaType application/json
     *
     * @response.representation.404.doc Requested course with id not found
     *
     * @return details of the course
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)

    public Course findCourseById(@PathParam("id") Long id) {
       return courseService.findById(id);
    }
=======
import javax.ws.rs.core.Response;
import java.util.List;

@Service
@Path("courses")
public class CourseController 
{
	 @Autowired
	    private CourseService courseService;

	    /**
	     * Get details of a specific course
	     *
	     * @param id course identifier of the required course
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
>>>>>>> neha Course service
}
