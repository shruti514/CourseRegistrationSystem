package org.courseregistration.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="section_info")
public class Section {

    @Id
    @GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
        @org.hibernate.annotations.Parameter(name = "sequenceName", value = "sequence"),
        @org.hibernate.annotations.Parameter(name = "allocationSize", value = "1"),
    })
    @GeneratedValue(generator = "sequence", strategy=GenerationType.SEQUENCE)
    @Column(name = "section_id")
    private Long id;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "semester",nullable = false)
    private String semester;

    @Column(name = "class_start_time",nullable = false)
    @Temporal(value=TemporalType.TIME)
    private Date classStartTime;

    @Column(name = "class_end_time",nullable = false)
    @Temporal(value=TemporalType.TIME)
    private Date classEndTime;

    @Column(name="day_of_week",nullable = false)
    private String dayOfWeek;

    @Column(name="class_start_date",nullable = false)
    private Date startDate;

    @Column(name = "class_end_date",nullable = false)
    private Date endDate;

    @Column(name = "room_number",nullable = false)
    private String roomNumber;

    @Column(name = "total_capacity",nullable = false)
    private Integer totalCapacity;

    @Column(name="wait_list",nullable = false)
    private Integer waitList;

    @Column(name="wait_list_capacity",nullable = false)
    private Integer waitListCapacity;

    @Column(name="mode_of_instruction",nullable = false)
    private String modeOfInstruction;

    @ManyToMany(mappedBy = "sections")
    private List<Student> students;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Date getClassStartTime() {
        return classStartTime;
    }

    public void setClassStartTime(Date classStartTime) {
        this.classStartTime = classStartTime;
    }

    public Date getClassEndTime() {
        return classEndTime;
    }

    public void setClassEndTime(Date classEndTime) {
        this.classEndTime = classEndTime;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(Integer totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public Integer getWaitList() {
        return waitList;
    }

    public void setWaitList(Integer waitList) {
        this.waitList = waitList;
    }

    public Integer getWaitListCapacity() {
        return waitListCapacity;
    }

    public void setWaitListCapacity(Integer waitListCapacity) {
        this.waitListCapacity = waitListCapacity;
    }

    public String getModeOfInstruction() {
        return modeOfInstruction;
    }

    public void setModeOfInstruction(String modeOfInstruction) {
        this.modeOfInstruction = modeOfInstruction;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
