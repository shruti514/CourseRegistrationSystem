package org.courseregistration.controller;


import org.courseregistration.model.Course;
import org.courseregistration.service.CourseService;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
}
