package com.JuanJose.LiterAlura.dto;

import com.JuanJose.LiterAlura.model.Author;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
        @JsonAlias("id")Integer api_id,
        String title,
        List<Author> authors,
        @JsonAlias("bookshelves")List<String> bookShelves,
        List<String> languages,
        Boolean copyright,
        int download_count
        ) {
}
