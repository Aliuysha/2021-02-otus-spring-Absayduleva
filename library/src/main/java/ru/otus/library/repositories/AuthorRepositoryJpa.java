package ru.otus.library.repositories;

import ru.otus.library.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepositoryJpa {
    Author save(Author author);

    Optional<Author> findById(long id);

    List<Author> findAll();

    List<Author> findByName(String name);

    void updateNameById(long id, String name);

    void deleteById(long id);
}
