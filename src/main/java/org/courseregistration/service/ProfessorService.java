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

    public List<Professor> findAllProfessors(){

        List<Professor>  professors = professorDAO.findAll();
        List<Professor> toReturn = newArrayList();
        for(Professor professor:professors)
        {
             toReturn.add(professor);
        }
     return toReturn;
    }

    public Professor findProfessorById(Long id) {
        Professor toReturn = professorDAO.findById(id);
        return toReturn;
    }

    public void addProfessor(Professor p){
       professorDAO.save(p);
    }

    public void deleteProfessorById(Long id){
        professorDAO.delete(id);
    }

    public void updateProfessorPass(Long id,String password){
        Professor professor = professorDAO.findById(id) ;
        professor.setHashedPassword(password);
    }

    public void updateProfessorPhone(Long id,String phone_number){
        Professor professor = professorDAO.findById(id);
        professor.setPhoneNumber(phone_number);
    }

    public void updateProfessorFaculty(Long id,String faculty) {
        Professor professor = professorDAO.findById(id);
        professor.setFacultyType(faculty);

    }

}
