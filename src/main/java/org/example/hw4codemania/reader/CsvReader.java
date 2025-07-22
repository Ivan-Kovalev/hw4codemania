package org.example.hw4codemania.reader;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

public class CsvReader {

    public List<String[]> getFile(String filePath) {
        try (InputStream inputStream = getClass().getResourceAsStream(filePath);
             CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {

            return reader.readAll().stream().skip(1).toList();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при чтении файла.");
        }
    }

    public LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH));
        } catch (DateTimeParseException e1) {
            try {
                return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MMMM d. yyyy", Locale.ENGLISH));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            System.err.println("Ошибка при парсинге даты: " + dateString);
            e1.printStackTrace();
            return null;
        }
    }

}
