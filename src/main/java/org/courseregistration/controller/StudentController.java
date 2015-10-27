package org.courseregistration.controller;

import com.google.common.collect.Lists;
import org.courseregistration.dao.StudentDAO;
import org.courseregistration.exception.ApplicationException;
import org.courseregistration.model.Section;
import org.courseregistration.model.Student;
import org.courseregistration.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Component
@Path("students")
@PermitAll
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

    // add single student
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStudent(Student s) throws ApplicationException {
         studentService.addStudent(s);
        return Response.ok(200).entity(s).build();
    }

    // add multiple students
    @POST
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"professor","admin"})
    public Response addStudents(List<Student> students) throws ApplicationException {
        studentService.addStudents(students);
        return Response.ok(200).entity(students).build();
    }

    //delete single student
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"professor","admin"})
    public Response deleteStudent(@PathParam("id") Long student_id) throws ApplicationException {
        studentService.deleteStudent(student_id);
        return Response.ok(201).entity(student_id).build();
    }


    //delete list of students
    @DELETE
    @Path("/list/{ids}")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"professor","admin"})
    public Response deleteAllStudents(@PathParam("ids") String ids) throws ApplicationException {
        String [] splitIds = ids.split(",");
        List<Long> toDelete = newArrayList();

        for(String str:splitIds){
            toDelete.add(new Long(str));
        }
        studentService.deleteAllStudents(toDelete);
        return Response.ok(200).entity("Deleted Students" + ids).build();
    }

    //get all students
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findStudents() throws ApplicationException {
        List<Student> allStudents = studentService.findAllStudents();
        return Response.ok().entity(allStudents).build();
    }

    //get student by id
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student findStudentById(@PathParam("id") Long id) throws ApplicationException {
        return studentService.findById(id);
    }


    //update student details
    @PUT
    @Path("update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStudent(@PathParam("id")Long id, Student s) throws ApplicationException {
        studentService.updateStudent(id, s);
        return Response.ok(200).entity("Student Details Updated").build();
    }

    //enroll to a section
    @POST
    @Path("{id}/sections/{section_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response enrollSection(@PathParam("id") Long id,@PathParam("section_id") Long section_id) throws ApplicationException {
         studentService.enrollSection(id, section_id);
        return Response.ok(200).entity("Enrolled to the Section").build();
    }

    //delete a single section
    @DELETE
    @Path("{id}/sections/{section_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dropSection(@PathParam("id") Long id, @PathParam("section_id")Long section_id) throws ApplicationException {
         studentService.dropSection(id, section_id);
        return Response.ok(200).entity("Dropped Section").build();
    }


}
