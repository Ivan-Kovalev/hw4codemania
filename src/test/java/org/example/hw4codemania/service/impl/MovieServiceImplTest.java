package org.example.hw4codemania.service.impl;

import org.example.hw4codemania.reader.CsvReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.example.hw4codemania.util.TestUtil.FILE;
import static org.mockito.Mockito.when;

class MovieServiceImplTest {

    MovieServiceImpl service;

    @Mock
    CsvReader reader;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new MovieServiceImpl(reader);
        String filePath = "/NetflixOriginals.csv";
        when(reader.getFile(filePath)).thenReturn(FILE);
    }

    @Test
    void getCountMovieAllGenre() {
        String expected =
                "Science fiction/Drama: 1, Documentary: 1, Action: 1, Horror: 1, Thriller: 3, Comedy: 2, Horror thriller: 1";
        Assertions.assertEquals(expected, service.getCountMovieAllGenre());
    }

    @Test
    void getFiveMostPopularMovie() {
        String expected =
                "[Christmas Crossfire - 4.8, Sentinelle - 4.7, 5 Star Christmas - 4.6, Rattlesnake - 4.6, The Girl on the Train - 4.4]";
        Assertions.assertEquals(expected, service.getFiveMostPopularMovie());
    }

    @Test
    void getNewAndOldMovie() {
        String expected =
                "Oldest Movie: The Open House, 2018-01-12\nNewest Movie: Sentinelle, 2021-03-05";
        Assertions.assertEquals(expected, service.getNewAndOldMovie());
    }

    @Test
    void getTotalDurationMovie() {
        Assertions.assertEquals("900", service.getTotalDurationMovie());
    }

    @Test
    void getBestAndWorstMovie() {
        String expected = "Best: Christmas Crossfire - 4.8\n" +
                " Worst: Enter the Anime - 2.5";
        Assertions.assertEquals(expected, service.getBestAndWorstMovie());
    }
}