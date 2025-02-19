package com.io_programming.PracticeProblems.MergeJSONObjects;

import org.json.*;
public class MergeJSON {

    public static JSONObject mergeJSON(JSONObject json1, JSONObject json2){
        JSONObject mergedJSON = new JSONObject(json1.toString());
        for(String key: JSONObject.getNames(json2)){
            mergedJSON.put(key, json2.get(key));
        }
        return mergedJSON;
    }

    public static void main(String[] args) {
        JSONObject json1 = new JSONObject();
        json1.put("name", "Abhay");
        json1.put("age", 22);

        JSONObject json2 = new JSONObject();
        json2.put("email", "example@123");
        json2.put("city", "New York");
        json1.put("age", 23); //key value will get updated

        JSONObject mergedJSON = mergeJSON(json1, json2);
        System.out.println(mergedJSON.toString());
    }
}
