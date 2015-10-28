package org.courseregistration.rest;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.google.common.collect.Lists;
import org.courseregistration.exception.ApplicationException;
import org.courseregistration.hateoas.ProfessorResourceWrapper;
import org.courseregistration.rest.writters.ProfessorAssembler;
import org.courseregistration.model.Professor;
import org.courseregistration.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.stereotype.Component;

import static com.google.common.collect.Lists.newArrayList;
import static org.courseregistration.rest.ResponseHelper.getCacheControl;

@Component
@Path("professors")
@PermitAll
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
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findProfessorById(@PathParam("id") Long id,
                                      @Context Request request) throws ApplicationException {
		Professor professor = professorService.findProfessorById(id);

        Resource<Professor> resource = new Resource<>(professor);
        Link selfRel = entityLinks.linkToSingleResource(Professor.class, professor.getId()).withSelfRel();
        resource.add(selfRel);

        CacheControl cc = getCacheControl();

        EntityTag tag = new EntityTag(Integer.toString(professor.hashCode()));

        Response.ResponseBuilder responseBuilder = request.evaluatePreconditions(tag);

        if (responseBuilder != null) {
            responseBuilder.cacheControl(cc);
            return responseBuilder.build();
        }
        responseBuilder = Response.ok(resource);
        responseBuilder.cacheControl(cc);
        responseBuilder.tag(tag);
        return responseBuilder.build();
    }

    /**
     * Get details of all professors in the system
     * @return details of professor
     */
	//@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllProfessors() {
		List<Professor> allProfessors = professorService.findAllProfessors();

        ProfessorAssembler professorAssembler = new ProfessorAssembler();
            List<ProfessorResourceWrapper> resources = professorAssembler.toResources(allProfessors);

        Resources<ProfessorResourceWrapper> wrapped = new Resources<>(resources);
        wrapped.add(JaxRsLinkBuilder.linkTo(ProfessorResource.class)
                .withSelfRel()
        );
        return Response.ok(wrapped).cacheControl(getCacheControl()).build();
	}

    @GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllProfessors(@QueryParam("page") @DefaultValue("0") int page,
                                      @QueryParam("size") @DefaultValue("2") int size,@Context UriInfo uriInfo) {

    List<Professor> allProfessors = professorService.findAllProfessors();
    ProfessorAssembler professorAssembler = new ProfessorAssembler();
    List<ProfessorResourceWrapper> resources = professorAssembler.toResources(allProfessors);

    List<ProfessorResourceWrapper> toShow = Lists.newArrayList();
    for(int i= page*size, j=0;j<size && i<resources.size(); i++,j++){
        toShow.add(resources.get(i));
    }

    int totalNumberOfPages = resources.size() / size;
    totalNumberOfPages = resources.size()%size != 0?totalNumberOfPages+1:totalNumberOfPages;

    List<Link> links = Lists.newArrayList();

    links.add(new Link(uriInfo.getAbsolutePathBuilder().queryParam("page",page+1).queryParam("size",size).build().toString(),Link.REL_NEXT));
    links.add(new Link(uriInfo.getAbsolutePathBuilder().queryParam("page",0).queryParam("size",size).build().toString(),Link.REL_FIRST));
    links.add(new Link(uriInfo.getAbsolutePathBuilder().queryParam("page",totalNumberOfPages).queryParam("size",size).build().toString(),Link.REL_LAST));
    if(page>0){
        links.add(new Link(uriInfo.getAbsolutePathBuilder().queryParam("page",page-1).queryParam("size",size).build().toString(),Link.REL_PREVIOUS));
    }

    PagedResources<ProfessorResourceWrapper> professorResourceWrappers = new PagedResources<>(
        toShow,
        new PagedResources.PageMetadata(
            size,
            page,
            resources.size(),
            (long) totalNumberOfPages),links
    );

    return Response.ok(professorResourceWrappers).cacheControl(getCacheControl()).build();
	}

    /**
    Adding single professor
     */

	@POST
    @Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"professor","admin"})
	public Response addProfessor(@Context UriInfo uriInfo, Professor professor)throws ApplicationException {
		professorService.addProfessor(professor);
        return Response.created(uriInfo.getAbsolutePathBuilder()
            .path(professor.getId().toString()).build())
            .entity("Professor successfully created")
            .build();
	}

    /**
     * Adding multiple professors
     */
    @POST
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    public Response addProfessor(List<Professor> professors)throws ApplicationException {
        professorService.addProfessors(professors);
        return Response.ok().entity(professors).build();
    }

    /**
     * Deleting single professor entry
     * @param professorId
     */
    @DELETE
	@Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"professor","admin"})
	public Response deleteProfessorById(@PathParam("id") Long professorId)throws ApplicationException{
		professorService.deleteProfessorById(professorId);
		return Response.ok(200).entity("Successfully deleted Professor with id:" + professorId).build();
	}

    /**
     * Deleting list of professors
     * @param ids
     */
    @DELETE
    @Path("list/{ids}")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"admin"})
    public Response deleteAllProfessors(@PathParam("ids") String ids)throws ApplicationException{
        String [] splitIds = ids.split(",");
        List<Long> toDelete = newArrayList();

        for(String str:splitIds){
            toDelete.add(new Long(str)) ;
        }
        professorService.deleteAllProfessors(toDelete);
        return Response.ok().entity("Deleted professors with ids : " + ids).build();

    }

    /**
     * Update professors details
     * @param id identifier for professor
     * @param professor deserialize professor object
     *
     */
	@PUT
	@Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"professor","admin"})
	public Response updateProfessor(@PathParam("id") Long id,Professor professor) throws ApplicationException{
		professorService.updateProfessor(id, professor);
		return Response.noContent().entity("Professor details updated").build();
	}


}

