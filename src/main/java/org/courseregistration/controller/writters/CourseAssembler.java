package org.courseregistration.controller.writters;

import org.courseregistration.controller.CourseController;
import org.courseregistration.hateoas.CourseResource;
import org.courseregistration.model.Course;

public class CourseAssembler extends ResourceAssembler<Course, CourseResource> {

    public CourseAssembler() {
        super(CourseController.class, CourseResource.class);
    }
    @Override
    public CourseResource toResource(Course entity) {
        CourseResource resource = createResourceWithId(entity.getId(),entity);
        resource.setCourse(entity);
        return resource;
    }
}
