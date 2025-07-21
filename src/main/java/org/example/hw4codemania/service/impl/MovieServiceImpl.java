package org.example.hw4codemania.service.impl;

import org.example.hw4codemania.dto.MoviePremiere;
import org.example.hw4codemania.dto.MovieRate;
import org.example.hw4codemania.reader.CsvReader;
import org.example.hw4codemania.service.MovieService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MovieServiceImpl implements MovieService {

    private final CsvReader reader;
    private List<String[]> movieData;

    public MovieServiceImpl(CsvReader reader) {
        this.reader = reader;
        loadMovieData();
    }

    private void loadMovieData() {
        try {
            String filePath = "/NetflixOriginals.csv";
            movieData = reader.getFile(filePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load movie data", e);
        }
    }

    @Override
    public String getCountMovieAllGenre() {
        Map<String, Long> countAllGenre = movieData.stream()
                .map(arr -> arr[1])
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return countAllGenre.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));
    }

    @Override
    public String getFiveMostPopularMovie() {
        return movieData.stream()
                .map(arr -> new MovieRate(arr[0], Double.parseDouble(arr[4])))
                .sorted(Comparator.comparingDouble(MovieRate::getRating).reversed())
                .limit(5)
                .toList().toString();
    }

    @Override
    public String getNewAndOldMovie() {
        MoviePremiere oldest = movieData.stream()
                .map(arr -> new MoviePremiere(arr[0], reader.parseDate(arr[2])))
                .min(Comparator.comparing(MoviePremiere::getPremiere))
                .orElse(null);

        MoviePremiere newest = movieData.stream()
                .map(arr -> new MoviePremiere(arr[0], reader.parseDate(arr[2])))
                .max(Comparator.comparing(MoviePremiere::getPremiere))
                .orElse(null);

        return "Oldest Movie: " + oldest + "\n" + "Newest Movie: " + newest;
    }

    @Override
    public String getTotalDurationMovie() {
        return movieData.stream()
                .map(arr -> Integer.parseInt(arr[3]))
                .reduce(0, Integer::sum).toString();
    }

    @Override
    public String getBestAndWorstMovie() {
        MovieRate best = movieData.stream()
                .map(arr -> new MovieRate(arr[0], Double.parseDouble(arr[4])))
                .max(Comparator.comparingDouble(MovieRate::getRating))
                .orElse(null);

        MovieRate worst = movieData.stream()
                .map(arr -> new MovieRate(arr[0], Double.parseDouble(arr[4])))
                .min(Comparator.comparingDouble(MovieRate::getRating))
                .orElse(null);
        return "Best: " + best + "\n Worst: " + worst;
    }
}
