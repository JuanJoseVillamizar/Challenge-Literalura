package com.JuanJose.LiterAlura.app;

import com.JuanJose.LiterAlura.model.Author;
import com.JuanJose.LiterAlura.model.Book;
import com.JuanJose.LiterAlura.repository.AuthorRepository;
import com.JuanJose.LiterAlura.repository.BookRepository;
import com.JuanJose.LiterAlura.service.AuthorService;
import com.JuanJose.LiterAlura.service.BookService;
import com.JuanJose.LiterAlura.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private static final Scanner SCANNER = new Scanner(System.in);

    private final BookService bookService;
    private final AuthorService authorService;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    public App(BookRepository bookRepository, BookService bookService, AuthorRepository authorRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
        this.authorService = authorService;
        this.authorRepository = authorRepository;
    }

    public void showMenu() {
        var menu = """
                ================= LiterAlura ===============================
                1 - Find book by title
                2 - List registered books
                3 - List registered authors
                4 - List living authors for a given given year
                5 - List books by language
                6 - Find author by name
                7 - Find the top ten most download books
                8 - Find saved books by title
               
                0 - Exit
                ============================================================
                """;
        while (true) {
            System.out.println(menu);
            System.out.println("Enter your choice: ");
            try {
                int option = Integer.parseInt(SCANNER.nextLine());
                if (!handleMenuOption(option)) {
                    logger.info("Closing the application...");
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private Boolean handleMenuOption(int option) {
            switch (option) {
                case 1 -> findBookByTitle();
                case 2 -> listRegisteredBooks();
                case 3 -> listRegisteredAuthors();
                case 4 -> listLivingAuthorsByYear();
                case 5 -> listBooksByLanguage();
                case 6 -> findAuthorByName();
                case 7 -> findTopTenBooksByDownloads();
                case 8 -> findBooksByTitle();
                case 0 -> {
                    return false;
                }
                default -> logger.warn("Invalid option. Please try again.");
            }
        return true;
    }
    private void findBookByTitle() {
        String bookName = getStringInput("Please enter the title of the book:");
        TextUtils.validateText(bookName);
        bookService.searchAndSaveBook(bookName);
    }

    private void listRegisteredBooks() {
        List<Book> books = bookService.findAllBooks();
        if (books.isEmpty()){
            logger.info("Not registered books found.");
        }else {
            logger.info("Books found: {}", books);
        }

    }

    private void listRegisteredAuthors() {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            logger.info("No authors are registered.");
        } else {
            logger.info("Authors found: {}", authors);
        }
    }

    private void listLivingAuthorsByYear() {
        int year = getIntInput("Please enter the year:");
        List<Author>authors = authorService.findLivingAuthorsByGivenYear(year);
        if (authors.isEmpty()){
            logger.info("No authors were alive in the year: {}.", year);
        }else {
            logger.info("Authors alive in the yerar {}: {}.", year, authors);
        }

    }

    private void listBooksByLanguage() {
        String language = getStringInput("""
                Please select the language:
                - en : English
                - es : Spanish
                - fr : French
                - pt : Portuguese
                """);
        List<String> validLanguages= List.of("es", "en", "fr", "pt");
        if (!validLanguages.contains(language)) {
            logger.error("Invalid language option. Please choose a valid language.");
            return;
        }
        List<Book> booksByLanguage = bookService.findByLanguage(language);
        if (booksByLanguage.isEmpty()){
            logger.info("No books found for the selected language.");
        }else {
            logger.info("Books found in {}: {}", language, booksByLanguage);
        }
    }

    private void findAuthorByName() {
        String name = getStringInput("Please enter the name of the Author:");
        TextUtils.validateText(name);
        authorService.findAuthorsByName(name);
    }
    private void findTopTenBooksByDownloads(){
        List<Book> books = bookService.findTopTenBooksByDownloads();
        if (books.isEmpty()){
            logger.info("Not registered books found.");
        }else {
            logger.info("The top ten most download books: {}", books);
        }
    }
    private void findBooksByTitle(){
        String bookName = getStringInput("Please enter the title of the book");
        TextUtils.validateText(bookName);
        bookService.findBookByName(bookName);
    }

    private String getStringInput(String prompt) {
        System.out.println(prompt);
        return SCANNER.nextLine();
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(SCANNER.nextLine());
            } catch (NumberFormatException e) {
                logger.error("Invalid input. Please enter a valid number.");
            }
        }
    }


}

