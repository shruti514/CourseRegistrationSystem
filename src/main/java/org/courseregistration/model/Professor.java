package org.courseregistration.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="professor_details")
@PrimaryKeyJoinColumn(name = "user_id")
public class Professor extends User{

    @Column(name="faculty_type",nullable = false)
    private String facultyType;

    @Column(name="years_of_experience",nullable = false)
    private Integer yearsOfExperience;

    @Column(name="office_hours_from_time",nullable = false)
    @Temporal(value= TemporalType.TIME)
    private Date officeHoursFromTime;

    @Column(name="office_hours_to_time",nullable = false)
    @Temporal(value= TemporalType.TIME)
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
}
