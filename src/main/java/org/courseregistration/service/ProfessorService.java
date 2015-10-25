package org.courseregistration.service;

import org.courseregistration.model.Professor;
import org.courseregistration.dao.ProfessorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class ProfessorService {
    @Autowired

    private ProfessorDAO professorDAO;

    public List<Professor> findAllProfessor(){

        List<Professor>  professors = professorDAO.findAll();
        List<Professor> toReturn = newArrayList();
        for(Professor professor:professors)
        {
             toReturn.add(professor);
        }
     return toReturn;
    }

    public Professor findById(Long id) {
        Professor toReturn = professorDAO.findById(id);
        return toReturn;
    }


}
