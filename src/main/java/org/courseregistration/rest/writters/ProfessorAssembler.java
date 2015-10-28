package org.courseregistration.rest.writters;

import org.courseregistration.rest.ProfessorResource;
import org.courseregistration.hateoas.ProfessorResourseWrapper;
import org.courseregistration.model.Professor;

public class ProfessorAssembler extends ResourceAssembler<Professor, ProfessorResourseWrapper> {

        public ProfessorAssembler() {
            super(ProfessorResource.class, ProfessorResourseWrapper.class);
        }
        @Override
        public ProfessorResourseWrapper toResource(Professor entity) {
            ProfessorResourseWrapper resource = createResourceWithId(entity.getId(),entity);
            resource.setProfessor(entity);
            return resource;
        }
    }
