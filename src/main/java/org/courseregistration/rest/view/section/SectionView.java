package org.courseregistration.rest.view.section;


import java.util.Date;
import java.util.List;

public class SectionView {
    Long id;
    String semester;
    Date classStartTime;
    Date classEndTime;
    String dayOfWeek;
    Date startDate;
    Date endDate;
    String roomNumber;
    Integer totalCapacity;
    Integer waitListCapacity;
    String modeOfInstruction;
    Integer price;
    Course course;
    Professor professor;
    List<Student> student;
    private int numberOfEnrolledStudents;

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setNumberOfEnrolledStudents(int numberOfEnrolledStudents) {
        this.numberOfEnrolledStudents = numberOfEnrolledStudents;
    }

    public int getNumberOfEnrolledStudents() {
        return numberOfEnrolledStudents;
    }
}
