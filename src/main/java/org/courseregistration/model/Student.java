package org.courseregistration.model;

import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student_details")
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {

    @Column(name = "admissionType", nullable = false)
    private String admissionType;

    @Column(name = "previous_degree", nullable = false)
    private String previousDegree;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "enrolled_student", joinColumns = {@JoinColumn(name = "student_id")}, inverseJoinColumns = {@JoinColumn(name = "section_id")}, foreignKey = @ForeignKey(name = "FK_student_section"), uniqueConstraints = {@UniqueConstraint(columnNames = {
        "student_id", "section_id"})})
    private List<Section> sections = Lists.newArrayList();

    public String getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(String admissionType) {
        this.admissionType = admissionType;
    }

    public String getPreviousDegree() {
        return previousDegree;
    }

    public void setPreviousDegree(String previousDegree) {
        this.previousDegree = previousDegree;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public void addSection(Section section) {
        this.sections.add(section);
    }

    public void dropSection(Section section) {
        if (this.sections.contains(section)) {
            this.sections.remove(section);
        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub

        String userDetails = super.toString();
        StringBuilder builder = new StringBuilder();
        builder.append("\n\tStudent:__________________________________");
        builder.append(userDetails);
        builder.append("\n\t[ Admission Type: " + admissionType);
        builder.append(", Previous Degree: " + previousDegree + "]");

        return builder.toString();
    }
}
