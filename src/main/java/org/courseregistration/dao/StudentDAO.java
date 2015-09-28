package org.courseregistration.dao;

import org.courseregistration.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.UUID;

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

    public void deleteStudent(UUID id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        logger.debug("Deleting a object of type: {} with id: {}", Student.class.getName(), id.toString());

        try {
            entityManager.createQuery("delete from " + Student.class.getName() + " where id=:id")
                .setParameter("id", id)
                .executeUpdate();
            entityManager.flush();
            transaction.commit();
        } catch (Exception exception) {
            logger.error("Error deleting object of type: {}", Student.class.getName(), exception);
            throw exception;
        }
        logger.debug("Done deleting object of type: {}", Student.class.getName());
    }
}
