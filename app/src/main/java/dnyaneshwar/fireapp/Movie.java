package dnyaneshwar.fireapp;

/**
 * Created by Dnyaneshwar on 12/25/2016.
 */
//package com.example.dnyaneshwar.myapplication;

/**
 * Created by Dnyaneshwar on 12/24/2016.
 */
public class Movie {
    private String title, genre;

    public Movie() {
    }

    public Movie(String title, String genre) {
        this.title = title;
        this.genre = genre;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

//    public String getYear() {
//        return year;
    //  }

//    public void setYear(String year) {
    //      this.year = year;
    // }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}