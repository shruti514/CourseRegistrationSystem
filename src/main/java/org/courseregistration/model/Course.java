package org.courseregistration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "course_details")
public class Course implements Serializable {
	@Id
	@SequenceGenerator(name = "sequence", sequenceName = "sequence", allocationSize = 1, initialValue = 100000)
	@GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
	@Column(name = "course_id")
	private Long id;

	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", columnDefinition = "Text", length = 65535)
	private String description;

	@Column(name = "no_of_credits", nullable = false)
	private Integer numOfCredits;

	@Column(name = "pre_requisite_course")
	private String prerequisiteCourse;

	@Column(name = "department", nullable = false)
	private String department;

	@Column(name = "program", nullable = false)
	private String program;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNumOfCredits() {
		return numOfCredits;
	}

	public void setNumOfCredits(Integer numOfCredits) {
		this.numOfCredits = numOfCredits;
	}

	public String getPrerequisiteCourse() {
		return prerequisiteCourse;
	}

	public void setPrerequisiteCourse(String prerequisiteCourse) {
		this.prerequisiteCourse = prerequisiteCourse;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Course))
			return false;

		Course course = (Course) o;

		if (!id.equals(course.id))
			return false;
		if (!code.equals(course.code))
			return false;
		if (!name.equals(course.name))
			return false;
		if (!description.equals(course.description))
			return false;
		if (!numOfCredits.equals(course.numOfCredits))
			return false;
		if (!department.equals(course.department))
			return false;
		return program.equals(course.program);

	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + code.hashCode();
		result = 31 * result + name.hashCode();
		result = 31 * result + description.hashCode();
		result = 31 * result + numOfCredits.hashCode();
		result = 31 * result + department.hashCode();
		result = 31 * result + program.hashCode();
		return result;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n\tCourse Details:__________________________________");
		builder.append("\n\tCode: " + code);
		builder.append(", Name: " + name);
		builder.append(" [ Credits: " + numOfCredits + "]");
		builder.append("\n\t[ Course Description: " + description + "]");
		builder.append("\n\t[ Prerequisites: " + prerequisiteCourse + "]");
		builder.append("\n\t[ Department: " + department);
		builder.append(", Program: " + program + "]");
		return builder.toString();
	}
}
