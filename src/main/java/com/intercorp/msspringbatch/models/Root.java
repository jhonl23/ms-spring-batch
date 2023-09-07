package com.intercorp.msspringbatch.models;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;


@XmlRootElement(name = "root")
@Getter
@Setter
@Entity
@Table(name = "roots")
public class Root implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    private int random;
    private float random_float;
    private boolean bool;
    private String date;
    private String regEx;
    @Column(name = "enum")
    private String enumValue;
    private String elt;
    private String last_update;
    private String last_modified;

    @XmlElement(name = "enum")
    public String getEnumValue() {
        return enumValue;
    }

    public void setEnumValue(String enumValue) {
        this.enumValue = enumValue;
    }

}