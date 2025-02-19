package com.io_programming.RealWorldPracticalProblems.IPL_And_Censor_Analyzer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import com.opencsv.*;

public class IPLCensorshipProcessor {
    public static void main(String[] args) {
        try {
            // To Process JSON File
            processJsonFile("src/main/java/com/io_programming/RealWorldPracticalProblems/IPL_And_Censor_Analyzer/input_ipl.json", "src/main/java/com/io_programming/RealWorldPracticalProblems/IPL_And_Censor_Analyzer/censored_ipl.json");

            // To Process CSV File
            processCsvFile("src/main/java/com/io_programming/RealWorldPracticalProblems/IPL_And_Censor_Analyzer/input_ipl.csv", "src/main/java/com/io_programming/RealWorldPracticalProblems/IPL_And_Censor_Analyzer/censored_ipl.csv");

            System.out.println("Censorship applied successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processJsonFile(String inputFile, String outputFile) throws Exception {
        JSONParser parser = new JSONParser();
        JSONArray matches = (JSONArray) parser.parse(new FileReader(inputFile));
        JSONArray censoredMatches = new JSONArray();

        for (Object obj : matches) {
            JSONObject match = (JSONObject) obj;
            match.put("team1", censorTeamName((String) match.get("team1")));
            match.put("team2", censorTeamName((String) match.get("team2")));
            match.put("winner", censorTeamName((String) match.get("winner")));
            match.put("player_of_match", "REDACTED");

            JSONObject score = (JSONObject) match.get("score");
            JSONObject censoredScore = new JSONObject();
            for (Object key : score.keySet()) {
                censoredScore.put(censorTeamName((String) key), score.get(key));
            }
            match.put("score", censoredScore);

            censoredMatches.add(match);
        }

        // Write to JSON File
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(censoredMatches.toJSONString());
        }
    }

    // Method to Censor CSV Data
    private static void processCsvFile(String inputFile, String outputFile) throws Exception {
        List<String[]> censoredData = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(inputFile))) {
            String[] headers = reader.readNext();
            censoredData.add(headers);

            String[] row;
            while ((row = reader.readNext()) != null) {
                row[1] = censorTeamName(row[1]);  // team1
                row[2] = censorTeamName(row[2]);  // team2
                row[5] = censorTeamName(row[5]);  // winner
                row[6] = "REDACTED";              // player_of_match
                censoredData.add(row);
            }
        }

        // Write to CSV File
        try (CSVWriter writer = new CSVWriter(new FileWriter(outputFile))) {
            writer.writeAll(censoredData);
        }
    }

    private static String censorTeamName(String team) {
        if (team.contains(" ")) {
            return team.substring(0, team.indexOf(" ")) + " ***";
        }
        return team;  // No change if no space found
    }
}
