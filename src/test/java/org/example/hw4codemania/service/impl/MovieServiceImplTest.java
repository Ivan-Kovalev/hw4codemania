package org.example.hw4codemania.service.impl;

import org.example.hw4codemania.reader.CsvReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class MovieServiceImplTest {

    private CsvReader csvReader;
    private MovieServiceImpl movieService;

    private final List<String[]> mockMovieData = Arrays.asList(
            new String[]{"Movie1", "Drama", "01.01.2020", "120", "8.5", "English"},
            new String[]{"Movie2", "Comedy", "02.02.2021", "90", "7.3", "French"},
            new String[]{"Movie3", "Drama", "03.03.2022", "150", "9.1", "Spanish"},
            new String[]{"Movie4", "Action", "04.04.2019", "110", "6.8", "German"},
            new String[]{"Movie5", "Comedy", "05.05.2023", "95", "8.9", "Italian"},
            new String[]{"Movie6", "Horror", "06.06.2024", "80", "5.7", "Japanese"}
    );

    @BeforeEach
    void setUp() {
        csvReader = Mockito.mock(CsvReader.class);

        when(csvReader.getFile(anyString())).thenReturn(mockMovieData);

        when(csvReader.parseDate("01.01.2020")).thenReturn(LocalDate.of(2020, 1, 1));
        when(csvReader.parseDate("02.02.2021")).thenReturn(LocalDate.of(2021, 2, 2));
        when(csvReader.parseDate("03.03.2022")).thenReturn(LocalDate.of(2022, 3, 3));
        when(csvReader.parseDate("04.04.2019")).thenReturn(LocalDate.of(2019, 4, 4));
        when(csvReader.parseDate("05.05.2023")).thenReturn(LocalDate.of(2023, 5, 5));
        when(csvReader.parseDate("06.06.2024")).thenReturn(LocalDate.of(2024, 6, 6));

        movieService = new MovieServiceImpl(csvReader);
    }

    @Test
    void getCountMovieAllGenre_ShouldReturnGenreCounts() {
        String result = movieService.getCountMovieAllGenre();
        String expected = "Action: 1, Horror: 1, Drama: 2, Comedy: 2";
        assertEquals(expected, result);
    }

    @Test
    void getFiveMostPopularMovie_ShouldReturnTop5ByRating() {
        String result = movieService.getFiveMostPopularMovie();
        String expected = "[Movie3 - 9.1, Movie5 - 8.9, Movie1 - 8.5, Movie2 - 7.3, Movie4 - 6.8]";
        assertEquals(expected, result);
    }

    @Test
    void getNewAndOldMovie_ShouldReturnExtremePremiereDates() {
        String result = movieService.getNewAndOldMovie();
        String expected = "Oldest Movie: Movie4, 2019-04-04\n" +
                "Newest Movie: Movie6, 2024-06-06";
        assertEquals(expected, result);
    }

    @Test
    void getTotalDurationMovie_ShouldReturnSumOfDurations() {
        String result = movieService.getTotalDurationMovie();
        assertEquals("645", result);
    }

    @Test
    void getBestAndWorstMovie_ShouldReturnHighestAndLowestRated() {
        String result = movieService.getBestAndWorstMovie();
        String expected = "Best: Movie3 - 9.1\n" +
                " Worst: Movie6 - 5.7";
        assertEquals(expected, result);
    }
}