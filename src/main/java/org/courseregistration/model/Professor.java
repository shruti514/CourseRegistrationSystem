package org.courseregistration.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "professor_details")
@PrimaryKeyJoinColumn(name = "user_id")
public class Professor extends User {

	@Column(name = "faculty_type", nullable = false)
	private String facultyType;

	@Column(name = "years_of_experience", nullable = false)
	private Integer yearsOfExperience;

	@Column(name = "office_hours_from_time", nullable = false)
	@Temporal(value = TemporalType.TIME)
	private Date officeHoursFromTime;

	@Column(name = "office_hours_to_time", nullable = false)
	@Temporal(value = TemporalType.TIME)
	private Date officeHoursToTime;

	public String getFacultyType() {
		return facultyType;
	}

	public void setFacultyType(String facultyType) {
		this.facultyType = facultyType;
	}

	public Integer getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(Integer yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public Date getOfficeHoursFromTime() {
		return officeHoursFromTime;
	}

	public void setOfficeHoursFromTime(Date officeHoursFromTime) {
		this.officeHoursFromTime = officeHoursFromTime;
	}

	public Date getOfficeHoursToTime() {
		return officeHoursToTime;
	}

	public void setOfficeHoursToTime(Date officeHoursToTime) {
		this.officeHoursToTime = officeHoursToTime;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Professor))
			return false;
		if (!super.equals(o))
			return false;

		Professor professor = (Professor) o;

		if (!facultyType.equals(professor.facultyType))
			return false;

		return yearsOfExperience.equals(professor.yearsOfExperience);

	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + facultyType.hashCode();
		result = 31 * result + yearsOfExperience.hashCode();
		result = 31 * result + officeHoursFromTime.hashCode();
		result = 31 * result + officeHoursToTime.hashCode();
		return result;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub

		String userDetails = super.toString();
		StringBuilder builder = new StringBuilder();
		builder.append("\n\tProfessor:__________________________________");
		builder.append(userDetails);
		builder.append("\n\tFaculty type:\t" + facultyType);
		builder.append("\n\tYears of Experience:\t" + yearsOfExperience);
		builder.append("\n\tOffice hours: from \t" + officeHoursFromTime
				+ " to " + officeHoursToTime);

		return builder.toString();
	}

}
