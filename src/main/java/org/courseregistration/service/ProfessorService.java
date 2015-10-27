package org.courseregistration.service;

import org.courseregistration.dao.SearchCriteria;
import org.courseregistration.model.Professor;
import org.courseregistration.dao.ProfessorDAO;
import org.courseregistration.model.Section;
import org.courseregistration.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void addProfessors(List<Professor> p)  {
        professorDAO.save(p);
    }

    public void deleteProfessorById(Long id){
        professorDAO.delete(id);
    }

    public void deleteAllProfessors(Collection<Long> ids){
        professorDAO.delete(ids);
    }

    /*
    public void updateProfessorPassword(Long id,String password){
        Professor professor = professorDAO.findById(id) ;
        professor.setHashedPassword(password);
        professorDAO.save(professor);
    }

    public void updateProfessorPhone(Long id,String phone_number){
        Professor professor = professorDAO.findById(id);
        professor.setPhoneNumber(phone_number);
        professorDAO.save(professor);
    }

    public void updateProfessorFacultyType(Long id,String facultyType) {
        Professor professor = professorDAO.findById(id);
        professor.setFacultyType(facultyType);
        professorDAO.save(professor);
    }  */

    public void updateProfessor(Long id ,Professor p){
        Professor currentProfessor = professorDAO.findById(id);

        if(p.getHashedPassword() != null){
            currentProfessor.setHashedPassword(p.getHashedPassword());
        }
        if(p.getPhoneNumber() != null){
            currentProfessor.setPhoneNumber(p.getPhoneNumber());
        }
        if(p.getFacultyType() != null){
            currentProfessor.setFacultyType(p.getFacultyType());
        }
        if(p.getOfficeHoursFromTime() != null){
            currentProfessor.setOfficeHoursFromTime(p.getOfficeHoursFromTime());
        }
        if (p.getOfficeHoursToTime() != null)  {
            currentProfessor.setOfficeHoursToTime(p.getOfficeHoursToTime());
        }
        if(p.getDateOfBirth() != null) {
            currentProfessor.setDateOfBirth(p.getDateOfBirth());
        }
        if(p.getYearsOfExperience()!=null){
            currentProfessor.setYearsOfExperience(p.getYearsOfExperience());
        }
        if(p.getAddress() != null){
            currentProfessor.setAddress(p.getAddress());
        }
        if(p.getCollegeId() !=null){
            currentProfessor.setCollegeId(p.getCollegeId());
        }
        if (p.getFirstName() !=null){
            currentProfessor.setFirstName(p.getFirstName());
        }
        if(p.getMiddleName() !=null){
            currentProfessor.setMiddleName(p.getMiddleName());
        }
        if(p.getLastName() != null){
            currentProfessor.setLastName(p.getLastName());
        }
        professorDAO.update(currentProfessor)  ;
    }

}





















