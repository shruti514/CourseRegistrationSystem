package org.courseregistration.controller;

import org.courseregistration.service.ProfessorService;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Path;

@Service
@Path("professor")

public class ProfessorController {
    @Autowired
    private ProfessorService professorservice;

    /**
     * Get details of a specific professor
     *
     * @param id professor identifier of the required professor
     *
     * @response.representation.200.doc Details of professor
     * @response.representation.200.mediaType application/json
     *
     * @response.representation.404.doc Requested professor with id not found
     *
     * @return details of a professor
     */
}
