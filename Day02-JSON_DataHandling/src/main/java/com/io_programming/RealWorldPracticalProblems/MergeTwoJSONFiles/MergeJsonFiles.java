package com.io_programming.RealWorldPracticalProblems.MergeTwoJSONFiles;

import java.io.FileReader;
import java.io.FileWriter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MergeJsonFiles {
    public static void main(String[] args) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject json1 = (JSONObject) parser.parse(new FileReader("src/main/java/com/io_programming/RealWorldPracticalProblems/MergeTwoJSONFiles/file1.json"));
            JSONObject json2 = (JSONObject) parser.parse(new FileReader("src/main/java/com/io_programming/RealWorldPracticalProblems/MergeTwoJSONFiles/file2.json"));

            json1.putAll(json2);

            try (FileWriter file = new FileWriter("src/main/java/com/io_programming/RealWorldPracticalProblems/MergeTwoJSONFiles/merged.json")) {
                file.write(json1.toJSONString());
                file.flush();
            }

            System.out.println("Merged JSON: " + json1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
