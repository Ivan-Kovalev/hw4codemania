package org.example.hw4codemania.service.impl;

import org.example.hw4codemania.dto.MoviePremiere;
import org.example.hw4codemania.dto.MovieRate;
import org.example.hw4codemania.model.MovieRecord;
import org.example.hw4codemania.reader.CsvReader;
import org.example.hw4codemania.service.MovieService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.example.hw4codemania.util.MovieReaderUtil.*;

public class MovieServiceImpl implements MovieService {

    private final CsvReader reader;
    private List<MovieRecord> movieData;
    private final String filePath = "/NetflixOriginals.csv";

    public MovieServiceImpl(CsvReader reader) {
        this.reader = reader;
        loadMovieData(filePath);
    }

    private void loadMovieData(String filePath) {
        try {
            movieData = reader.getFile(filePath).stream()
                    .map(arr -> new MovieRecord(
                            arr[TITLE_INDEX],
                            arr[GENRE_INDEX],
                            arr[PREMIERE_INDEX],
                            Integer.parseInt(arr[RUNTIME_INDEX]),
                            Double.parseDouble(arr[RATING_INDEX]),
                            arr[LANGUAGE_INDEX]))
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load movie data", e);
        }
    }

    @Override
    public String getCountMovieAllGenre() {
        Map<String, Long> countAllGenre = movieData.stream()
                .map(MovieRecord::getGenre)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return countAllGenre.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));
    }

    @Override
    public String getFiveMostPopularMovie() {
        return movieData.stream()
                .map(arr -> new MovieRate(arr.getTitle(), arr.getRating()))
                .sorted(Comparator.comparingDouble(MovieRate::getRating).reversed())
                .limit(5)
                .toList().toString();
    }

    @Override
    public String getNewAndOldMovie() {
        MoviePremiere oldest = movieData.stream()
                .map(arr -> new MoviePremiere(arr.getTitle(), reader.parseDate(arr.getPremiere())))
                .min(Comparator.comparing(MoviePremiere::getPremiere))
                .orElse(null);

        MoviePremiere newest = movieData.stream()
                .map(arr -> new MoviePremiere(arr.getTitle(), reader.parseDate(arr.getPremiere())))
                .max(Comparator.comparing(MoviePremiere::getPremiere))
                .orElse(null);

        return "Oldest Movie: " + oldest + "\n" + "Newest Movie: " + newest;
    }

    @Override
    public String getTotalDurationMovie() {
        return movieData.stream()
                .map(MovieRecord::getRuntime)
                .reduce(0, Integer::sum).toString();
    }

    @Override
    public String getBestAndWorstMovie() {
        MovieRate best = movieData.stream()
                .map(arr -> new MovieRate(arr.getTitle(), arr.getRating()))
                .max(Comparator.comparingDouble(MovieRate::getRating))
                .orElse(null);

        MovieRate worst = movieData.stream()
                .map(arr -> new MovieRate(arr.getTitle(), arr.getRating()))
                .min(Comparator.comparingDouble(MovieRate::getRating))
                .orElse(null);
        return "Best: " + best + "\n Worst: " + worst;
    }
}
