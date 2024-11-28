package com.JuanJose.LiterAlura.service;

import com.JuanJose.LiterAlura.client.ExternalApiClient;
import com.JuanJose.LiterAlura.dto.BookDTO;
import com.JuanJose.LiterAlura.exception.ApiRequestException;
import com.JuanJose.LiterAlura.exception.BookNotFoundException;
import com.JuanJose.LiterAlura.model.Book;
import com.JuanJose.LiterAlura.model.BookData;
import com.JuanJose.LiterAlura.repository.BookRepository;
import com.JuanJose.LiterAlura.util.JsonDataConverter;
import com.JuanJose.LiterAlura.util.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Service
public class BookService {
    @Autowired
    BookRepository repository;
    private static final String BASE_URL = "https://gutendex.com/books/";
    private static final JsonDataConverter jdc = new JsonDataConverter();
    private static final ExternalApiClient eac = new ExternalApiClient();

    public BookDTO getDataBook(String bookName) throws IOException {
        String encodedName = URLEncoder.encode(bookName, StandardCharsets.UTF_8);
        String url = BASE_URL + "?search=" + encodedName;
        UrlUtils.validateUrl(url);
        try {
            String response = eac.getDataFromApi(url);
            BookData bookData = jdc.deserialize(response, BookData.class);
            return bookData.books().stream().findFirst()
                    .orElseThrow(() -> new BookNotFoundException("Book not found: " + bookName));
        } catch (IOException e) {
            throw new ApiRequestException("Error fetching book data from API", e);
        }

    }

    public void searchWebBook(String BookName) throws IOException {
        BookDTO data = getDataBook(BookName);
        saveToDataBase(data);
        System.out.println(data);
    }

    public void saveToDataBase(BookDTO bookDTO) {
        Book book = new Book(bookDTO);
        repository.save(book);
        System.out.println("Book save in database");
    }

}
