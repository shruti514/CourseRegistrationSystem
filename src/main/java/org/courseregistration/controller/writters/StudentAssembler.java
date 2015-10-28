package org.courseregistration.controller.writters;

import org.courseregistration.controller.StudentController;
import org.courseregistration.hateoas.CourseResource;
import org.courseregistration.hateoas.StudentResource;
import org.courseregistration.model.Course;
import org.courseregistration.model.Student;

public class StudentAssembler extends ResourceAssembler<Student, StudentResource> {

    public StudentAssembler() {
        super(StudentController.class, StudentResource.class);
    }
    @Override
    public StudentResource toResource(Student entity) {

        StudentResource resource = createResourceWithId(entity.getId(),entity);
        resource.setStudent(entity);
        return resource;
    }
}
