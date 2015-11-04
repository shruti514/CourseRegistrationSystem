package org.courseregistration.rest;

import com.google.common.collect.Lists;
import org.courseregistration.dao.StudentDAO;
import org.courseregistration.exception.ApplicationException;
import org.courseregistration.hateoas.ProfessorResourceWrapper;
import org.courseregistration.hateoas.StudentResourceWrapper;
import org.courseregistration.model.Professor;
import org.courseregistration.model.Student;
import org.courseregistration.rest.writters.ProfessorAssembler;
import org.courseregistration.rest.writters.StudentAssembler;
import org.courseregistration.service.StudentService;
import org.courseregistration.webconfig.CacheControlAnnotation;
import org.courseregistration.webconfig.CacheControlAnnotation.CacheMaxAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.google.common.collect.Lists.newArrayList;
import static org.courseregistration.rest.ResponseHelper.getCacheControl;

@Component
@Path("students")
@PermitAll
public class StudentResource {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentDAO studentDAO;

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
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"PROFESSOR","ADMIN"})
    public Response addStudent(@Context UriInfo uriInfo,Student student) throws ApplicationException {
        studentService.addStudent(student);
        return Response.created(uriInfo.getAbsolutePathBuilder()
            .path(student.getId().toString()).build())
            .entity("Student successfully created")
            .build();
    }

    // add multiple students
    @POST
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"PROFESSOR","ADMIN"})
    public Response addStudents(List<Student> students) throws ApplicationException {
        studentService.addStudents(students);
        return Response.ok(200).entity(students).build();
    }

    //delete single student
    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"PROFESSOR","ADMIN"})
    public Response deleteStudent(@PathParam("id") Long studentId) throws ApplicationException {
        studentService.deleteStudent(studentId);
        return Response.ok().entity("Successfully deleted student with id :"+ studentId).build();
    }

    //delete list of students
    @DELETE
    @Path("list/{ids}")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"PROFESSOR","ADMIN"})
    public Response deleteAllStudents(@PathParam("ids") String ids) throws ApplicationException {
        String [] splitIds = ids.split(",");
        List<Long> toDelete = newArrayList();

        for(String str:splitIds){
            toDelete.add(new Long(str));
        }
        studentService.deleteAllStudents(toDelete);
        return Response.ok().entity("Deleted Students" + ids).build();
    }

    //get all students
    //@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findStudents() throws ApplicationException {
        List<Student> allStudents = studentService.findAllStudents();

        StudentAssembler studentAssembler = new StudentAssembler();
        List<StudentResourceWrapper> resources = studentAssembler.toResources(allStudents);

        Resources<StudentResourceWrapper> wrapped = new Resources<>(resources);
        wrapped.add(JaxRsLinkBuilder.linkTo(StudentResource.class)
                .withSelfRel()
        );
        return Response.ok(wrapped).build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findStudentsPaged(@QueryParam("page") @DefaultValue("1") int page,
                                      @QueryParam("size") @DefaultValue("2") int size,
                                      @Context UriInfo uriInfo,@Context Request request) throws ApplicationException {

        if(page<1 || size<1){
            return Response.status(400).build();
        }

        List<Student> allStudents = studentService.findAllStudents();
        StudentAssembler studentAssembler = new StudentAssembler();
        List<StudentResourceWrapper> resources = studentAssembler.toResources(allStudents);

        List<StudentResourceWrapper> toShow = Lists.newArrayList();
        for(int i=(page-1)*size, j=0;j<size && i<resources.size(); i++,j++){
            toShow.add(resources.get(i));
        }

        int totalNumberOfPages = resources.size() / size;
        totalNumberOfPages = resources.size()%size != 0?totalNumberOfPages+1:totalNumberOfPages;

        //List<Link> links = Lists.newArrayList();

        /*links.add(new Link(uriInfo.getAbsolutePathBuilder().queryParam("page",page+1).queryParam("size",size).build().toString(),Link.REL_NEXT));
        links.add(new Link(uriInfo.getAbsolutePathBuilder().queryParam("page",1).queryParam("size",size).build().toString(),Link.REL_FIRST));
        links.add(new Link(uriInfo.getAbsolutePathBuilder().queryParam("page",totalNumberOfPages).queryParam("size",size).build().toString(),Link.REL_LAST));
        if(page>1){
            links.add(new Link(uriInfo.getAbsolutePathBuilder().queryParam("page",page-1).queryParam("size",size).build().toString(),Link.REL_PREVIOUS));
        }*/

        List<Link> links = this.getPaginationLinks(page,size,uriInfo,totalNumberOfPages);

        PagedResources<StudentResourceWrapper> studentResourceWrappers = new PagedResources<>(
            toShow,
            new PagedResources.PageMetadata(
                size,
                page,
                resources.size(),
                (long) totalNumberOfPages),links
        );

        return Response.ok(studentResourceWrappers).cacheControl(getCacheControl()).build();
    }

    //get student by id
    @GET
    @Path("{id}")
    @CacheMaxAge(time = 10, unit = TimeUnit.MINUTES, isPrivate=false,noStore=false)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findStudentById(@Context UriInfo uriInfo,@PathParam("id") Long id,
                                    @Context Request request) throws ApplicationException {
        Student student = studentService.findById(id);
        StudentAssembler studentAssembler = new StudentAssembler();
        StudentResourceWrapper resources = studentAssembler.toResource(student);


        int hashCode = student.hashCode();

        EntityTag tag = new EntityTag(Integer.toString(hashCode));

        Response.ResponseBuilder responseBuilder = request.evaluatePreconditions(tag);

        if (responseBuilder != null) {
            responseBuilder.lastModified(student.getUpdatedAt());
            return responseBuilder.build();
        }
        responseBuilder = Response.ok(resources);
        responseBuilder.lastModified(student.getUpdatedAt());
        responseBuilder.tag(tag);
        return responseBuilder.build();
    }

    //update student details
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateStudent(@PathParam("id")Long id, Student s,
                                  @Context Request request) throws ApplicationException {
        Student fromDB = studentDAO.findById(id);

        EntityTag tag = new EntityTag(Integer.toString(fromDB.hashCode()));
        Date timestamp = fromDB.getUpdatedAt();

        Response.ResponseBuilder builder =request.evaluatePreconditions(timestamp, tag);
        if (builder != null) {
            return builder.build();
        }
        Student stud = studentService.updateStudent(id,fromDB, s);
        return Response.noContent().entity("Successfully updated Student with id :"+stud.getId()).build();
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

    private List<Link> getPaginationLinks(int page,int size,UriInfo uriInfo, int totalNumberOfPages) {

        List<Link> links = Lists.newArrayList();
        if(page == totalNumberOfPages){
            links.add(new Link(uriInfo.getAbsolutePathBuilder().queryParam("page",page).queryParam("size",size).build().toString(),Link.REL_NEXT));
        }else{
            links.add(new Link(uriInfo.getAbsolutePathBuilder().queryParam("page",page+1).queryParam("size",size).build().toString(),Link.REL_NEXT));
        }
        links.add(new Link(uriInfo.getAbsolutePathBuilder().queryParam("page",1).queryParam("size",size).build().toString(),Link.REL_FIRST));
        links.add(new Link(uriInfo.getAbsolutePathBuilder().queryParam("page",totalNumberOfPages).queryParam("size",size).build().toString(),Link.REL_LAST));
        if(page>1){
            links.add(new Link(uriInfo.getAbsolutePathBuilder().queryParam("page",page-1).queryParam("size",size).build().toString(),Link.REL_PREVIOUS));
        }
        return links;
    }


}
