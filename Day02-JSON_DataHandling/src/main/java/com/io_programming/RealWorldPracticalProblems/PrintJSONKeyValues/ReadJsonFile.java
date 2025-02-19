package com.io_programming.RealWorldPracticalProblems.PrintJSONKeyValues;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadJsonFile {
    public static void main(String[] args) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(
                    "src/main/java/com/io_programming/RealWorldPracticalProblems/PrintJSONKeyValues/data.json"
            ));

            for (Object key : jsonObject.keySet()) {
                System.out.println(key + " : " + jsonObject.get(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
