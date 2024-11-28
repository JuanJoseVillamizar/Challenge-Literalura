package com.JuanJose.LiterAlura.util;

public interface iDataConverter {
    <T> T deserialize (String json, Class<T> tClass);
}
