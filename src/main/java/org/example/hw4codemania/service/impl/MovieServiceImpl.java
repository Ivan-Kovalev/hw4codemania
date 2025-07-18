package org.example.hw4codemania.service.impl;

import org.example.hw4codemania.dto.MoviePremiere;
import org.example.hw4codemania.dto.MovieRate;
import org.example.hw4codemania.reader.CsvReader;
import org.example.hw4codemania.service.MovieService;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MovieServiceImpl implements MovieService {

    private final CsvReader reader;

    public MovieServiceImpl(CsvReader reader) {
        this.reader = reader;
    }

    @Override
    public String getCountMovieAllGenre() {
        Map<String, Long> countAllGenre = reader.getFile().stream()
                .map(arr -> arr[1])
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return countAllGenre.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));
    }

    @Override
    public String getFiveMostPopularMovie() {
        return reader.getFile().stream()
                .map(arr -> new MovieRate(arr[0], Double.parseDouble(arr[4])))
                .sorted(Comparator.comparingDouble(MovieRate::getRating).reversed())
                .limit(5)
                .toList().toString();
    }

    @Override
    public String getNewAndOldMovie() {
        MoviePremiere oldest = reader.getFile().stream()
                .map(arr -> new MoviePremiere(arr[0], reader.getDate(arr[2])))
                .min(Comparator.comparing(MoviePremiere::getPremiere))
                .orElse(null);

        MoviePremiere newest = reader.getFile().stream()
                .map(arr -> new MoviePremiere(arr[0], reader.getDate(arr[2])))
                .max(Comparator.comparing(MoviePremiere::getPremiere))
                .orElse(null);

        return "Oldest Movie: " + oldest + "\n" + "Newest Movie: " + newest;
    }

    @Override
    public String getTotalDurationMovie() {
        return reader.getFile().stream()
                .map(arr -> Integer.parseInt(arr[3]))
                .reduce(0, Integer::sum).toString();
    }

    @Override
    public String getBestAndWorstMovie() {

        MovieRate best = reader.getFile().stream()
                .map(arr -> new MovieRate(arr[0], Double.parseDouble(arr[4])))
                .sorted(Comparator.comparingDouble(MovieRate::getRating).reversed())
                .findFirst().get();

        MovieRate worst = reader.getFile().stream()
                .map(arr -> new MovieRate(arr[0], Double.parseDouble(arr[4])))
                .sorted(Comparator.comparingDouble(MovieRate::getRating))
                .findFirst().get();

        return "Best: " + best + "\n Worst: " + worst;
    }
}
