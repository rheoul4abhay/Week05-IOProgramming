package com.io_programming.AdvancedProblems.ProcessValidCSV;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ValidateCSVData {

    // Regex for Email Validation
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    // Regex for Phone Number Validation (10 digits)
    private static final String PHONE_REGEX = "^[0-9]{10}$";

    public static void validateCSV(String filepath) {

        Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
        Pattern phonePattern = Pattern.compile(PHONE_REGEX);

        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(filepath)).withSkipLines(1).build()) {

            List<String[]> invalidData = csvReader.readAll().stream()
                    .filter(row -> !emailPattern.matcher(row[2]).matches() || !phonePattern.matcher(row[3]).matches()) // Validate Email & Phone
                    .collect(Collectors.toList());

            if (invalidData.isEmpty()) {
                System.out.println("All records are valid!");
            } else {
                System.out.println("------ Invalid Records Found ------");
                for (String[] row : invalidData) {
                    System.out.print("Error: ");
                    if (!emailPattern.matcher(row[2]).matches()) {
                        System.out.print("Invalid Email (" + row[2] + ") ");
                    }
                    if (!phonePattern.matcher(row[3]).matches()) {
                        System.out.print("Invalid Phone Number (" + row[3] + ")");
                    }
                    System.out.println();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filepath = "src/main/java/com/io_programming/AdvancedProblems/ProcessValidCSV/employees.csv";
        validateCSV(filepath);
    }
}
