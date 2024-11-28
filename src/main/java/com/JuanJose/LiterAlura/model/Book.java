package com.JuanJose.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "books", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String title;
    @ManyToMany(mappedBy = "books",cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    private List<Author> authors;
    private List<String> bookShelves;
    private List<String> languages;
    private Boolean copyright;
    private int download_count;
    @Version
    private Long version;

    public Book(){}

    public Book(String title, List<Author> authors, List<String> bookShelves, List<String> languages, Boolean copyright, int download_count) {
        this.title = title;
        this.authors = authors;
        this.bookShelves = bookShelves;
        this.languages = languages;
        this.copyright = copyright;
        this.download_count = download_count;
    }

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

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", authors=" + (authors != null ? authors.stream().map(Author::getName).toList() : "[]")+
                ", bookShelves=" + bookShelves +
                ", languages=" + languages +
                ", copyright=" + copyright +
                ", download_count=" + download_count +
                '}';
    }
}
