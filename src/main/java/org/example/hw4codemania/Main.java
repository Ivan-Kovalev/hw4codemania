package org.example.hw4codemania;

import org.example.hw4codemania.reader.CsvReader;
import org.example.hw4codemania.service.MovieService;
import org.example.hw4codemania.service.impl.MovieServiceImpl;

public class Main {
    public static void main(String[] args) {

        CsvReader reader = new CsvReader();
        MovieService service = new MovieServiceImpl(reader);

        System.out.println(service.getBestAndWorstMovie());

    }
}
