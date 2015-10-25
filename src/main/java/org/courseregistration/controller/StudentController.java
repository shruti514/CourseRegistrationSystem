package org.courseregistration.controller;

import com.google.common.collect.Lists;
import org.courseregistration.dao.StudentDAO;
import org.courseregistration.model.Student;
import org.courseregistration.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
@Path("students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * Get details of a specific student
     *
     * @param id student identifier of the required student
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
    public Student findStudentById(@PathParam("id") Long id) {
       return studentService.findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStudent(Student s) {
         studentService.addStudent(s);
        return Response.ok(200).entity(s).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findStudents() {
        List<Student> allStudents = studentService.findAllStudents();
        return Response.ok().entity(allStudents).build();
    }





}
