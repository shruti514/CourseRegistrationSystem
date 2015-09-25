package org.courseregistration.datatests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtils {
	private static EntityManagerFactory entityManagerFactory = null;
	private static EntityManager entityManager = null;

	public static void initEntityManager() {
		if (entityManager == null) {
			entityManagerFactory = Persistence
					.createEntityManagerFactory("student_registration_system");
			entityManager = entityManagerFactory.createEntityManager();
		}

	}

	public static EntityManager getEntityManager() {
		if (entityManager == null) {
			HibernateUtils.initEntityManager();
		}
		return entityManager;
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		if (entityManagerFactory == null) {
			HibernateUtils.initEntityManager();
		}
		return entityManagerFactory;
	}

	public static void closeEntityManager() {
		entityManager.close();
		entityManagerFactory.close();
	}

	public static void setEntityManager(EntityManager emf) {
		entityManager = emf;
	}

}
