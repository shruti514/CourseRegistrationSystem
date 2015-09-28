package org.courseregistration.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Shital on 9/27/2015.
 */

@Entity
@Table(name = "address")
@PrimaryKeyJoinColumn(name = "user_id")
public class Address implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "address_id")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "address")

    @Column(name = "street_name", nullable = false)
    private String street_name;

    @Column(name = "apt_no", nullable = false)
    private Integer apt_no;

    @Column(name="zip_code", nullable = false)
    private Integer zip_code;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}

    public String getStreetName() {return street_name;}
    public void setStreetName(String street_name) {
        this.street_name= street_name;
    }

    public Integer getAptNo(){return apt_no;}
    public void setAptNo(Integer apt_no){this.apt_no=apt_no;}

    public Integer getZipCode(){return zip_code;}
    public void setZipCode(Integer zip_code){this.zip_code=zip_code;}

    public String setCity(){return city;}
    public void setCity(String city){this.city=city;}

    public String setState(){return state;}
    public void setState(String state){this.state=state;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!street_name.equals(address.street_name)) return false;
        if (!apt_no.equals(address.apt_no)) return false;
        if (!zip_code.equals(address.zip_code)) return false;
        if (!city.equals(address.city)) return false;
        return state.equals(address.state);

    }

    @Override
    public int hashCode() {
        int result = street_name.hashCode();
        result = 31 * result + apt_no.hashCode();
        result = 31 * result + zip_code.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + state.hashCode();
        return result;
    }
}
