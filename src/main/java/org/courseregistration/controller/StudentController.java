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
     * @response.representation.200.doc Details of student
     * @response.representation.200.mediaType application/json
     *
     * @response.representation.404.doc Requested student with id not found
     *
     * @return details of a student
     */

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSection(Student s) {
         studentService.addStudent(s);
        return Response.ok(200).entity(s).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteStudent(@PathParam("id") Long student_id) {
        studentService.deleteStudent(student_id);
        return Response.ok(201).entity(student_id).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findStudents() {
        List<Student> allStudents = studentService.findAllStudents();
        return Response.ok().entity(allStudents).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student findStudentById(@PathParam("id") Long id) {
        return studentService.findById(id);
    }

    @PUT
    @Path("{id}/contactnumber/update/{phone_number}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudentPhone(@PathParam("id") Long id, @PathParam("phone_number") String phone_number) {
      studentService.updateStudentPhone(id, phone_number);
        return Response.ok(200).entity("Phone Number Updated").build();
    }

    @PUT
    @Path("{id}/password/update/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudentPass(@PathParam("id") Long id, @PathParam("password") String password) {
        studentService.updateStudentPass(id, password);
        return Response.ok(200).entity("Password successfully changed").build();
    }

    @POST
    @Path("{student_id}/sections/{section_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response enrollSection(@PathParam("student_id") Long student_id,@PathParam("section_id") Long section_id) {
         studentService.enrollSection(student_id, section_id);
        return Response.ok(200).entity("Student Enrolled").build();
    }

    @DELETE
    @Path("{student_id}/sections/{section_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response dropSection(@PathParam("student_id/sections/section_id")Long student_id, Long section_id) {
         studentService.dropSection(student_id, section_id);
        return Response.ok(200).entity("Dropped Section").build();
    }

}
