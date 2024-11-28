package com.JuanJose.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(
        int count,
        String next,
        String previous,
        @JsonAlias("results") List<Book> books
) {
}
