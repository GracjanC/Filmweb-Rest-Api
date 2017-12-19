package com.codecool.bolec.model;

public class Movie {

    private String title;
    private String category;
    private String director;
    private String review;
    private int minAge;
    private int rating;
    private int length;
    private int year;

    public Movie() {}


    public Movie(String title, String category, String director, String review, int minAge, int rating, int length, int year) {
        this.title = title;
        this.category = category;
        this.director = director;
        this.review = review;
        this.minAge = minAge;
        this.rating = rating;
        this.length = length;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDirector() {
        return director;
    }

    public String getReview() {
        return review;
    }

    public int getMinAge() {
        return minAge;
    }

    public int getRating() {
        return rating;
    }

    public int getLength() {
        return length;
    }

    public int getYear() {
        return year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
