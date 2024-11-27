package com.JuanJose.LiterAlura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer birth_year;
    private Integer death_year;
    private String name;
    @ManyToMany(mappedBy = "authors",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Book> books;

}
