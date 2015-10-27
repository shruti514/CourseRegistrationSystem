package org.courseregistration.hateoas;


import org.courseregistration.model.Student;
import org.springframework.hateoas.ResourceSupport;

public class StudentResource extends ResourceSupport {
    private Student student;


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


}
