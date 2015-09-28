package org.courseregistration.dao;

import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.courseregistration.model.Course;
import org.courseregistration.model.Professor;
import org.courseregistration.model.Section;
import org.courseregistration.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.*;

@RunWith(BlockJUnit4ClassRunner.class)
public class StudentDAOTest extends BaseTest {

    @Test
    public void findAll_students() throws Exception {
        StudentDAO studentDAO = new StudentDAO(entityManager);

        List<Student> students = studentDAO.findAll();

        assertEquals("Number of students do not match", 4, students.size());
    }

    @Test
    public void find_student_by_id() throws Exception {

        StudentDAO studentDAO = new StudentDAO(entityManager);

        Student expected = studentDAO.findAll().get(0);

        Student actual = studentDAO.findById(expected.getId());

        assertEquals(expected.getId(), (actual.getId()));
        assertEquals(expected.getFirstName(), (actual.getFirstName()));
        assertEquals(expected.getLastName(), (actual.getLastName()));
        assertEquals(expected.getMiddleName(), (actual.getMiddleName()));
    }


    @Test
    public void update_student_by_dropping_a_course() throws Exception {

        StudentDAO studentDAO = new StudentDAO(entityManager);

        Student student = TestUtils.createNewStudent(187354L,"Daniel");
        Professor professor = TestUtils.createNewProfessor(890897L,"Thomas");
        Course course = TestUtils.createCourse("CMPE-281","Cloud Technologies");
        Section section = TestUtils.createSection(professor,course);

        student.addSection(section);
        entityManager.persist(student);

        assertNotNull(student.getId());
        assertNotNull(section.getId());
        assertEquals(1,student.getSections().size());
        assertEquals(section,student.getSections().get(0));

        student.dropSection(section);

        studentDAO.update(student);

        Student updatedStudent = entityManager.find(Student.class, student.getId());

        assertEquals(0,updatedStudent.getSections().size());
    }

    @Test
    public void update_student_by_adding_a_course() throws Exception {

        StudentDAO studentDAO = new StudentDAO(entityManager);

        Student student = TestUtils.createNewStudent(187354L,"Daniel");
        Professor professor = TestUtils.createNewProfessor(890897L,"Thomas");
        Course course = TestUtils.createCourse("CMPE-281", "Cloud Technologies");
        Section section = TestUtils.createSection(professor, course);

        Professor professorForSection2 = TestUtils.createNewProfessor(897554L, "Prof.ABC");
        Section section2 = TestUtils.createSection(professorForSection2,course);

        student.addSection(section);
        entityManager.persist(student);
        entityManager.persist(section2);

        assertNotNull(student.getId());
        assertNotNull(section.getId());
        assertEquals(1,student.getSections().size());
        assertEquals(section,student.getSections().get(0));

        student.addSection(section2);

        studentDAO.update(student);

        Student updatedStudent = entityManager.find(Student.class, student.getId());

        assertEquals(2,updatedStudent.getSections().size());
        assertTrue(Iterables.elementsEqual(newArrayList(section, section2), updatedStudent.getSections()));
    }

    @Test
    public void delete_student_by_id() {
        StudentDAO studentDAO = new StudentDAO(entityManager);

        Student student = TestUtils.createNewStudent(12345656,"Shrutee");
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.flush();
        entityManager.clear();
        entityManager.getTransaction().commit();

        studentDAO.delete(student.getId());

        Student updatedStudent = entityManager.find(Student.class,student.getId());

        assertNull(updatedStudent);
    }


}
