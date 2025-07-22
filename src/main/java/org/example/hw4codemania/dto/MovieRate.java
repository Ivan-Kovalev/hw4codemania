package org.example.hw4codemania.dto;

public class MovieRate {
    private String title;
    private double rating;

    public MovieRate(String title, Double rating) {
        this.title = title;
        this.rating = Math.round(rating * 10) / 10.0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return title + " - " + rating;
    }
}
