package com.io_programming.RealWorldPracticalProblems.ConvertJSONToXML;

import org.json.JSONObject;
import org.json.XML;

public class JsonToXmlConverter {
    public static void main(String[] args) {
        String jsonString = "{\"name\": \"Abhay\", \"age\": 23}";
        JSONObject json = new JSONObject(jsonString);
        String xml = XML.toString(json);

        System.out.println(xml);
    }
}

