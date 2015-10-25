package org.courseregistration.service;

import org.courseregistration.dao.CourseDAO;
import org.courseregistration.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cpunekar on 25-Oct-15.
 */

@Service
public class CourseService {
    @Autowired
    private CourseDAO courseDAO;

    public Course findById(Long id) {
       Course toReturn = courseDAO.findById(id);
        return toReturn;
    }
}
