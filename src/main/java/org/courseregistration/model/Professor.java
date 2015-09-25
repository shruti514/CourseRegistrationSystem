package org.courseregistration.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="professor_details")
public class Professor {

    @Id
    @SequenceGenerator(name="sequence", sequenceName="sequence", allocationSize=1, initialValue=100000)
    @GeneratedValue(generator = "sequence", strategy=GenerationType.SEQUENCE)
    @Column(name = "professor_id")
    private Long id;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "email_id",nullable = false)
    private String emailId;

    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;

    @Column(name = "date_of_birth",nullable = false)
    @Temporal(value=TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "address1",nullable = false)
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name="city",nullable = false)
    private String city;

    @Column(name="state",nullable = false)
    private String state;

    @Column(name="country",nullable = false)
    private String country;

    @Column(name = "zip_code",nullable = false)
    private String zipCode;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

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
