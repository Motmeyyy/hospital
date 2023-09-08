package com.motmey.TEST.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reserve
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name, surname,middle_name,history,blood_type,medical_policy,email,doctor;
    private int born_data;

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public Reserve(String name, String surname, String middle_name, String history, String blood_type, String medical_policy, String email, int born_data, String doctor) {
        this.name = name;
        this.surname = surname;
        this.middle_name = middle_name;
        this.history = history;
        this.blood_type = blood_type;
        this.doctor = doctor;
        this.medical_policy = medical_policy;
        this.email = email;
        this.born_data = born_data;
    }

    public Reserve() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }



    public String getMedical_policy() {
        return medical_policy;
    }

    public void setMedical_policy(String medical_policy) {
        this.medical_policy = medical_policy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBorn_data() {
        return born_data;
    }

    public void setBorn_data(int born_data) {
        this.born_data = born_data;
    }
}
