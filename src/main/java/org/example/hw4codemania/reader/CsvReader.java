package org.example.hw4codemania.reader;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

public class CsvReader {

    public List<String[]> getFile() {
        try (InputStream inputStream = getClass().getResourceAsStream("/NetflixOriginals.csv");
             CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {

            return reader.readAll().stream().skip(1).toList();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при чтении файла.");
        }
    }

    public LocalDate getDate(String dateString) {
        DateTimeFormatter dateFormat = new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH))
                .appendOptional(DateTimeFormatter.ofPattern("MMMM d. yyyy", Locale.ENGLISH))
                .toFormatter();

        try {
            return LocalDate.parse(dateString, dateFormat);
        } catch (DateTimeParseException e) {
            System.err.println("Ошибка при парсинге даты: " + dateString);
            e.printStackTrace();
            return null;
        }
    }

}
