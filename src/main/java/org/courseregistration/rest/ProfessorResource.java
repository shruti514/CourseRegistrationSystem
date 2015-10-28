package org.courseregistration.rest;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.common.collect.Lists;
import org.courseregistration.exception.ApplicationException;
import org.courseregistration.rest.writters.CourseAssembler;
import org.courseregistration.rest.writters.ProfessorAssembler;
import org.courseregistration.hateoas.ProfessorResourseWrapper;
import org.courseregistration.model.Professor;
import org.courseregistration.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.*;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.stereotype.Component;

import static com.google.common.collect.Lists.newArrayList;

@Component
@Path("professors")
@PermitAll
@ExposesResourceFor(Professor.class)
public class ProfessorResource {
	@Autowired
	private ProfessorService professorService;
    @Autowired
    private EntityLinks entityLinks;

	/**
	 * Get details of a specific professor
	 * @response.representation.200.doc Details of professor
	 * @response.representation.200.mediaType application/json
	 * @response.representation.404.doc Requested professor with id not found
     * @return details of a professor
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findProfessorById(@PathParam("id") Long id) throws ApplicationException{

        Professor professor = professorService.findProfessorById(id);

        Resource<Professor> resource  = new Resource<>(professor)   ;
        Link selfRel =entityLinks.linkToSingleResource(Professor.class, professor.getId()).withSelfRel();
        resource.add(selfRel);

        return Response.ok(resource).build();
    }

    /**
     * Get details of all professors in the system
     * @return details of professor
     */
	@GET
    @Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllProfessors() {
		List<Professor> allProfessors = professorService.findAllProfessors();

        ProfessorAssembler professorAssembler = new ProfessorAssembler();
        List<ProfessorResourseWrapper> resources = professorAssembler.toResources(allProfessors);

        Resources<ProfessorResourseWrapper> wrapped = new Resources<>(resources);
        wrapped.add(JaxRsLinkBuilder.linkTo(ProfessorResource.class)
                .withSelfRel()
        );


        return Response.ok(wrapped).build();
	}

    /**
    Adding single professor
     */

	@POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"professor","admin"})
	public Response addProfessor(Professor p) {
		professorService.addProfessor(p);
		return Response.ok(201).entity(p).build();
	}

    /**
     * Adding multiple professors
     */
    @POST
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    public Response addProfessor(List<Professor> professors) {
         professorService.addProfessors(professors);
        return Response.ok(201) .entity(professors).build();
    }

    /**
     * Deleting single professor entry
     * @param professor_id
     */
    @DELETE
	@Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"professor","admin"})
	public Response deleteProfessorById(@PathParam("id") Long professor_id) {
		professorService.deleteProfessorById(professor_id);
		return Response.ok(200).entity(professor_id).build();
	}

    /**
     * Deleting list of professors
     * @param ids
     */
    @DELETE
    @Path("/list/{ids}")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"admin"})
    public Response deleteAllProfessors(@PathParam("ids") String ids){
        String [] splitIds = ids.split(",");
        List<Long> toDelete = newArrayList();

        for(String str:splitIds){
            toDelete.add(new Long(str)) ;
        }
        professorService.deleteAllProfessors(toDelete);
        return Response.ok(200).entity("Deleted professors" + ids).build();

    }

    /**
     * Update professors details
     * @param id
     * @param p
     */
	@PUT
	@Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"professor","admin"})
	public Response updateProfessor(@PathParam("id") Long id,Professor p) {
		professorService.updateProfessor(id, p);
		return Response.ok(200).entity("Professor details updated").build();
	}

    @GET
    @Path("/paginate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findProfessorPaged(
        @QueryParam("page") @DefaultValue("0") int page,
        @QueryParam("size") @DefaultValue("2") int size){

        List<Professor> allProfessors = professorService.findAllProfessors();
        ProfessorAssembler professorAssembler = new ProfessorAssembler();
        List<ProfessorResourseWrapper> resources = professorAssembler.toResources(allProfessors);

        List<ProfessorResourseWrapper> toShow = Lists.newArrayList();
        for(int i= page*size ,j=0;j<size && i<resources.size();i++,j++)   {
            toShow.add(resources.get(i)) ;
        }

        int totalNumberOfPages =resources.size()/size;
        totalNumberOfPages= resources.size()%size != 0?totalNumberOfPages+1:totalNumberOfPages;

        List<Link> links = Lists.newArrayList();
        Link selfRel = JaxRsLinkBuilder.linkTo(ProfessorResource.class).withSelfRel();
        String selfRelHref = selfRel.getHref();
        links.add(new Link(new UriTemplate(selfRelHref+"?Page="+(page+1)+"&size="+size),Link.REL_NEXT));
        if (page>0) {
            links.add(new Link(new UriTemplate(selfRelHref+"?page="+(page-1)+"&size="+size),Link.REL_PREVIOUS));
        }

        PagedResources<ProfessorResourseWrapper> professorResources = new PagedResources<>(
              toShow,
            new PagedResources.PageMetadata(
                size,
                page,
                resources.size(),
                (long)totalNumberOfPages),links
        );
        professorResources.add();
        return Response.ok(professorResources).build();
    }


}

