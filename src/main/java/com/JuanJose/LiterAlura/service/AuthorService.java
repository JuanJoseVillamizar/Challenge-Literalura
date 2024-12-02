package com.JuanJose.LiterAlura.service;

import com.JuanJose.LiterAlura.model.Author;
import com.JuanJose.LiterAlura.model.Book;
import com.JuanJose.LiterAlura.repository.AuthorRepository;
import com.JuanJose.LiterAlura.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public void findAuthorsByName(String name) {
        List<Author> foundAuthors = authorRepository.findListAuthorsByNameContainingIgnoreCase(name);
        if (foundAuthors.isEmpty()) {
            logger.info("Author not found");
        } else {
            logger.info("Found authors: {}",foundAuthors);

        }
    }

    @Transactional
    public void associateBookWithAuthor(Book book) {
        for (Author author : book.getAuthors()) {
            Optional<Author> existingAuthor = authorRepository.findAuthorByNameContainingIgnoreCase(author.getName());
            if (existingAuthor.isPresent()) {
                Author authorFound = existingAuthor.get();
                if (!authorFound.getBooks().contains(book)) {
                    book.setAuthors(null);
                    bookRepository.save(book);
                    authorFound.getBooks().add(book);
                    authorRepository.save(authorFound);
                    logger.info("Author exists, associating with book...");
                }
            } else {
                author.getBooks().add(book);
                authorRepository.save(author);
                System.out.println("New author added, associating with book...");
            }
        }
    }

    public List<Author> findLivingAuthorsByGivenYear(int year) {
        return authorRepository.findAuthorsAliveInYear(year);
    }
}
