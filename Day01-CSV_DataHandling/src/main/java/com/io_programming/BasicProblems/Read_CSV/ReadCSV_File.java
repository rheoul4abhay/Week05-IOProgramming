package com.io_programming.BasicProblems.Read_CSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CSVReader;

public class ReadCSV_File {

    public static void main(String[] args) {
        String filepath = "src/main/java/com/io_programming/BasicProblems/Read_CSV/students.csv";
        /*
        try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
            String line;
            while((line = br.readLine()) != null){
                String[] columns = line.split(",");
                System.out.println("ID: " + columns[0] + ", Name: " + columns[1] + ", Age: " + columns[2] + ", Marks: " + columns[3]);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        */

        //Reading csv file using opencsv
        try(CSVReader csvReader = new CSVReader(new FileReader(filepath))){
               String[] rowData;

               while((rowData = csvReader.readNext()) != null){
                   System.out.println("ID: " + rowData[0] + ", Name: " + rowData[1] + ", Age: " + rowData[2] + ", Marks: " + rowData[3]);
               }

        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("General error: " + e.getMessage());
        }
    }
}
