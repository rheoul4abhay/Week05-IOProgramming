package com.io_programming.PracticeProblems.ConvertJavaObjectToJSON;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JavaObjectIntoJSON {
    public static void main(String[] args) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();

            Car car = new Car("Cybertruck", "Tesla Motors", false, 100000.0);
            String jsonString = objectMapper.writeValueAsString(car);
            System.out.println(jsonString);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
