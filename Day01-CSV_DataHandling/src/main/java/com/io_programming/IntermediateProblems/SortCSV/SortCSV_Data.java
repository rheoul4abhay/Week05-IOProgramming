package com.io_programming.IntermediateProblems.SortCSV;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class SortCSV_Data {

    public static void SortAndPrintTopSalaries(String filepath){
        try(CSVReader csvReader = new CSVReaderBuilder(new FileReader(filepath)).withSkipLines(1).build()){
            List<String[]> allData = csvReader.readAll();

            allData.sort((a,b) -> Double.compare(Double.parseDouble(b[3]), Double.parseDouble(a[3])));

            System.out.println("---Top 5 highest-paid employees---");
            for(int i = 0; i < Math.min(5, allData.size()); i++){
                System.out.println("-> " + allData.get(i)[1]);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String filepath = "src/main/java/com/io_programming/IntermediateProblems/SortCSV/employees.csv";
        SortAndPrintTopSalaries(filepath);
    }
}
