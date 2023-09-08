package com.motmey.TEST.Models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Time
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String doctor1;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoctor1() {
        return doctor1;
    }

    public void setDoctor1(String doctor1) {
        this.doctor1 = doctor1;
    }

    public Time() {
    }


    public Time(String doctor1)
    {
        this.doctor1 = doctor1;
    }
}
