package org.example.hw4codemania.reader;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component
public class CsvReader {

    private final String FILE_PATH = "C:\\Users\\Иван\\Desktop\\NetflixOriginals.csv";

    public List<String[]> getFile() {
        try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH))) {
            List<String[]> result = reader.readAll();
            result.remove(1);
            return result;
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public LocalDate getDate(String dateString) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);

        try {
            return LocalDate.parse(dateString, dateFormat);
        } catch (DateTimeParseException e) {
            System.err.println("Ошибка при парсинге даты: " + dateString);
            e.printStackTrace();
            return null;
        }
    }

}
