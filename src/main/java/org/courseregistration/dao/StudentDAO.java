package org.courseregistration.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.courseregistration.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentDAO extends GenericDAO<Student> {
	private final EntityManager entityManager;
	private static final Logger logger = LoggerFactory
			.getLogger(StudentDAO.class);

	public StudentDAO(EntityManager entityManager) {
		super(Student.class, entityManager);
		this.entityManager = entityManager;
	}

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
		return entityManager
				.createQuery(
						"select s from Student s where s.firstName=:firstName",
						Student.class).setParameter("firstName", name)
				.getSingleResult();

	}
}
