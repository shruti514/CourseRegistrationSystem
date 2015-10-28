package org.courseregistration.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "sequence", allocationSize = 1, initialValue = 100000)
    @GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    protected Long id;

    @Column(name = "college_id", nullable = false, unique = true)
    private Long collegeId;

    @JsonIgnore
    @Column(name = "password")
    private String hashedPassword;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email_id", nullable = false)
    private String emailId;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "date_of_birth", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date dateOfBirth;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "FK_user_address"), nullable = false)
    private Address address;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<Role>();

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;

        User user = (User) o;

        if (!id.equals(user.id))
            return false;
        if (!collegeId.equals(user.collegeId))
            return false;
        if (hashedPassword != null ? !hashedPassword
            .equals(user.hashedPassword) : user.hashedPassword != null)
            return false;
        if (!firstName.equals(user.firstName))
            return false;
        if (!middleName.equals(user.middleName))
            return false;
        if (!lastName.equals(user.lastName))
            return false;
        if (!emailId.equals(user.emailId))
            return false;
        if (!phoneNumber.equals(user.phoneNumber))
            return false;
        if (!address.equals(user.address))
            return false;

        return roles.equals(user.roles);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + collegeId.hashCode();
        result = 31 * result
            + (hashedPassword != null ? hashedPassword.hashCode() : 0);
        result = 31 * result + firstName.hashCode();
        result = 31 * result + middleName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + emailId.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + roles.hashCode();
        return result;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("\n\tName :\t" + firstName + " " + middleName + " "
            + lastName);
        builder.append("\n\t[ Email: " + emailId);
        builder.append(", Phone: " + phoneNumber);
        builder.append(", Date of birth :" + dateOfBirth + "] ");
        builder.append(address.toString());
        return builder.toString();

    }
}
