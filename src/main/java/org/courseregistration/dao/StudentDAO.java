package org.courseregistration.dao;

import org.courseregistration.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentDAO extends GenericDAO<Student> {
    private final EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(StudentDAO.class);

    protected StudentDAO(EntityManager entityManager) {
        super(Student.class, entityManager);
        this.entityManager = entityManager;
    }

    public List<Student> findAll() {
        logger.debug("about to load all Students");
        String query = "from Student order by firstName asc";
        List<Student> students = entityManager.createQuery(query, Student.class)
            .getResultList();
        logger.debug("returning all({}) Students", students.size());
        return students;
    }
}
