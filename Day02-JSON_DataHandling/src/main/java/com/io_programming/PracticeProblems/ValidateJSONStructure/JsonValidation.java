package com.io_programming.PracticeProblems.ValidateJSONStructure;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import java.io.File;
import java.io.IOException;

public class JsonValidation {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File("src/main/java/com/io_programming/PracticeProblems/ValidateJSONStructure/people.json");
        File schemaFile = new File("src/main/java/com/io_programming/PracticeProblems/ValidateJSONStructure/schema.json");

        try {
            JsonNode jsonNode = objectMapper.readTree(jsonFile);
            JsonNode schemaNode = objectMapper.readTree(schemaFile);

            JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            JsonSchema jsonSchema = factory.getJsonSchema(schemaNode);

            ProcessingReport report = jsonSchema.validate(jsonNode);

            if (report.isSuccess()) {
                System.out.println("SON is valid!");
            } else {
                System.out.println("JSON is INVALID. Errors:");
                report.forEach(msg -> System.out.println(msg));
            }

        } catch (IOException | ProcessingException e) {
            e.printStackTrace();
        }
    }
}
