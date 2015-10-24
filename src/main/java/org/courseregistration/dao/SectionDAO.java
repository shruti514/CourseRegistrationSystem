package org.courseregistration.dao;

import com.google.common.collect.Lists;
import org.courseregistration.model.Course;
import org.courseregistration.model.Professor;
import org.courseregistration.model.Section;
import org.hibernate.jpa.criteria.CriteriaBuilderImpl;
import org.hibernate.jpa.criteria.predicate.ComparisonPredicate;
import org.hibernate.jpa.criteria.predicate.LikePredicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Map;

import static org.hibernate.jpa.criteria.predicate.ComparisonPredicate.ComparisonOperator.EQUAL;

/**
 * An implementation of {@link org.courseregistration.dao.GenericDAO}
 * The responsibility of this class is to perform database operations on entity Section
 */
@Repository
public class SectionDAO extends GenericDAO<Section> {

    private static final Logger logger = LoggerFactory.getLogger(SectionDAO.class);

    /**
     * Find course by given criteria
     *
     * @param criteria a map of filter criterias which should be applied on the Section vs
     *                 filter values
     * @return list of section found matching the given criteria
     */
	public List<Section> findCourseByCriteria(
			Map<SearchCriteria, String> criteria) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Section> cq = cb.createQuery(Section.class);

		Root<Section> section = cq.from(Section.class);
		Join<Section, Course> courseJoin = section.join("course");
		Join<Section, Professor> professorJoin = section.join("professor");

		Predicate[] predicates = getPredicates(cb, criteria, section,
				courseJoin, professorJoin);
		CriteriaQuery<Section> queryToExecute = cq.where(predicates);
		return entityManager.createQuery(queryToExecute).getResultList();
	}


    private Predicate[] getPredicates(CriteriaBuilder cb,
                                      Map<SearchCriteria, String> criteria,
                                      Root<Section> section,
                                      Join<Section,Course> courseJoin,
                                      Join<Section,Professor> professorJoin){
        List<Predicate> predicates = Lists.newArrayList();

        for(Map.Entry<SearchCriteria,String> entry: criteria.entrySet()) {
            Predicate predicate = this.buildPredicate(cb, entry.getKey(), entry.getValue().trim(), section, courseJoin, professorJoin);
            if(predicate!=null){
                predicates.add(predicate);
            }
        }

        Predicate[] toReturn = new Predicate[]{};
        return predicates.toArray(toReturn);
    }

    private Predicate buildPredicate(CriteriaBuilder cb,
                                     SearchCriteria definedCriteria,
                                     String queryParam,
                                     Root<Section> section,
                                     Join<Section, Course> courseJoin,
                                     Join<Section, Professor> professorJoin) {
        switch (definedCriteria) {
            case COURSE_NAME_CONTAINS:
                return new LikePredicate((CriteriaBuilderImpl) cb,courseJoin.get("name"),"%"+queryParam+"%");
            case DEPARTMENT_EQUALS:
                return new ComparisonPredicate((CriteriaBuilderImpl)cb, EQUAL,courseJoin.get("department"),queryParam);
            case SEMESTER_EQUALS:
                return new ComparisonPredicate((CriteriaBuilderImpl)cb, EQUAL,section.get("semester"),queryParam);
            case DAY_OF_WEEK_EQUALS:
                return new ComparisonPredicate((CriteriaBuilderImpl)cb, EQUAL,section.get("dayOfWeek"),queryParam);
            case PROFESSOR_FIRST_NAME_CONTAINS:
                return new LikePredicate((CriteriaBuilderImpl)cb,professorJoin.get("firstName"),"%"+queryParam+"%");
           case PROFESSOR_FIRST_NAME_EQUALS:
                return new ComparisonPredicate((CriteriaBuilderImpl)cb, EQUAL,professorJoin.get("firstName"),queryParam);
            case PROFESSOR_LAST_NAME_CONTAINS:
                return new LikePredicate((CriteriaBuilderImpl)cb,professorJoin.get("lastName"),"%"+queryParam+"%");
           case PROFESSOR_LAST_NAME_EQUALS:
                return new ComparisonPredicate((CriteriaBuilderImpl)cb, EQUAL,professorJoin.get("lastName"),queryParam);
            case COURSE_CODE_EQUALS:
                return new ComparisonPredicate((CriteriaBuilderImpl)cb, EQUAL,courseJoin.get("code"),queryParam);
            case COURSE_CODE_CONTAINS:
                return new LikePredicate((CriteriaBuilderImpl)cb,courseJoin.get("code"),"%"+queryParam+"%");
        }
        return null;
    }
    /**
     * Find list of Sections
     *
     * @return list of Section fetched from the database ordered by the course code
     */

    @Override
    public List<Section> findAll() {
        logger.debug("about to load all Sections");
        String query = "Select s from Section s order by s.course.code asc";
        List<Section> sections = entityManager.createQuery(query, Section.class)
            .getResultList();
        logger.debug("returning all({}) Sections", sections.size());
        return sections;
    }
    public Section fetchSection(String courseId, String professor) {
        return entityManager
            .createQuery(
                "select s from Section s where s.course.code=:courseId and s.professor.lastName=:lastname",
                Section.class).setParameter("courseId", courseId)
            .setParameter("lastname", professor).getSingleResult();
    }
}
