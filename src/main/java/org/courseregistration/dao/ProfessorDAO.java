package org.courseregistration.dao;


import org.courseregistration.model.Professor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class ProfessorDAO extends GenericDAO<Professor> {
    private final EntityManager entityManager;

    public ProfessorDAO(EntityManager entityManager) {
        super(Professor.class, entityManager);
        this.entityManager = entityManager;
    }

    private static final Logger logger = LoggerFactory.getLogger(ProfessorDAO.class);

    @Override
    public List<Professor> findAll() {
        logger.debug("about to load all Professors");
        String query = "from Professor order by firstName asc";
        List<Professor> professors = entityManager.createQuery(query, Professor.class)
            .getResultList();
        logger.debug("returning all({}) Professors", professors.size());
        return professors;
    }

}
