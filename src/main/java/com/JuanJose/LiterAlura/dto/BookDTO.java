package com.JuanJose.LiterAlura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
        String title,
        List<AuthorDTO> authors,
        @JsonAlias("bookshelves")List<String> bookShelves,
        List<String> languages,
        Boolean copyright,
        int download_count
        ) {
}
