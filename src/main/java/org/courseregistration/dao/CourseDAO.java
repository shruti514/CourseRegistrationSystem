package org.courseregistration.dao;

import org.courseregistration.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * An implementation of {@link org.courseregistration.dao.GenericDAO}
 * The responsibility of this class is to perform database operations on the Course
 */
public class CourseDAO extends GenericDAO<Course> {

    private final EntityManager entityManager;

    public CourseDAO(EntityManager entityManager) {
        super(Course.class, entityManager);
        this.entityManager = entityManager;
    }

    private static final Logger logger = LoggerFactory.getLogger(CourseDAO.class);

    /**
     * Find list of Courses
     *
     * @return list of Courses fetched from the database ordered by course code
     */
    @Override
    public List<Course> findAll() {
        logger.debug("about to load all Courses");
        String query = "from Course order by code asc";
        List<Course> courses = entityManager.createQuery(query, Course.class)
            .getResultList();
        logger.debug("returning all({}) Courses", courses.size());
        return courses;
    }


}
