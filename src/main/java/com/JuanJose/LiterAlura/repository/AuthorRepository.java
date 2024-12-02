package com.JuanJose.LiterAlura.repository;

import com.JuanJose.LiterAlura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    //Find Author by name (case-insensitive)
    Optional<Author> findAuthorByNameContainingIgnoreCase(String name);

    // Find Authors by name fragment (case-insensitive)
    List<Author> findListAuthorsByNameContainingIgnoreCase(String name);

    // Find authors who wre alive in a given year
    @Query(value = "SELECT a FROM Author a WHERE a.birthYear < :year AND a.deathYear > :year")
    List<Author> findAuthorsAliveInYear(int year);
}
