package org.courseregistration.controller.writters;

import org.courseregistration.controller.ProfessorController;
import org.courseregistration.hateoas.ProfessorResourse;
import org.courseregistration.model.Professor;

public class ProfessorAssembler extends ResourceAssembler<Professor, ProfessorResourse> {

        public ProfessorAssembler() {
            super(ProfessorController.class, ProfessorResourse.class);
        }
        @Override
        public ProfessorResourse toResource(Professor entity) {
            ProfessorResourse resource = createResourceWithId(entity.getId(),entity);
            resource.setProfessor(entity);
            return resource;
        }
    }
