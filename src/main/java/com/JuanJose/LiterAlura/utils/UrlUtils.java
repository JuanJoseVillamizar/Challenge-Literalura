package com.JuanJose.LiterAlura.utils;

import java.net.URI;
import java.net.URISyntaxException;

public class UrlUtils {
    private UrlUtils() {
        // Constructor privado para evitar instanciación
    }
    public static void validateUrl(String url) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("URL cannot be null or blank");
        }
        try {
            new URI(url); // Intenta crear un URI para validar su formato
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid URL format: " + url, e);
        }
    }
}
