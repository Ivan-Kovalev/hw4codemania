package org.example.hw4codemania.service.impl;

import org.example.hw4codemania.dto.MoviePremiere;
import org.example.hw4codemania.dto.MovieRate;
import org.example.hw4codemania.reader.CsvReader;
import org.example.hw4codemania.service.MovieService;
import org.example.hw4codemania.util.MovieReaderUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.example.hw4codemania.util.MovieReaderUtil.*;

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
                .map(arr -> arr[GENRE_INDEX])
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return countAllGenre.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));
    }

    @Override
    public String getFiveMostPopularMovie() {
        return movieData.stream()
                .map(arr -> new MovieRate(arr[TITLE_INDEX], Double.parseDouble(arr[RATING_INDEX])))
                .sorted(Comparator.comparingDouble(MovieRate::getRating).reversed())
                .limit(5)
                .toList().toString();
    }

    @Override
    public String getNewAndOldMovie() {
        MoviePremiere oldest = movieData.stream()
                .map(arr -> new MoviePremiere(arr[TITLE_INDEX], reader.parseDate(arr[PREMIERE_INDEX])))
                .min(Comparator.comparing(MoviePremiere::getPremiere))
                .orElse(null);

        MoviePremiere newest = movieData.stream()
                .map(arr -> new MoviePremiere(arr[TITLE_INDEX], reader.parseDate(arr[PREMIERE_INDEX])))
                .max(Comparator.comparing(MoviePremiere::getPremiere))
                .orElse(null);

        return "Oldest Movie: " + oldest + "\n" + "Newest Movie: " + newest;
    }

    @Override
    public String getTotalDurationMovie() {
        return movieData.stream()
                .map(arr -> Integer.parseInt(arr[RUNTIME_INDEX]))
                .reduce(0, Integer::sum).toString();
    }

    @Override
    public String getBestAndWorstMovie() {
        MovieRate best = movieData.stream()
                .map(arr -> new MovieRate(arr[TITLE_INDEX], Double.parseDouble(arr[RATING_INDEX])))
                .max(Comparator.comparingDouble(MovieRate::getRating))
                .orElse(null);

        MovieRate worst = movieData.stream()
                .map(arr -> new MovieRate(arr[TITLE_INDEX], Double.parseDouble(arr[RATING_INDEX])))
                .min(Comparator.comparingDouble(MovieRate::getRating))
                .orElse(null);
        return "Best: " + best + "\n Worst: " + worst;
    }
}
