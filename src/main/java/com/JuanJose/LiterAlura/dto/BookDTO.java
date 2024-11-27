package com.JuanJose.LiterAlura.dto;

import com.JuanJose.LiterAlura.model.Person;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
        Long id,
        String title,
        List<String> bookShelves,
        List<String> languages,
        Boolean copyright,
        int download_count
) {
}
