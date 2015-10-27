package org.courseregistration.service;

import org.courseregistration.dao.SectionDAO;
import org.courseregistration.dao.StudentDAO;
import org.courseregistration.model.Section;
import org.courseregistration.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class StudentService {
    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private SectionDAO sectionDAO;

    public List<Student> findAllStudents(){
        List<Student> students = studentDAO.findAll();
        List<Student> toReturn = newArrayList();
        for(Student student: students){
            toReturn.add(student);
        }
        return toReturn;
    }

    public Student findById(Long id) {
        Student toReturn = studentDAO.findById(id);
        return toReturn;
    }

    public void addStudent(Student s) {
        studentDAO.save(s);
    }

    public void deleteStudent(Long student_id) {
        studentDAO.delete(student_id);
    }

    public void enrollSection(Long student_id, Long section_id) {
        Section section = sectionDAO.findById(section_id);
        Student student = studentDAO.findById(student_id);
        student.addSection(section);
    }

    public void dropSection(Long student_id, Long section_id) {
        Section section = sectionDAO.findById(section_id);
        Student student = studentDAO.findById(student_id);
        student.dropSection(section);
    }


 /*   public void updateStudentPhone(Long student_id, String phone_number) {
        Student student = studentDAO.findById(student_id);
        student.setPhoneNumber(phone_number);
        studentDAO.update(student);
    }

    public void updateStudentPass(Long id, String password) {
        Student student = studentDAO.findById(id);
        student.setHashedPassword(password);
        studentDAO.update(student);
    }*/

    public void updateStudent(Long id, Student s) {
        Student student = studentDAO.findById(id);

    }
}
