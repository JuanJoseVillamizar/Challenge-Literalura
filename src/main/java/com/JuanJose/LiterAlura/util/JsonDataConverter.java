package com.JuanJose.LiterAlura.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataConverter implements iDataConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T deserialize(String json, Class<T> tClass) {
        JsonUtils.validateJson(json);
        try {
            return objectMapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new DataConversionException("Failed to deserialize JSON: " + e.getMessage(), e);
        }
    }
}
