package com.io_programming.AdvancedProblems.ConvertJsonCSV_ViceVersa;

import com.opencsv.CSVWriter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.Iterator;

public class ConvertJsonToCSV {

    // Method to convert JSON to CSV
    public static void convertJSONToCSV(String jsonFilePath, String csvFilePath) {
        try {
            // Read the JSON file
            BufferedReader reader = new BufferedReader(new FileReader(jsonFilePath));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            reader.close();

            // Convert the JSON string to JSONArray
            JSONArray jsonArray = new JSONArray(jsonString.toString());

            // Prepare CSV file writer
            FileWriter fileWriter = new FileWriter(csvFilePath);
            CSVWriter csvWriter = new CSVWriter(fileWriter);

            // Write header
            JSONObject firstObject = jsonArray.getJSONObject(0);
            Iterator<String> keys = firstObject.keys();
            String[] header = new String[firstObject.length()];
            int index = 0;
            while (keys.hasNext()) {
                header[index++] = keys.next();
            }
            csvWriter.writeNext(header);

            // Write records
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject student = jsonArray.getJSONObject(i);
                String[] record = new String[student.length()];
                int j = 0;
                for (String key : student.keySet()) {
                    record[j++] = student.getString(key);
                }
                csvWriter.writeNext(record);
            }
            csvWriter.close();
            System.out.println("CSV file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to convert CSV to JSON
    public static void convertCSVToJSON(String csvFilePath, String jsonFilePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
            String line;
            String[] header = reader.readLine().split(",");

            JSONArray jsonArray = new JSONArray();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                JSONObject jsonObject = new JSONObject();
                for (int i = 0; i < header.length; i++) {
                    jsonObject.put(header[i], data[i]);
                }
                jsonArray.put(jsonObject);
            }
            reader.close();

            // Write the JSON array to the file
            FileWriter fileWriter = new FileWriter(jsonFilePath);
            fileWriter.write(jsonArray.toString(4));  // Pretty print with indentation of 4
            fileWriter.close();
            System.out.println("JSON file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Convert JSON to CSV
        String jsonFilePath = "src/main/java/com/io_programming/AdvancedProblems/ConvertJsonCSV_ViceVersa/students.json";
        String csvFilePath = "src/main/java/com/io_programming/AdvancedProblems/ConvertJsonCSV_ViceVersa/students.csv";
        convertJSONToCSV(jsonFilePath, csvFilePath);

        // Convert CSV to JSON
        String newJsonFilePath = "students_new.json";
        convertCSVToJSON(csvFilePath, newJsonFilePath);
    }
}
