package com.JuanJose.LiterAlura.model;

import com.JuanJose.LiterAlura.dto.BookDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "books", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToMany(mappedBy = "books", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Author> authors;
    private List<String> bookShelves;
    private List<String> languages;
    private Boolean copyright;
    private int download_count;
    @Version
    private Long version;

    //Constructors
    public Book() {
    }

    public Book(BookDTO bookDTO) {
        this.title = bookDTO.title();
        this.authors = bookDTO.authors();
        this.bookShelves = bookDTO.bookShelves();
        this.languages = bookDTO.languages();
        this.copyright = bookDTO.copyright();
        this.download_count = bookDTO.download_count();
    }

    //Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<String> getBookShelves() {
        return bookShelves;
    }

    public void setBookShelves(List<String> bookShelves) {
        this.bookShelves = bookShelves;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Boolean getCopyright() {
        return copyright;
    }

    public void setCopyright(Boolean copyright) {
        this.copyright = copyright;
    }

    public int getDownload_count() {
        return download_count;
    }

    public void setDownload_count(int download_count) {
        this.download_count = download_count;
    }

    // To String
    @Override
    public String toString() {
        return "---------Book------------------------- \n" +
                "Title: " + title + "\n" +
                "Authors: " + (authors != null ? authors.stream().map(Author::getName).collect(Collectors.joining("; ")) : "No authors") + "\n" +
                "Languages: " + (languages != null ? String.join(", ", languages) : "No languages") + "\n" +
                "Download Count: " + download_count+ "\n" +
                "-------------------------------------";
    }
}
