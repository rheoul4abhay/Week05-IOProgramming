package com.io_programming.PracticeProblems.JavaObjectsToJSONArray;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

class Person{
    private String name;
    private int  age;

    public  Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    //getters
    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }
}
public class JSONObjectsToArray {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Abhay", 23),
                new Person("Rohit", 32),
                new Person("Girish", 54),
                new Person("Ramesh", 32)
        );

        ObjectMapper objectMapper = new ObjectMapper();
        try{
            String json = objectMapper.writeValueAsString(people);
            System.out.println(json);
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }
}
