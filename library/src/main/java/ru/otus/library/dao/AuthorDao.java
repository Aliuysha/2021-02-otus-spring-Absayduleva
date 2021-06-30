package ru.otus.library.dao;

import ru.otus.library.domain.Author;

import java.util.List;

public interface AuthorDao {
    void insert(Author book);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);
}
