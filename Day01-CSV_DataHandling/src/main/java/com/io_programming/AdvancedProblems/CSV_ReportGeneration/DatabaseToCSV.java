package com.io_programming.AdvancedProblems.CSV_ReportGeneration;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import com.opencsv.CSVWriter;

public class DatabaseToCSV {

    public static void generateCSVReport(String dbUrl, String username, String password, String outputCsvFile) {
        String query = "SELECT employee_id, name, department, salary FROM employees";

        try (Connection connection = DriverManager.getConnection(dbUrl, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
             CSVWriter csvWriter = new CSVWriter(new FileWriter(outputCsvFile))) {

            // Writing the header to the CSV
            csvWriter.writeNext(new String[]{"Employee ID", "Name", "Department", "Salary"});

            // Writing data rows
            while (resultSet.next()) {
                String[] record = {
                        String.valueOf(resultSet.getInt("employee_id")),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        String.valueOf(resultSet.getDouble("salary"))
                };
                csvWriter.writeNext(record);
            }
            System.out.println("CSV Report generated successfully!");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://localhost:3306/employee_db";
        String username = "root";
        String password = "root@123";
        String outputCsvFile = "src/main/java/com/io_programming/AdvancedProblems/CSV_ReportGeneration/employees_report.csv";

        generateCSVReport(dbUrl, username, password, outputCsvFile);
    }
}

