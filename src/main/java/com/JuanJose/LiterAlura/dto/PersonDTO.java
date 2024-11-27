package com.JuanJose.LiterAlura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PersonDTO(
        Integer birth_year,
        Integer death_year,
        String name
) {
}
