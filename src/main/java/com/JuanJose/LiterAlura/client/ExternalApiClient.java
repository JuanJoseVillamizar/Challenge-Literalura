package com.JuanJose.LiterAlura.client;

import com.JuanJose.LiterAlura.util.UrlUtils;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.ServerErrorException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExternalApiClient {
    // Create a single HTTP client instance for all requests
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    public String getDataFromApi(String url) throws IOException {
        UrlUtils.validateUrl(url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response;
            response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            // Check if the response status code indicates a client error (4xx)
            if (response.statusCode() >= 400 && response.statusCode() < 500) {
                throw new ClientErrorException("Client error: " + response.statusCode(), response.statusCode());
                // Check if the response status code indicates a server error (5xx)
            } else if (response.statusCode() >= 500) {
                throw new ServerErrorException("Server error: " + response.statusCode(), response.statusCode());
            }
            return response.body();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status of the thread
            throw new IOException("Request was interrupted: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new IOException("Failed to fetch data from API: " + e.getMessage(), e);
        }


    }
}
