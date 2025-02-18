package com.io_programming.BasicProblems.WriteToCSV;

import com.opencsv.CSVWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteDataToCSV {
    public static void main(String[] args) {

        String destination_path = "src/main/java/com/io_programming/BasicProblems/WriteToCSV/employees.csv";

        //Using BufferedWriter to write into destination
        /*
        try(BufferedWriter bfw = new BufferedWriter(new FileWriter(destination_path))){
            bfw.write("ID, Name, Department, Salary\n");
            bfw.write("101, Abhay, Sales, 500.0\n");
            bfw.write("102, Samay, Accounts, 500.0\n");
            bfw.write("103, Reyna, Management, 400.0\n");
            bfw.write("104, Mikey, Consultancy, 350.0\n");
            System.out.println("CSV file written successfully!");
        } catch (IOException e){
            e.printStackTrace();
        }
        */

        //Write Using openCSV
        try(CSVWriter csvWriter = new CSVWriter(new FileWriter(destination_path))){
            String[] header = {"ID", "Name", "Department", "Salary"};
            String[] emp1 = {"101", "Abhay", "Sales", "2500.0"};
            String[] emp2 = {"102", "Samay", "Accounts", "1500.0"};
            String[] emp3 = {"103", "Reyna", "Management", "3500.0"};
            String[] emp4 = {"104", "Mikey", "Consultancy", "500.0"};

            csvWriter.writeNext(header);
            csvWriter.writeNext(emp1);
            csvWriter.writeNext(emp2);
            csvWriter.writeNext(emp3);
            csvWriter.writeNext(emp4);
            System.out.println("CSV file written successfully.");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
