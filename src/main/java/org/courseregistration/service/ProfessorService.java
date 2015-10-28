package org.courseregistration.service;

import org.courseregistration.model.Professor;
import org.courseregistration.dao.ProfessorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
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

    public Professor findProfessorById(Long id) throws ApplicationException {
        checkNotNull(id,"Id should not be null");
        Professor toReturn = professorDAO.findById(id);

        if(toReturn == null){
            throw ApplicationException.createNew()
                .withCode(404)
                .withTitle("Resource not found")
                .withStatus(Response.Status.NOT_FOUND.getStatusCode())
                .withDetail("Professor with id " + id + " is not available").build();
        }

        return toReturn;
    }

    public void addProfessor(Professor professor){
        professorDAO.save(professor);
    }

    public void addProfessors(List<Professor> professors)  {
        professorDAO.save(professors);
    }

    public void deleteProfessorById(Long id){
        professorDAO.delete(id);
    }

    public void deleteAllProfessors(Collection<Long> ids){
        professorDAO.delete(ids);
    }

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
        if(p.getUsername() !=null){
            currentProfessor.setUsername(p.getUsername());
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





















