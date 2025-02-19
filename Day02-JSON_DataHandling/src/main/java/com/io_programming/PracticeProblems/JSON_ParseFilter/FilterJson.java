package com.io_programming.PracticeProblems.JSON_ParseFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Person {
    public String name;
    public int age;

    public Person() {} // Default constructor for Jackson

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}

public class FilterJson {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File("src/main/java/com/io_programming/PracticeProblems/JSON_ParseFilter/people.json");

        try {
            // Read JSON file into List<Person>
            List<Person> people = objectMapper.readValue(jsonFile, new TypeReference<List<Person>>() {});

            List<Person> filteredPeople = new ArrayList<>();
            for (Person person : people) {
                if (person.age > 25) {
                    filteredPeople.add(person);
                }
            }

            for (Person person : filteredPeople) {
                System.out.println(person);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

