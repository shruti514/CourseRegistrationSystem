package org.courseregistration.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.courseregistration.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *A class use
 */
public class StudentDAO extends GenericDAO<Student> {
	private final EntityManager entityManager;
	private static final Logger logger = LoggerFactory
			.getLogger(StudentDAO.class);

	public StudentDAO(EntityManager entityManager) {
		super(Student.class, entityManager);
		this.entityManager = entityManager;
	}

    /**
     *Find students from database
     * @return a list of students fetched from database
     */
	@Override
	public List<Student> findAll() {
		logger.debug("about to load all Students");
		String query = "from Student order by firstName asc";
		List<Student> students = entityManager
				.createQuery(query, Student.class).getResultList();
		logger.debug("returning all({}) Students", students.size());
		return students;
	}

	public Student findByName(String name) {
		try {
			return entityManager
					.createQuery(
							"select s from Student s where s.firstName=:firstName",
							Student.class).setParameter("firstName", name)
					.getSingleResult();
		} catch (Exception e) {
			return null;

		}
	}
}
