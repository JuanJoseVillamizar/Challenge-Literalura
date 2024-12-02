package com.JuanJose.LiterAlura;

import com.JuanJose.LiterAlura.app.App;
import com.JuanJose.LiterAlura.repository.AuthorRepository;
import com.JuanJose.LiterAlura.repository.BookRepository;
import com.JuanJose.LiterAlura.service.AuthorService;
import com.JuanJose.LiterAlura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BookService bookService;
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private AuthorService authorService;
	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
	App app = new App(bookRepository,bookService,authorRepository,authorService);
	app.showMenu();
	}
}
