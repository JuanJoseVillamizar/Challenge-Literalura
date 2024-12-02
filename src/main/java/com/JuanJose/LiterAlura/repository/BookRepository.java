package com.JuanJose.LiterAlura.repository;

import com.JuanJose.LiterAlura.model.Author;
import com.JuanJose.LiterAlura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    //Find a book bt its title (case-insensitive, sorted by download count)
    @Query(value = "SELECT b FROM Book b WHERE b.title ILIKE %:title% ORDER BY b.download_count DESC LIMIT 1")
    Optional<Book> findByTitleContainingIgnoreCase(String title);

    // Find Authors by name fragment (case-insensitive)
    List<Book>  findBooksByTitleContainingIgnoreCase(String title);

    // Find books by a specific language (case-insensitive, using native query for array fields)
    @Query(value = "SELECT * FROM books b WHERE EXISTS" +
            "(SELECT 1 FROM  unnest(b.languages) as languages " +
            "WHERE languages ILIKE CONCAT('%', :language, '%'))", nativeQuery = true)
    List<Book> findBooksByLanguagesContainingIgnoreCase(String language);
}
