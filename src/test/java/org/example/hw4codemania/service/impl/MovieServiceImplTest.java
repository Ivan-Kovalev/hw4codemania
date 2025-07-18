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
        when(reader.getFile()).thenReturn(FILE);
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
                "Oldest Movie: The Open House, January 19, 2018\nNewest Movie: Sentinelle, March 5, 2021";
        Assertions.assertEquals(expected, service.getNewAndOldMovie());
    }

    @Test
    void getTotalDurationMovie() {
    }

    @Test
    void getBestAndWorstMovie() {
    }
}