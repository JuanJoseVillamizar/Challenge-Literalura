package com.JuanJose.LiterAlura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "authors", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer birth_year;
    private Integer death_year;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name= "book_id")
    )
    private List<Book> books;
    @Version  // Campo para la gestión de versiones en optimista
    private Long version;
    public Integer getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(Integer birth_year) {
        this.birth_year = birth_year;
    }

    public Integer getDeath_year() {
        return death_year;
    }

    public void setDeath_year(Integer death_year) {
        this.death_year = death_year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
