package com.io_programming.RealWorldPracticalProblems.ConvertCSVToJSON;

import java.io.FileReader;
import com.opencsv.CSVReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CsvToJson {
    public static void main(String[] args) {
        try (CSVReader reader = new CSVReader(new FileReader("src/main/java/com/io_programming/RealWorldPracticalProblems/ConvertCSVToJSON/data.csv"))) {
            String[] headers = reader.readNext();
            JSONArray jsonArray = new JSONArray();

            String[] line;
            while ((line = reader.readNext()) != null) {
                JSONObject jsonObject = new JSONObject();
                for (int i = 0; i < headers.length; i++) {
                    jsonObject.put(headers[i], line[i]);
                }
                jsonArray.add(jsonObject);
            }

            System.out.println(jsonArray.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

