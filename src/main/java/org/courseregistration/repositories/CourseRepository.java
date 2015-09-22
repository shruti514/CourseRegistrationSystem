package org.courseregistration.repositories;

import org.courseregistration.datatests.HibernateUtils;
import org.courseregistration.model.Course;
import org.courseregistration.model.Section;
import org.hibernate.Criteria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CourseRepository {

    public List<Course> findAllCourses() {
        EntityManager entityManager = HibernateUtils.getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> rootEntry = cq.from(Course.class);
        CriteriaQuery<Course> all = cq.select(rootEntry);
        TypedQuery<Course> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }


}
