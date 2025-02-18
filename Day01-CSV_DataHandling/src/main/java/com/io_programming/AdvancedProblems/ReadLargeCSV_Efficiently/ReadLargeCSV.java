package com.io_programming.AdvancedProblems.ReadLargeCSV_Efficiently;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ReadLargeCSV {

    public static void readCSVInChunks(String filepath) {
        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(filepath)).withSkipLines(1).build()) {
            List<String[]> batch;
            int totalRecordsProcessed = 0;

            while ((batch = csvReader.readAll()).size() > 0) {
                int count = 0;
                for (String[] row : batch) {
                    if (count < 100) {
                        totalRecordsProcessed++;
                        count++;
                    } else {
                        break;
                    }
                }
                System.out.println("Records processed: " + totalRecordsProcessed);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filepath = "src/main/java/com/io_programming/AdvancedProblems/ReadLargeCSV_Efficiently/large_file.csv";
        readCSVInChunks(filepath);
    }
}
