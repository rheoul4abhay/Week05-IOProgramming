package com.io_programming.RealWorldPracticalProblems.GenerateJSONReport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DatabaseToJson {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/employee_db";
        String user = "root";
        String password = "root@123";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM employees")) {

            JSONArray employeeArray = new JSONArray();

            while (rs.next()) {
                JSONObject employeeObject = new JSONObject();
                employeeObject.put("employee_id", rs.getInt("employee_id"));
                employeeObject.put("name", rs.getString("name"));
                employeeObject.put("email", rs.getString("email"));
                employeeObject.put("designation", rs.getString("designation"));
                employeeObject.put("salary", rs.getDouble("salary"));
                employeeObject.put("department", rs.getString("department"));

                employeeArray.add(employeeObject);
            }

            // Print the Employee JSON Report
            System.out.println(employeeArray.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
