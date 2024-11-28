package com.JuanJose.LiterAlura.util;

public class TextUtils {
    private TextUtils(){

    }
    public static void validateText (String text){
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Text cannot be null or empty");
        }
    }
}
