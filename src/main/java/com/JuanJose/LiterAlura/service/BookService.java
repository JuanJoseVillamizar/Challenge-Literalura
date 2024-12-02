package com.JuanJose.LiterAlura.service;

import com.JuanJose.LiterAlura.client.ExternalApiClient;
import com.JuanJose.LiterAlura.dto.BookDTO;
import com.JuanJose.LiterAlura.exception.ApiRequestException;
import com.JuanJose.LiterAlura.model.Book;
import com.JuanJose.LiterAlura.model.BookData;
import com.JuanJose.LiterAlura.repository.BookRepository;
import com.JuanJose.LiterAlura.util.JsonDataConverter;
import com.JuanJose.LiterAlura.util.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;


@Service
public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository repository;
    private final AuthorService authorService;
    private static final String BASE_URL = "https://gutendex.com/books/";
    private static final JsonDataConverter jsonDataConverter = new JsonDataConverter();
    private static final ExternalApiClient externalApiClient = new ExternalApiClient();

    @Autowired
    public BookService(BookRepository repository, AuthorService authorService) {
        this.repository = repository;
        this.authorService = authorService;
    }

    public Optional<BookDTO> fetchBookData(String bookName) {
        String encodedName = URLEncoder.encode(bookName, StandardCharsets.UTF_8);
        String url = BASE_URL + "?search=" + encodedName;
        UrlUtils.validateUrl(url);
        try {
            String response = externalApiClient.getDataFromApi(url);
            BookData bookData = jsonDataConverter.deserialize(response, BookData.class);
            return bookData.books().stream().findFirst();
        } catch (IOException e) {
            throw new ApiRequestException("Error fetching book data from API. URL: " + url, e);
        }

    }

    public void searchAndSaveBook(String bookName) {
        Optional<BookDTO> data = fetchBookData(bookName);
        if (data.isEmpty()) {
            logger.info("No book found with the title: {}", bookName);
            return;
        }
        Book book = new Book(data.get());
        checkAndSaveBook(book);
    }


    public void checkAndSaveBook(Book book) {
        Optional<Book> existingBook = repository.findByTitleContainingIgnoreCase(book.getTitle());
        if (existingBook.isPresent()) {
            logger.info("The book already exists in the database.");
        } else {
            saveBook(book);
        }
    }

    public void saveBook(Book book) {
        if (book.getAuthors() == null || book.getAuthors().isEmpty()) {
            repository.save(book);
            logger.info("Book successfully saved to the database.");
            findBookByName(book.getTitle());
        }
        authorService.associateBookWithAuthor(book);
        repository.save(book);
        logger.info("Book successfully saved to the database.");
        findBookByName(book.getTitle());

    }

    public List<Book> findAllBooks() {
        return repository.findAll();
    }

    public List<Book> findByLanguage(String language) {
        return repository.findBooksByLanguagesContainingIgnoreCase(language);
    }

    public void findBookByName(String name) {
        List<Book> booksFound = repository.findBooksByTitleContainingIgnoreCase(name);
        if (booksFound.isEmpty()) {
            logger.info("No books found.");
        } else {
            logger.info("Books found: {}", booksFound);

        }
    }

}
