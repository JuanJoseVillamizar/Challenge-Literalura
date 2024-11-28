package com.JuanJose.LiterAlura.app;

import com.JuanJose.LiterAlura.model.Book;
import com.JuanJose.LiterAlura.model.BookData;
import com.JuanJose.LiterAlura.repository.BookRepository;
import com.JuanJose.LiterAlura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Component
public class App {
    private final BookService boookService;
    private final BookRepository repository;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String BASE_URL = "https://gutendex.com/books/";

    public App(BookRepository repository, BookService boookService) {
        this.repository = repository;
        this.boookService = boookService;
    }

    public void showMenu() throws IOException {
        var menu = """
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    
                    0 - Salir
                    """;
        while (true) {
            System.out.println(menu);
            int option = SCANNER.nextInt();
            SCANNER.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Please enter the name of the book:");
                    String bookName = SCANNER.nextLine();
                    boookService.getDataBook(bookName);
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    return;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
}
