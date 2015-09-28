package org.courseregistration.dao;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.courseregistration.model.Course;
import org.courseregistration.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(BlockJUnit4ClassRunner.class)
public class CourseDAOTest extends BaseTest{

    @Test
    public void findAll_courses_asc_order() throws Exception {
        CourseDAO courseDAO = new CourseDAO(entityManager);

        Course course1 = TestUtils.createCourse("CMPE-285","Virtualization Tech");
        Course course2 = TestUtils.createCourse("CMPE-284","Virtualization Tech2");

        entityManager.getTransaction().begin();
        entityManager.persist(course1);
        entityManager.persist(course2);
        entityManager.getTransaction().commit();


        List<Course> courses = courseDAO.findAll();

        assertEquals("Number of courses do not match", 2, courses.size());
        assertTrue(Iterables.elementsEqual(courses, Lists.newArrayList(course2,course1)));
    }
}
