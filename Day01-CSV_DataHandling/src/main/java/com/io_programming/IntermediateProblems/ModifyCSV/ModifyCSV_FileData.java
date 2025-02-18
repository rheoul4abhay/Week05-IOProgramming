package com.io_programming.IntermediateProblems.ModifyCSV;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class ModifyCSV_FileData {

    public static void modifyCSV(){
        String old_filePath = "src/main/java/com/io_programming/IntermediateProblems/ModifyCSV/employees.csv";
        String new_filePath = "src/main/java/com/io_programming/IntermediateProblems/ModifyCSV/updated_employees.csv";

        DecimalFormat df = new DecimalFormat("0.0");
        try(CSVReader csvReader = new CSVReaderBuilder(new FileReader(old_filePath)).withSkipLines(1).build()){
            List<String[]> allData = csvReader.readAll();

            for(String[] row: allData){
                if(row[2].equalsIgnoreCase("IT")){
                    double salary = Double.parseDouble(row[3]);
                    row[3] = df.format(salary * 1.10); //Increase by 10%
                }
            }

            try (CSVWriter csvWriter = new CSVWriter(new FileWriter(new_filePath))){
                csvWriter.writeNext(new String[]{"ID", "Name", "Department", "Salary"});
                csvWriter.writeAll(allData); //writing back updated data
            }
            System.out.println("CSV file updated successfully!");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        modifyCSV();
    }
}
