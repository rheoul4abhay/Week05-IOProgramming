package com.io_programming.IntermediateProblems.SearchRecords;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchRecordInCSV {

    //For this problem , key will be employee name
    public static void searchRecord(String filepath, String key){

        //Approach 1 -> Using streams
        try(CSVReader csvReader = new CSVReaderBuilder(new FileReader(filepath)).withSkipLines(1).build()){

            List<String[]> filteredData = csvReader.readAll().stream().filter(row -> row[1].equalsIgnoreCase(key)).collect(Collectors.toList());

            if(filteredData.isEmpty()){
                System.out.println("No record with name '" + key + "' found");
            } else {
                System.out.println("------Record found for '" + key + "'-------");
                filteredData.forEach(row -> System.out.println(row[2] + " " + row[3]));
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        /*
        //Approach 2 -> Nested for loops
        try(CSVReader csvReader = new CSVReaderBuilder(new FileReader(filepath)).withSkipLines(1).build()){
            List<String[]> allData = csvReader.readAll();

            boolean recordPresent = false;
            List<String[]> filteredData = new ArrayList<>();
            for(String[] row: allData){
                boolean individualRecordFound = false;
                    if(row[1].equalsIgnoreCase(key)){
                        individualRecordFound = true;
                        if(!recordPresent){
                            System.out.println("------Record found for '" + key + "------");
                        }
                        recordPresent = true;
                        System.out.print(row[2] + " " + row[3]);
                }
                if(individualRecordFound) System.out.println();
            }

            if(!recordPresent) System.out.println("No record with name '" + key + "' found");
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

    }

    public static void main(String[] args) {

        String filepath = "src/main/java/com/io_programming/IntermediateProblems/SearchRecords/employees.csv";
            String key = "Mikey";
            searchRecord(filepath, key);
    }
}
