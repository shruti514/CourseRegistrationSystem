package org.courseregistration.service;

import org.courseregistration.dao.SectionDAO;
import org.courseregistration.dao.StudentDAO;
import org.courseregistration.exception.ApplicationException;
import org.courseregistration.model.Section;
import org.courseregistration.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;


@Service
public class StudentService {
    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private SectionDAO sectionDAO;

    public List<Student> findAllStudents() throws ApplicationException {
        List<Student> students = studentDAO.findAll();
        List<Student> toReturn = newArrayList();
        for(Student student: students){
            toReturn.add(student);
        }
        return toReturn;
    }

    public Student findById(Long id) throws ApplicationException {
        checkNotNull(id, "Student not Found");
        Student toReturn = studentDAO.findById(id);

        if(toReturn==null) {
            throw ApplicationException.createNew()
                .withCode(404)
                .withTitle("Resource not found")
                .withStatus(Response.Status.NOT_FOUND.getStatusCode())
                .withDetail("Student with id " + id + " is not available").build();
        }
        return toReturn;
    }


    public void addStudent(Student s) throws ApplicationException {
        studentDAO.save(s);
    }

    public void addStudents(List<Student> students) throws ApplicationException {
        studentDAO.save(students);
    }

    public void deleteStudent(Long student_id) throws ApplicationException {
        studentDAO.delete(student_id);
    }

    public void deleteAllStudents(Collection<Long> ids) throws ApplicationException {
        studentDAO.delete(ids);

    }

    public void enrollSection(Long student_id, Long section_id) throws ApplicationException {
        Section section = sectionDAO.findById(section_id);
        Student student = studentDAO.findById(student_id);
        student.addSection(section);
    }

    public void dropSection(Long student_id, Long section_id) throws ApplicationException {
        Section section = sectionDAO.findById(section_id);
        Student student = studentDAO.findById(student_id);
        student.dropSection(section);
    }


    public Student updateStudent(Long id, Student s) throws ApplicationException {
        Student currentStud = null;
        currentStud = studentDAO.findById(id);

        if(s.getHashedPassword() != null) {
            currentStud.setHashedPassword(s.getHashedPassword());
        }
        if(s.getPhoneNumber() != null) {
            currentStud.setPhoneNumber(s.getPhoneNumber());
        }
        if(s.getAdmissionType() != null) {
            currentStud.setAdmissionType(s.getAdmissionType());
        }
        if(s.getPreviousDegree() != null) {
            currentStud.setPreviousDegree(s.getPreviousDegree());
        }
        if(s.getUsername() != null) {
            currentStud.setUsername(s.getUsername());
        }
        if(s.getEmailId()!= null) {
            currentStud.setEmailId(s.getEmailId());
        }
        if(s.getDateOfBirth()!= null) {
            currentStud.setDateOfBirth(s.getDateOfBirth());
        }
        if(s.getFirstName() != null) {
            currentStud.setFirstName(s.getFirstName());
        }
        if(s.getMiddleName() != null) {
            currentStud.setMiddleName(s.getMiddleName());
        }
        if(s.getLastName() != null) {
            currentStud.setLastName(s.getLastName());
        }
        if(s.getAddress() != null) {
            currentStud.setAddress(s.getAddress());
        }

        studentDAO.update(currentStud);
        return currentStud;
    }


}
