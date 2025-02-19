package com.io_programming.RealWorldPracticalProblems.EmailValidator;

import java.io.FileReader;
import java.util.regex.Pattern;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ValidateEmailJson {
    public static void main(String[] args) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(
                    "src/main/java/com/io_programming/RealWorldPracticalProblems/EmailValidator/data.json"
            ));

            String email = (String) jsonObject.get("email");

            if (isValidEmail(email)) {
                System.out.println("Valid Email: " + email);
            } else {
                System.out.println("Invalid Email: " + email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Email Validation Function
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }
}
