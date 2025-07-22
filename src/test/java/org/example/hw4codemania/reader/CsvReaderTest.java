package org.example.hw4codemania.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvReaderTest {
    private CsvReader csvReader;

    @BeforeEach
    void setUp() {
        csvReader = new CsvReader();
    }

    @Test
    void testGetFile_validFilePath() {
        List<String[]> result = csvReader.getFile("/NetflixOriginals.csv");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void testParseDate_validFormat1() {
        LocalDate date = csvReader.parseDate("January 1, 2021");
        assertNotNull(date);
        assertEquals(LocalDate.of(2021, 1, 1), date);
    }

    @Test
    void testParseDate_validFormat2() {
        LocalDate date = csvReader.parseDate("January 1. 2021");
        assertNotNull(date);
        assertEquals(LocalDate.of(2021, 1, 1), date);
    }

    @Test
    void testParseDate_invalidFormat() {
        LocalDate date = csvReader.parseDate("Invalid Date");
        assertNull(date);
    }
}