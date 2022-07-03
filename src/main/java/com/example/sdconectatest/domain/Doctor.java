package com.example.sdconectatest.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String surname;

    private String phone;

    @ElementCollection
    private List<CRM> crms;

    public Doctor() {
    }

    public Doctor(Integer id, String email, String password, String name, String surname, String phone, List<CRM> crms) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.crms = crms;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String mobilePhone) {
        this.phone = mobilePhone;
    }

    public List<CRM> getCrms() {
        return crms;
    }

    public void setCrms(List<CRM> crms) {
        this.crms = crms;
    }

    public void update(Doctor doctor) {
        this.email = doctor.getEmail().isBlank() ? this.email : doctor.getEmail();
        this.password = doctor.getPassword().isBlank() ? this.password : doctor.getPassword();
        this.name = doctor.getName().isBlank() ? this.name : doctor.getName();
        this.surname = doctor.getSurname().isBlank() ? this.surname : doctor.getSurname();
        this.phone = doctor.getPhone().isBlank() ? this.phone : doctor.getPhone();
        this.crms = doctor.crms.isEmpty() ? this.crms : doctor.crms;
    }
}
