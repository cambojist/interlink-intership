package com.interlink;

import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Main {
    private static final String CSV_FILE_NAME = "acme_worksheet.csv";

    public static void main(String[] args) throws URISyntaxException, IOException, CsvException {
        File file = Utils.getCSVFile(CSV_FILE_NAME);
        List<String[]> employees = Utils.readCSVFile(file);

        CSVConverter csvConverter = new CSVConverter();
        List<List<String>> newCSV = csvConverter.convertCSV(employees);

        List<String> headers = csvConverter.getParsedDistDates();
        headers.add(0, "Name / Date");
        Utils.buildCSV(headers, newCSV);
    }
}