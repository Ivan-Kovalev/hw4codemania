package org.example.hw4codemania.dto;

import java.time.LocalDate;

public class MoviePremiere {

    private String title;
    private LocalDate premiere;

    public MoviePremiere(String title, LocalDate premiere) {
        this.title = title;
        this.premiere = premiere;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPremiere() {
        return premiere;
    }

    public void setPremiere(LocalDate premiere) {
        this.premiere = premiere;
    }

    @Override
    public String toString() {
        return title + ", " + premiere;
    }
}
