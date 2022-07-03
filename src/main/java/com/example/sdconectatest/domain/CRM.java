package com.example.sdconectatest.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CRM {
    @Column(nullable = false, length = 45)
    private String code;
    @Column(nullable = false, length = 2)
    private String uf;
    private String specialty;

    public CRM() {
    }

    public CRM(String code, String uf, String specialty) {
        this.code = code;
        this.uf = uf;
        this.specialty = specialty;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
