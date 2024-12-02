package com.JuanJose.LiterAlura.app;

import com.JuanJose.LiterAlura.model.Author;
import com.JuanJose.LiterAlura.model.Book;
import com.JuanJose.LiterAlura.repository.AuthorRepository;
import com.JuanJose.LiterAlura.repository.BookRepository;
import com.JuanJose.LiterAlura.service.AuthorService;
import com.JuanJose.LiterAlura.service.BookService;
import com.JuanJose.LiterAlura.util.TextUtils;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Scanner;

@Component
public class App {
    private final BookService bookService;
    private final AuthorService authorService;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private static final Scanner SCANNER = new Scanner(System.in);

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
                7 - Find saved books by title
               
                0 - Exit
                ============================================================
                """;
        boolean isRunning = true;
        while (isRunning) {
            System.out.println(menu);
            System.out.println("Enter your choice: ");
            try {
                int option = Integer.parseInt(SCANNER.nextLine());
                isRunning = handleMenuOption(option);
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
                case 7 -> findBooksByTitle();
                case 0 -> {
                    System.out.println("Closing the application...");
                    return false;
                }
                default -> System.out.println("Invalid option. Please try again");
            }
        return true;
    }
    private void findBookByTitle() {
        System.out.println("Please enter the title of the book:");
        String bookName = SCANNER.nextLine();
        TextUtils.validateText(bookName);
        bookService.searchAndSaveBook(bookName);
    }

    private void listRegisteredBooks() {
        List<Book> books = bookService.findAllBooks();
        if (books.isEmpty()){
            System.out.println("Not registered books found.");
        }else {
            System.out.println(books);
        }

    }

    private void listRegisteredAuthors() {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            System.out.println("No authors are registered.");
        } else {
            System.out.println(authors);
        }
    }

    private void listLivingAuthorsByYear() {
        System.out.println("Please enter the year");
        int year = getIntInput();
        List<Author>authors = authorService.findLivingAuthorsByGivenYear(year);
        if (authors.isEmpty()){
            System.out.println("No authors were alive in the year: " + year + ".");
        }else {
            System.out.println(authors);
        }

    }

    private void listBooksByLanguage() {
        System.out.println("""
                Please select which language you want to search for books:
                -en : English
                -es : Spanish
                -fr : French
                -pt : Portuguese
                """);
        System.out.println("Select one: ");
        String language = SCANNER.nextLine();
        List<String> validLanguages= List.of("es", "en", "fr", "pt");
        if (!validLanguages.contains(language)){
            System.out.println("Invalid option");
            return;
        }
        List<Book> booksByLanguage = bookService.findByLanguage(language);
        if (booksByLanguage.isEmpty()){
            System.out.println("No books for the select language.");
        }else {
            System.out.println(booksByLanguage);
        }
    }

    private void findAuthorByName() {
        System.out.println("Please enter the name of the Author");
        String name = SCANNER.nextLine();
        TextUtils.validateText(name);
        authorService.findAuthorByName(name);
    }
    private void findBooksByTitle(){
        System.out.println("Please enter the title of the book");
        String bookName = SCANNER.nextLine();
        TextUtils.validateText(bookName);
        bookService.findBookByName(bookName);
    }

    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(SCANNER.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }


}

