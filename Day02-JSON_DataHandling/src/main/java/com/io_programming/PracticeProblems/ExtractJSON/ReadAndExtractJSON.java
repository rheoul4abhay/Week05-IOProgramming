package com.io_programming.PracticeProblems.ExtractJSON;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadAndExtractJSON {
    public static void main(String[] args) {
        String filepath = "src/main/java/com/io_programming/PracticeProblems/ExtractJSON/data.json";
        StringBuilder sb = new StringBuilder();

        try(BufferedReader bfr = new BufferedReader(new FileReader(filepath))){
            String line;
            while((line = bfr.readLine()) != null){
                sb.append(line);
            }

            //Parse JSON
            JSONObject jsonObject = new JSONObject(sb.toString());

            //Extract specific fields
            String name = jsonObject.optString("name", "unknown");
            String email = jsonObject.optString("email", "N/A");
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
