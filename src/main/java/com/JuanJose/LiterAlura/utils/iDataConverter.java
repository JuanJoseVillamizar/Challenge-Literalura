package com.JuanJose.LiterAlura.utils;

public interface iDataConverter {
    <T> T deserialize (String json, Class<T> tClass);
}
