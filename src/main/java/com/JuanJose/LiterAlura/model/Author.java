package com.JuanJose.LiterAlura.model;

import com.JuanJose.LiterAlura.dto.AuthorDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "authors", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer birthYear;
    private Integer deathYear;
    private String name;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name= "book_id")
    )
    private List<Book> books ;
    @Version  // Campo para la gestión de versiones en optimista
    private Long version;

    public Author(){}

    public Author(AuthorDTO authorDTO, List<Book> books) {
        this.birthYear = (authorDTO.birth_year() != null ? authorDTO.birth_year() : null);
        this.deathYear = (authorDTO.death_year() != null ? authorDTO.death_year() : null);
        this.name = authorDTO.name();
        this.books = books != null ? books : new ArrayList<>();
    }


    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birth_year) {
        this.birthYear = birth_year;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer death_year) {
        this.deathYear = death_year;
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
                "name:" + name  + "\n" +
                "birth_year: " + birthYear + "\n" +
                "death_year: " + deathYear + "\n" +
                "books: " + (books != null ? books.stream().map(Book::getTitle).collect(Collectors.joining(", ")) : "No books" ) +  "\n" +
                " -------------------------------------- ";
    }
}
