package org.courseregistration.repositories;

import org.courseregistration.datatests.HibernateUtils;
import org.courseregistration.model.Section;
import org.hibernate.Criteria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CourseRepository {

    public List<Section> findCourseBy(){
        EntityManager entityManager = HibernateUtils.getEntityManager();
        Query nativeQuery = entityManager.createNativeQuery("select * from section_info", Section.class);
        return (List<Section>) nativeQuery.getResultList();
    }

}
