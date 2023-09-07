package com.intercorp.msspringbatch.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

@Entity
@Table(name = "persons")
public class Person implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String firstname;
    private String lastname;
    private String city;
    private String country;
    private String firstname2;
    private String lastname2;
    private String email;

    @XmlAttribute(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @XmlAttribute(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @XmlAttribute(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlAttribute(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @XmlAttribute(name = "firstname2")
    public String getFirstname2() {
        return firstname2;
    }

    public void setFirstname2(String firstname2) {
        this.firstname2 = firstname2;
    }

    @XmlAttribute(name = "lastname2")
    public String getLastname2() {
        return lastname2;
    }

    public void setLastname2(String lastname2) {
        this.lastname2 = lastname2;
    }

    @XmlAttribute(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
