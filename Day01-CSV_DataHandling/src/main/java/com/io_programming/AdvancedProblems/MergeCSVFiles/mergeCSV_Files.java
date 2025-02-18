package com.io_programming.AdvancedProblems.MergeCSVFiles;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mergeCSV_Files {

    public static void mergeCSV(String file1, String file2){
        String outputFile = "src/main/java/com/io_programming/AdvancedProblems/MergeCSVFiles/studentsMerged.csv";

        Map<String, String[]> studentData = new HashMap<>();

        try(CSVReader reader1 = new CSVReaderBuilder(new FileReader(file1)).withSkipLines(1).build();
            CSVReader reader2 = new CSVReaderBuilder(new FileReader(file2)).withSkipLines(1).build();
            CSVWriter writer = new CSVWriter(new FileWriter(outputFile))){

            List<String[]> data1 = reader1.readAll();
            for(String[] row: data1){
                studentData.put(row[0], row);
            }

            List<String[]> data2 = reader2.readAll();
            for(String[] row: data2){
                String id = row[0];
                if(studentData.containsKey(id)){
                    studentData.put(row[0], row);
                    String[] details = studentData.get(id);
                    studentData.put(id, new String[]{details[0], details[1], details[2], row[1], row[2]});
                }
            }

            //write merged data to a new csv file
            writer.writeNext(new String[]{"ID", "Name", "Age", "Marks", "Grade"}); //header
            for(String[] row: studentData.values()){
                writer.writeNext(row);
            }
            System.out.println("CSV files merged successfully into " + outputFile);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String file1 = "src/main/java/com/io_programming/AdvancedProblems/MergeCSVFiles/students1.csv";
        String file2 = "src/main/java/com/io_programming/AdvancedProblems/MergeCSVFiles/students2.csv";
        mergeCSV(file1, file2);
    }
}
