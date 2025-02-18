package com.io_programming.AdvancedProblems.CSV_Data_Into_JavaObjects;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CovertCSV_ToObjects {

    public static List<Student> readCSV(String filepath){
        List<Student> studentsList = new ArrayList<>();

        try(CSVReader csvReader = new CSVReaderBuilder(new FileReader(filepath)).withSkipLines(1).build()){
            List<String[]> allData = csvReader.readAll();

            for(String[] row: allData){
                int id = Integer.parseInt(row[0]);
                String name = row[1];
                int age = Integer.parseInt(row[2]);
                double marks = Double.parseDouble(row[3]);

                studentsList.add(new Student(id, name, age, marks));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return studentsList;
    }

    public static void main(String[] args){
        String filepath = "src/main/java/com/io_programming/AdvancedProblems/CSV_Data_Into_JavaObjects/students.csv";
        List<Student> students = readCSV(filepath);

        students.forEach(System.out::println);
    }
}
