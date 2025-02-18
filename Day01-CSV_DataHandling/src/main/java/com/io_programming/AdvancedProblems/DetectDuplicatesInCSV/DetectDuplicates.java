package com.io_programming.AdvancedProblems.DetectDuplicatesInCSV;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DetectDuplicates {

    public static void detectDuplicates(String filepath) {
        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(filepath)).withSkipLines(1).build()) {
            List<String[]> allData = csvReader.readAll();
            Set<String> seenIds = new HashSet<>();
            Set<String[]> duplicateRecords = new HashSet<>();

            for (String[] row : allData) {
                String id = row[0];  // Assuming ID is the first column
                if (!seenIds.add(id)) {
                    duplicateRecords.add(row);
                }
            }

            if (duplicateRecords.isEmpty()) {
                System.out.println("No duplicates found.");
            } else {
                System.out.println("Duplicate records:");
                for (String[] record : duplicateRecords) {
                    System.out.println(String.join(", ", record));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filepath = "src/main/java/com/io_programming/AdvancedProblems/DetectDuplicatesInCSV/students.csv";
        detectDuplicates(filepath);
    }
}

