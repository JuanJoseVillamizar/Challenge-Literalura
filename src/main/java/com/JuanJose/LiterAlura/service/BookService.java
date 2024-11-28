package com.JuanJose.LiterAlura.service;

import com.JuanJose.LiterAlura.client.ExternalApiClient;
import com.JuanJose.LiterAlura.model.Book;
import com.JuanJose.LiterAlura.model.BookData;
import com.JuanJose.LiterAlura.repository.BookRepository;
import com.JuanJose.LiterAlura.util.JsonDataConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository repository;
    private static final String BASE_URL = "https://gutendex.com/books/";
    private static final JsonDataConverter jdc = new JsonDataConverter();
    private static final ExternalApiClient eac = new ExternalApiClient();

    public Book getDataBook(String name) throws IOException {
        if (repository == null) {
            System.out.println("El repositorio es null.");
        }
        String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);
        String url = BASE_URL + "?search=" + encodedName;
        System.out.println(url);
        String response = eac.getDataFromApi(url);
        BookData bookData = jdc.deserialize(response, BookData.class);
        Optional<Book> book = bookData.books().stream().findFirst();
        if (book.isPresent()) {
            Book b = book.get();
            System.out.println(b);
            repository.save(b);
            return b;
        } else {
            System.out.println("Not found book");
            return null;
        }
    }

}
