package org.courseregistration.dao;

import com.google.common.collect.Maps;
import org.courseregistration.model.Course;
import org.courseregistration.model.Professor;
import org.courseregistration.model.Section;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import javax.persistence.JoinColumn;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(BlockJUnit4ClassRunner.class)
public class SectionDAOTest extends BaseTest {
    private SectionDAO sectionDAO = new SectionDAO(entityManager);


    @Test
    public void testFindCourseByCriteria() throws Exception {
        //Data For 2 Sections
        Professor professor = TestUtils.createNewProfessor(890897L,"Thomas");
        Course course1 = TestUtils.createCourse("CMPE-281", "Cloud Technologies");
        Section section1 = TestUtils.createSection(professor, course1);

        Professor professorForSection2 = TestUtils.createNewProfessor(897554L, "Prof.ABC");
        Course course2 = TestUtils.createCourse("CMPE-283", "Cloud Technologies");
        Section section2 = TestUtils.createSection(professorForSection2,course2);

        entityManager.getTransaction().begin();
        entityManager.persist(section1);
        entityManager.persist(section2);
        entityManager.flush();
        entityManager.getTransaction().commit();


        //Checks to see if correct data exists in DB
        assertNotNull(professor.getId());
        assertNotNull(professorForSection2.getId());
        assertNotNull(section1.getId());

        assertNotNull(course1.getId());
        assertNotNull(course2.getId());
        assertNotNull(section2.getId());

        //Search Section based on criteria
        Map<SearchCriteria,String> criteria = Maps.newHashMap();
        criteria.put(SearchCriteria.COURSE_CODE_CONTAINS,"28");
        criteria.put(SearchCriteria.PROFESSOR_FIRST_NAME_CONTAINS,"Thomas");

        List<Section> foundSections = sectionDAO.findCourseByCriteria(criteria);

        assertEquals(1, foundSections.size());
        assertEquals(section1.getCourse().getCode(),foundSections.get(0).getCourse().getCode());
        assertEquals(section1.getCourse().getName(),foundSections.get(0).getCourse().getName());
        assertEquals(section1.getProfessor().getFirstName(),foundSections.get(0).getProfessor().getFirstName());

    }
}
