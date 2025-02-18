package com.io_programming.IntermediateProblems.FilterRecords;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FilterCSV_Data {
    public static void main(String[] args) {
        String filepath = "src/main/java/com/io_programming/IntermediateProblems/FilterRecords/students.csv";

        try(CSVReader csvReader = new CSVReaderBuilder(new FileReader(filepath)).withSkipLines(1).build()){
            /*
            List<String[]> allData = csvReader.readAll();
            for(String[] row: allData){
                boolean recordFound = false;
                for(String cell: row){
                    if(Integer.parseInt(row[3]) > 80){
                        recordFound = true;
                        System.out.print(cell + " ");
                    }
                }
                if(recordFound) System.out.println();
            }
             */

            //Using filter stream
            List<String[]> filteredData = csvReader.readAll().stream().filter(row -> Integer.parseInt(row[3]) > 80).collect(Collectors.toList());
            filteredData.forEach(row -> System.out.println(String.join(" ", row)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
