package org.courseregistration.dbtests;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtils {
	private static EntityManagerFactory entityManagerFactory = null;
	private static EntityManager entityManager = null;

	public static void initEntityManager() {
		if (entityManager == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory(
					"student_registration_system", createProperties());
			entityManager = entityManagerFactory.createEntityManager();
		}

	}

	public static EntityManager getEntityManager() {
		if (entityManager == null) {
			HibernateUtils.initEntityManager();
		}
		return entityManager;
	}

	public static void closeEntityManager() {
		entityManager.close();
		entityManagerFactory.close();
	}

	private static Map<String, String> createProperties() {

		Map<String, String> properties = new HashMap<>();
		properties.put("javax.persistence.jdbc.driver",
				DatabaseConnector.JDBC_DRIVER);
		properties.put("javax.persistence.jdbc.url", DatabaseConnector.DB_URL);
		properties.put("javax.persistence.jdbc.user", DatabaseConnector.USER);
		properties.put("javax.persistence.jdbc.password",
				DatabaseConnector.PASS);
		return properties;
	}

}
