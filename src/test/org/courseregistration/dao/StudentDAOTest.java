package org.courseregistration.dao;

import com.sun.xml.internal.rngom.parse.host.Base;
import org.courseregistration.dbtests.DataGenerator;
import org.courseregistration.dbtests.HibernateUtils;
import org.courseregistration.model.Course;
import org.courseregistration.model.Professor;
import org.courseregistration.model.Section;
import org.courseregistration.model.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;


import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(BlockJUnit4ClassRunner.class)
public class StudentDAOTest extends BaseTest {

    @Test
    public void findAll_students() throws Exception {
        StudentDAO studentDAO = new StudentDAO(entityManager);

        List<Student> students = studentDAO.findAll();

        assertEquals("Number of students do not match",2,students.size());
    }

    @Test
    public void find_student_by_id() throws Exception{

        StudentDAO studentDAO = new StudentDAO(entityManager);

        Student expected = studentDAO.findAll().get(0);

        Student actual = studentDAO.findById(expected.getId());

        assertEquals(expected.getId(),(actual.getId()));
        assertEquals(expected.getFirstName(),(actual.getFirstName()));
        assertEquals(expected.getLastName(),(actual.getLastName()));
        assertEquals(expected.getMiddleName(),(actual.getMiddleName()));
    }

    @Test
    public void update_student_by_adding_a_new_course() throws  Exception{

        StudentDAO studentDAO = new StudentDAO(entityManager);
        ProfessorDAO professorDAO = new ProfessorDAO(entityManager);
        CourseDAO courseDAO = new CourseDAO(entityManager);

        Student existingStudent = studentDAO.findAll().get(0);
        Professor existingProfessor = professorDAO.findAll().get(0);
        Course existingCourse = courseDAO.findAll().get(0);

        Section section = TestUtils.createSection(existingProfessor,existingCourse);
        section.setSemester("Fall-2016");
        section.setModeOfInstruction("online");

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(section);
        entityManager.persist(section);
        entityManager.flush();
        entityManager.detach(section);
        transaction.commit();

        int numberOfSectionsBeforeUpdate = existingStudent.getSections().size();

        existingStudent.addSection(section);
        Student updatedStudent = studentDAO.update(existingStudent);

        List<Section> updatedSections = updatedStudent.getSections();

        assertEquals(numberOfSectionsBeforeUpdate+1,updatedSections.size());

    }

    @Test
    public void delete_student_by_id(){

        StudentDAO dao = new StudentDAO(entityManager);

        List<Student> students = dao.findAll();
        int totalNumberOfStudents = students.size();
        Student student = students.get(1);

        UUID idTobeDeleted = student.getId();


        dao.delete(idTobeDeleted);

        List<Student> studentsAfterDelete = dao.findAll();
        Student byId = dao.findById(idTobeDeleted);

        assertEquals(totalNumberOfStudents - 1, studentsAfterDelete.size());
        assertNull(byId);
    }


}
