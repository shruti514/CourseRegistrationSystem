package org.courseregistration.service;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.courseregistration.dao.CourseDAO;
import org.courseregistration.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
	@Autowired
	private CourseDAO courseDao;

	/**
	 * Find list of Courses
	 *
	 * @return list of all Courses
	 */
	// @Override
	public List<Course> findAllCourses() {
		List<Course> courses = courseDao.findAll();
		List<Course> toReturn = newArrayList();
		for (Course course : courses) {
			toReturn.add(course);
		}
		return toReturn;
	}

	/**
	 * Find a course by course id
	 *
	 * @return the course details fetched from the database of the corresponding
	 *         course id
	 */
	public Course findById(Long id) {
		Course toReturn = courseDao.findById(id);
		return toReturn;
	}

	/**
	 * Delete a course given the course id
	 *
	 * @return the status
	 */
	public void deleteById(Long id) {
		courseDao.delete(id);
	}

	public void save(Course section) {
		courseDao.save(section);
	}
}
