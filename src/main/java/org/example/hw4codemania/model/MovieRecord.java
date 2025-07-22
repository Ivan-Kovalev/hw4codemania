package org.example.hw4codemania.model;

import java.util.Objects;

public class MovieRecord {

    private String title;
    private String genre;
    private String premiere;
    private Integer runtime;
    private Double rating;
    private String language;

    public MovieRecord(String title, String genre, String premiere, Integer runtime, Double rating, String language) {
        this.title = title;
        this.genre = genre;
        this.premiere = premiere;
        this.runtime = runtime;
        this.rating = Math.round(rating * 10) / 10.0;
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPremiere() {
        return premiere;
    }

    public void setPremiere(String premiere) {
        this.premiere = premiere;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieRecord that = (MovieRecord) o;
        return Objects.equals(title, that.title) && Objects.equals(genre, that.genre) && Objects.equals(premiere, that.premiere) && Objects.equals(runtime, that.runtime) && Objects.equals(rating, that.rating) && Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genre, premiere, runtime, rating, language);
    }

    @Override
    public String toString() {
        return "MovieRecord{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", premiere=" + premiere +
                ", runtime=" + runtime +
                ", rating=" + rating +
                ", language='" + language + '\'' +
                '}';
    }
}
