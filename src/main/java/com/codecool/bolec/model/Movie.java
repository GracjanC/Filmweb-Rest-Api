package com.codecool.bolec.model;

import com.codecool.bolec.exceptions.NegativeValueException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @OneToOne
    private Category category;
    @OneToOne
    private Director director;
    private String review;
    private int minAge;
    private int rating;
    private int length;
    private int year;

    public Movie() {}


    public Movie(String title, Category category, Director director, String review, int minAge, int rating, int length, int year) {
        this.title = title;
        this.category = category;
        this.director = director;
        this.review = review;
        this.minAge = minAge;
        this.rating = rating;
        this.length = length;
        this.year = year;
    }

    public String getTitle() { return title; }

    public Director getDirector() { return director; }

    public String getReview() { return review; }

    public int getMinAge() { return minAge; }

    public int getRating() { return rating; }

    public int getLength() { return length; }

    public int getYear() { return year; }

    public void setTitle(String title) { this.title = title; }

    public void setDirector(Director director) { this.director = director; }

    public void setReview(String review) { this.review = review; }

    public void setMinAge(int minAge) throws NegativeValueException {

        if (minAge > 0) {
            this.minAge = minAge;
        } else {
            throw new NegativeValueException("Wrong min. age provided, should be positive.");
        }

    }

    public void setRating(int rating) throws NegativeValueException {

        if(rating >= 0) {
            this.rating = rating;

        } else {
            throw new NegativeValueException("Wrong rating provided, shouldn't be negative.");
        }
    }

    public void setLength(int length) throws NegativeValueException {

        if(length > 0) {
            this.length = length;
        } else {
            throw new NegativeValueException("length should be positive");
        }

    }

    public void setYear(int year) { this.year = year; }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
