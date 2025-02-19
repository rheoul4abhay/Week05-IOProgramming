package com.io_programming.RealWorldPracticalProblems.AgeFilter;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FilterJsonData {
    public static void main(String[] args) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray users = (JSONArray) parser.parse(new FileReader("src/main/java/com/io_programming/RealWorldPracticalProblems/AgeFilter/users.json"));

            for (Object obj : users) {
                JSONObject user = (JSONObject) obj;
                long age = (long) user.get("age");
                if (age > 25) {
                    System.out.println(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

