package com.JuanJose.LiterAlura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "authors", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer birth_year;
    private Integer death_year;
    private String name;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name= "book_id")
    )
    private List<Book> books;
    @Version  // Campo para la gestión de versiones en optimista
    private Long version;

    public Author(){

    }
    public Author(Integer birth_year, Integer death_year, String name, List<Book> books) {
        this.birth_year = birth_year;
        this.death_year = death_year;
        this.name = name;
        this.books = books;
    }

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

    @Override
    public String toString() {
        return "---------Author------------------------- \n" +
                "birth_year: " + birth_year +  "\n" +
                "death_year: " + death_year +  "\n" +
                "name:" + name  + "\n" +
                "books: " + (books != null ? books.stream().map(Book::getTitle).collect(Collectors.joining(", ")) : "No books" ) +  "\n" +
                "----------------------------------------";
    }
}
