package com.codecool.bolec.model;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    private String sex;
    private String hairColour;
    private int height;

    public Director() {
    }

    public Director(String name, String surname, String birthdate, String sex, String hairColour, int height)  {
        this.name = name;
        this.surname = surname;
        this.birthdate = this.stringToDate(birthdate);
        this.sex = sex;
        this.hairColour = hairColour;
        this.height = height;
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        if (!(sex.equalsIgnoreCase("male") | sex.equalsIgnoreCase("female")))
            throw new IllegalArgumentException();
        this.sex = sex;
    }

    public String getHairColour() {
        return hairColour;
    }

    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height <= 0)
            throw new IllegalArgumentException();

        this.height = height;
    }

    private Date stringToDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = Calendar.getInstance().getTime();

        try {
            date = sdf.parse(dateString);

        } catch (ParseException e) {
            e.printStackTrace();
        }


        return date;
    }
}
