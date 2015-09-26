package org.courseregistration.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="course_details")
public class Course implements Serializable{
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name="course_id")
    private UUID id;

    @Column(name="code",nullable = false)
    private String code;

    @Column(name="name",nullable = false)
    private String name ;

    @Column(name="description")
    private String description;

    @Column(name="no_of_credits", nullable = false)
    private Integer numOfCredits;

    @Column(name="pre_requisite_course")
    private String prerequisiteCourse;

    @Column(name="department",nullable = false)
    private String department;

    @Column(name="program",nullable = false)
    private String program;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
}
