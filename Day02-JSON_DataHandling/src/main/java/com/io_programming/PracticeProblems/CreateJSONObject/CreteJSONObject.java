package com.io_programming.PracticeProblems.CreateJSONObject;

import org.json.*;

public class CreteJSONObject {
    public static void main(String[] args) {
        JSONArray subjects = new JSONArray();
        subjects.put("Mathematics");
        subjects.put("Science");
        subjects.put("English");
        subjects.put("Physics");
        subjects.put("Chemistry");

        JSONObject student = new JSONObject();
        student.put("subjects", subjects);
        student.put("name", "Abhay");
        student.put("age", 22);

        System.out.println(student.toString(1));
    }
}
