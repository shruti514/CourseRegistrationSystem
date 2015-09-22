package org.courseregistration.repositories;

import org.courseregistration.datatests.HibernateUtils;
import org.courseregistration.model.Student;

import javax.persistence.EntityManager;

public class StudentRepository {

        public Student findStudent(Long id){
            EntityManager em = HibernateUtils.getEntityManager();
            return em.find(Student.class, id);
        }

}
