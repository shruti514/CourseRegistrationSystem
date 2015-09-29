package org.courseregistration.dao;

import static org.hibernate.jpa.criteria.predicate.ComparisonPredicate.ComparisonOperator.EQUAL;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.courseregistration.model.Section;
import org.courseregistration.model.Student;
import org.hibernate.jpa.criteria.CriteriaBuilderImpl;
import org.hibernate.jpa.criteria.predicate.ComparisonPredicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class EnrolledStudentsDAO extends GenericDAO<Section> {
	private final EntityManager entityManager;
	private static final Logger logger = LoggerFactory
			.getLogger(EnrolledStudentsDAO.class);

	public EnrolledStudentsDAO(EntityManager entityManager) {
		super(Section.class, entityManager);
		this.entityManager = entityManager;
	}

	private Predicate[] getPredicates(CriteriaBuilder cb,
			Map<SearchCriteria, String> criteria, Root<Section> section,
			Join<Section, Student> studentJoin) {
		List<Predicate> predicates = Lists.newArrayList();

		for (Map.Entry<SearchCriteria, String> entry : criteria.entrySet()) {
			Predicate predicate = this.buildPredicate(cb, entry.getKey(), entry
					.getValue().trim(), section, studentJoin);
			if (predicate != null) {
				predicates.add(predicate);
			}
		}

		Predicate[] toReturn = new Predicate[] {};
		return predicates.toArray(toReturn);
	}

	private Predicate buildPredicate(CriteriaBuilder cb,
			SearchCriteria definedCriteria, String queryParam,
			Root<Section> section, Join<Section, Student> studentJoin) {
		switch (definedCriteria) {
		case STUDENT_ID_EQUALS:
			return new ComparisonPredicate((CriteriaBuilderImpl) cb, EQUAL,
					studentJoin.get("user_id"), queryParam);
		case STUDENT_LAST_NAME_EQUALS:
			return new ComparisonPredicate((CriteriaBuilderImpl) cb, EQUAL,
					section.get("last_name"), queryParam);
		case STUDENT_EMAIL_ID_EQUALS:
			return new ComparisonPredicate((CriteriaBuilderImpl) cb, EQUAL,
					section.get("email_id"), queryParam);
			// case COURSE_CODE_CONTAINS:
			// return new LikePredicate((CriteriaBuilderImpl) cb,
			// studentJoin.get("code"), "%" + queryParam + "%");
		}
		return null;
	}

	@Override
	public List<Section> findAll() {
		logger.debug("about to load all Sections");
		String query = "Select s from Enrolled_student s order by s.student_details.last_name asc";
		List<Section> sections = entityManager
				.createQuery(query, Section.class).getResultList();
		logger.debug("returning all({}) Sections", sections.size());
		return sections;
	}
}
