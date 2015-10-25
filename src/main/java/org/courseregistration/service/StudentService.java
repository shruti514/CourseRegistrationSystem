package org.courseregistration.service;

import org.courseregistration.dao.StudentDAO;
import org.courseregistration.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class StudentService {
    @Autowired
    private StudentDAO studentDAO;

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

}
