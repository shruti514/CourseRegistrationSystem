package org.courseregistration.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="section_info")
public class Section {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "section_id")
    private String id;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "semester")
    private String semester;

    @Column(name = "class_start_time")
    @Temporal(value=TemporalType.TIME)
    private Date classStartTime;

    @Column(name = "class_end_time")
    @Temporal(value=TemporalType.TIME)
    private Date classEndTime;

    @Column(name="day_of_week")
    private String dayOfWeek;

    @Column(name="class_start_date")
    private Date startDate;

    @Column(name = "class_end_date")
    private Date endDate;

    @Column(name = "room_number")
    private String roomNumber;

    @Column(name = "total_capacity")
    private Integer totalCapacity;

    @Column(name="wait_list")
    private Integer waitList;

    @Column(name="wait_list_capacity")
    private Integer waitListCapacity;

    @Column(name="mode_of_instruction")
    private String modeOfInstruction;

    @ManyToMany(mappedBy = "sections")
    private List<Student> students;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
