package org.courseregistration.dao;

import org.courseregistration.dbtests.HibernateUtils;
import org.courseregistration.model.Section;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Map;

import static org.courseregistration.dao.SearchCriteria.COURSE_NAME;

public class SectionDAO extends GenericDAO<Section>{

    private final EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(SectionDAO.class);

    public SectionDAO(EntityManager entityManager) {
        super(Section.class,entityManager);
        this.entityManager = entityManager;
    }

    public List<Section> findCourseByCriteria(String courseName,String lastName){
        Map<SearchCriteria,String> criteria = null;
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("from").append(Section.class.getName());

        for(SearchCriteria definedCriteria :SearchCriteria.values()){
            queryBuilder.append(getQueryStringFor(definedCriteria));
        }

        TypedQuery<Section> query = entityManager.createQuery(
            "from Section where course.name like :courseName and professor.lastName = :lastName",
            Section.class);
        query.setParameter("courseName", courseName);
        query.setParameter("lastName",lastName);

        return query.getResultList();
    }

    private String getQueryStringFor(SearchCriteria definedCriteria) {
        switch(definedCriteria){
            case COURSE_NAME:

                break;
            case SEMESTER:
                break;
            case PROFESSOR_NAME_BEGINS_WITH:
                break;

        }
        return null;
    }
}
