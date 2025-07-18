package org.example.hw4codemania.controller;

import org.example.hw4codemania.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping(path = "/count")
    public ResponseEntity<String> getCountMovieAllGenre() {
        return null;
    }

    @GetMapping(path = "/top")
    public ResponseEntity<String> getFiveMostPopularMovie() {
        return null;
    }

    @GetMapping(path = "/new-old")
    public ResponseEntity<String> getNewAndOldMovie() {
        return null;
    }

    @GetMapping(path = "/total-duration")
    public ResponseEntity<String> getTotalDurationMovie() {
        return null;
    }

    @GetMapping(path = "/best-worst")
    public ResponseEntity<String> getBestAndWorstMovie() {
        return null;
    }
}
