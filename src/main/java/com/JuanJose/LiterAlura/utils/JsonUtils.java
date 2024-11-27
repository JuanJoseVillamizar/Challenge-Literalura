package com.JuanJose.LiterAlura.utils;

public class JsonUtils {
    private JsonUtils(){}
    public static void validateJson(String json){
        if (json == null || json.trim().isEmpty()) {
            throw new IllegalArgumentException("Input JSON string cannot be null or empty");
        }
    }
}
