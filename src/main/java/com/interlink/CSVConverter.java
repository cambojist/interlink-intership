package com.interlink;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CSVConverter {
    private final List<String> names;
    private final List<String> dates;

    private List<String> distNames;
    private List<String> distDates;

    public CSVConverter() {
        names = new ArrayList<>();
        dates = new ArrayList<>();
    }

    public List<List<String>> convertCSV(List<String[]> employees) {
        parseCSV(employees);
        getDistNamesAndDates();

        List<List<String>> newCSV = new ArrayList<>();
        for (String name : distNames) {
            List<String> employee = new ArrayList<>();
            employee.add(name);
            for (String date : distDates) {
                employee.add(getWorkTime(name, date, employees));
            }
            newCSV.add(employee);
        }
        return newCSV;
    }

    private String getWorkTime(String name, String date, List<String[]> employees) {
        for (String[] employee : employees) {
            if (employee[0].equals(name) && employee[1].equals(date)) {
                return employee[2];
            }
        }
        return "0";
    }

    private void getDistNamesAndDates() {
        distNames = names.stream().distinct().collect(toList());
        distDates = dates.stream().distinct().collect(toList());
    }

    private void parseCSV(List<String[]> employees) {
        for (String[] emp : employees) {
            names.add(emp[0]);
            dates.add(emp[1]);
        }
    }

    public List<String> getParsedDistDates() {
        return distDates.stream()
                .map(Utils::parseDate)
                .map(LocalDate::toString)
                .collect(toList());
    }
}
