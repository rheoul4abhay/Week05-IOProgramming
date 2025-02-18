package com.io_programming.BasicProblems.ReadCountRows;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ReadDataAndCountRows {
    public static void main(String[] args) {

        String filepath = "src/main/java/com/io_programming/BasicProblems/ReadCountRows/students.csv";
        try(CSVReader csvReader = new CSVReaderBuilder(new FileReader(filepath)).withSkipLines(1).build()){
            List<String[]> allData = csvReader.readAll(); //to read all the data at once
            for(String[] row: allData){
                for(String data: row){
                    System.out.print(data + " ");
                }
                System.out.println();
            }
            System.out.println("Number of records in file: " + allData.size());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
