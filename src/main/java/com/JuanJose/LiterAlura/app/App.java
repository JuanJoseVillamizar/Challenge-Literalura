package com.JuanJose.LiterAlura.app;


import com.JuanJose.LiterAlura.repository.BookRepository;
import com.JuanJose.LiterAlura.service.BookService;
import com.JuanJose.LiterAlura.util.TextUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class App {
    private final BookService bookService;
    private final BookRepository repository;
    private static final Scanner SCANNER = new Scanner(System.in);

    public App(BookRepository repository, BookService bookService) {
        this.repository = repository;
        this.bookService = bookService;
    }

    public void showMenu() {
        var menu = """
                ================= LiterAlura ===============================
                1 - Find book by title
                2 - List registered books
                3 - List registered authors
                4 - List living authors for a given given year
                5 - List books by language
               
                0 - Exit
                ============================================================
                """;
        while (true) {
            System.out.println(menu);
            System.out.println("Enter your choice: ");
            try {
                int option = Integer.parseInt(SCANNER.nextLine());
                handleMenuOption(option);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private void handleMenuOption(int option) {
            switch (option) {
                case 1 -> findBookByTitle();
                case 2 -> listRegisteredBooks();
                case 3 -> listRegisteredAuthors();
                case 4 -> listLivingAuthorsByYear();
                case 5 -> listBooksByLanguage();
                case 0 -> {
                    System.out.println("Closing the application...");
                    return;
                }
            }
    }
    private void findBookByTitle() {
        System.out.println("Please enter the title of the book");
        String bookName = SCANNER.nextLine();
        TextUtils.validateText(bookName);
        try{
            bookService.searchWebBook(bookName);
        } catch (IOException e) {
            System.out.println("An error occurred while fetching the book: " + e.getMessage());
        }
    }
    private void listRegisteredBooks() {
        // Implementación temporal
        System.out.println("Feature not implemented yet.");
    }

    private void listRegisteredAuthors() {
        // Implementación temporal
        System.out.println("Feature not implemented yet.");
    }

    private void listLivingAuthorsByYear() {
        // Implementación temporal
        System.out.println("Feature not implemented yet.");
    }

    private void listBooksByLanguage() {
        // Implementación temporal
        System.out.println("Feature not implemented yet.");
    }
}

