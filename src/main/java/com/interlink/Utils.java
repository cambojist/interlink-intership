package com.interlink;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public final class Utils {
    public static File getCSVFile(String fileName) throws URISyntaxException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(fileName);
        return new File(Objects.requireNonNull(resource).toURI());
    }

    public static List<String[]> readCSVFile(File file) throws IOException, CsvException {
        CSVReader reader = new CSVReaderBuilder(new FileReader(file))
                .withSkipLines(1)
                .build();
        return reader.readAll();
    }

    public static LocalDate parseDate(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.US);
        return LocalDate.parse(s, formatter);
    }
}
