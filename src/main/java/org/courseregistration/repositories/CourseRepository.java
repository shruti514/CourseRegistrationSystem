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

    public Object[][] findCourseBy(){

        EntityManagerFactory em = HibernateUtils.getEntityManagerFactory();
        EntityManager entityManager = HibernateUtils.getEntityManager();
        Query nativeQuery = entityManager.createNativeQuery("select * from section_info", Section.class);
        List<Section> resultList = (List<Section>) nativeQuery.getResultList();

        String[] columnNames = { "Comlumn1", "Column2", "Column3" };
        Object[][] toReturn = {{"data1","data2","data3"},{"data4","data5","data6"},{"data7","data8","data9"},};


        return toReturn;
    }

}
