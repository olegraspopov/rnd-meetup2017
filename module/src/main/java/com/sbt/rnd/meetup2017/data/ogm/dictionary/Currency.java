package com.sbt.rnd.meetup2017.data.ogm.dictionary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Currency {

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String code;
    private int intCode;
    private String name;

    public Currency(String code, int intCode, String name) {
        this.code = code;
        this.intCode = intCode;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIntCode() {
        return intCode;
    }

    public void setIntCode(int intCode) {
        this.intCode = intCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
