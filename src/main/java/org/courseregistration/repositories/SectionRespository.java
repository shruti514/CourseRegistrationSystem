package org.courseregistration.repositories;

import org.courseregistration.datatests.HibernateUtils;
import org.courseregistration.model.Section;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class SectionRespository {
    public List<Section> findCourseBy(){
        EntityManager entityManager = HibernateUtils.getEntityManager();
        Query nativeQuery = entityManager.createNativeQuery("select * from section_info", Section.class);
        return (List<Section>) nativeQuery.getResultList();
    }
}
