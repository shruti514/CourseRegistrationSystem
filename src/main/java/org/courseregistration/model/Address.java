package org.courseregistration.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @SequenceGenerator(name="sequence", sequenceName="sequence", allocationSize=1, initialValue=100000)
    @GeneratedValue(generator = "sequence", strategy=GenerationType.SEQUENCE)
    @Column(name = "address_id")
    private Long id;

    @Column(name = "street_name", nullable = false)
    private String streetName;

    @Column(name = "apt_no", nullable = false)
    private Integer aptNo;

    @Column(name="zip_code", nullable = false)
    private Integer zipcode;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getAptNo() {
        return aptNo;
    }

    public void setAptNo(Integer aptNo) {
        this.aptNo = aptNo;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (!id.equals(address.id)) return false;
        if (!streetName.equals(address.streetName)) return false;
        if (aptNo != null ? !aptNo.equals(address.aptNo) : address.aptNo != null) return false;
        if (!zipcode.equals(address.zipcode)) return false;
        if (!city.equals(address.city)) return false;
        return state.equals(address.state);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + streetName.hashCode();
        result = 31 * result + (aptNo != null ? aptNo.hashCode() : 0);
        result = 31 * result + zipcode.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + state.hashCode();
        return result;
    }
}
