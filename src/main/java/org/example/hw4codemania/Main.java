package org.example.hw4codemania;

import org.example.hw4codemania.reader.CsvReader;
import org.example.hw4codemania.service.MovieService;
import org.example.hw4codemania.service.impl.MovieServiceImpl;

public class Main {
    public static void main(String[] args) {

        CsvReader reader = new CsvReader();
        MovieService service = new MovieServiceImpl(reader);

        System.out.println(service.getTotalDurationMovie());
        System.out.println();
        System.out.println(service.getCountMovieAllGenre());
        System.out.println();
        System.out.println(service.getBestAndWorstMovie());
        System.out.println();
        System.out.println(service.getFiveMostPopularMovie());
        System.out.println();
        System.out.println(service.getNewAndOldMovie());

    }
}
