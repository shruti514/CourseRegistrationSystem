package org.courseregistration.controller;

import com.google.common.collect.Lists;
import org.courseregistration.controller.writters.CourseAssembler;
import org.courseregistration.exception.ApplicationException;
import org.courseregistration.hateoas.CourseResource;
import org.courseregistration.model.Course;
import org.courseregistration.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("courses")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({"admin","professor"})
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
	public Response findCourseById(@PathParam("id") Long id) throws ApplicationException {

        Course course = courseService.findById(id);

        Resource<Course>  resource = new Resource<>(course);
        Link selfRel = entityLinks.linkToSingleResource(Course.class, course.getId()).withSelfRel();
        resource.add(selfRel);

        return Response.ok(resource).build();
	}

	@GET
    @Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCourses() {
		List<Course> allCourses = courseService.findAllCourses();

        CourseAssembler courseAssembler = new CourseAssembler();
        List<CourseResource> resources = courseAssembler.toResources(allCourses);

        Resources<CourseResource> wrapped = new Resources<>(resources);
        wrapped.add(JaxRsLinkBuilder.linkTo(CourseController.class)
                .withSelfRel()
        );


        return Response.ok(wrapped).build();

	}

    @GET
    @Path("/paginate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCoursesPaged(
        @QueryParam("page") @DefaultValue("0") int page,
        @QueryParam("size") @DefaultValue("2") int size) {

        List<Course> allCourses = courseService.findAllCourses();
        CourseAssembler courseAssembler = new CourseAssembler();
        List<CourseResource> resources = courseAssembler.toResources(allCourses);

        List<CourseResource> toShow = Lists.newArrayList();
        for(int i= page*size, j=0;j<size && i<resources.size(); i++,j++){
            toShow.add(resources.get(i));
        }


        int totalNumberOfPages = resources.size() / size;
        totalNumberOfPages = resources.size()%size != 0?totalNumberOfPages+1:totalNumberOfPages;

        List<Link> links = Lists.newArrayList();
        Link selfRel = JaxRsLinkBuilder.linkTo(CourseController.class).withSelfRel();
        String selfRelHref = selfRel.getHref();
        links.add(new Link(new UriTemplate(selfRelHref+"?page="+(page+1)+"&size="+size),Link.REL_NEXT));
        if(page>0){
            links.add(new Link(new UriTemplate(selfRelHref+"?page="+(page-1)+"&size="+size),Link.REL_PREVIOUS));
        }

        links.add(new Link(new UriTemplate(selfRelHref+"?page=0&size="+size),Link.REL_FIRST));
        links.add(new Link(new UriTemplate(selfRelHref+"?page="+totalNumberOfPages+"&size="+size),Link.REL_LAST));

        PagedResources<CourseResource> courseResources = new PagedResources<>(
            toShow,
            new PagedResources.PageMetadata(
                size,
                page,
                resources.size(),
                (long) totalNumberOfPages),links
        );

        courseResources.add();
        return Response.ok(courseResources).build();
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
	public Response createCourse(Course course) {

		String result = "Course saved : " + course;
		courseService.save(course);
		return Response.status(201).entity(result).build();
	}
}
