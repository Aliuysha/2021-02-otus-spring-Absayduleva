package ru.otus.library.dao;

import ru.otus.library.domain.Genre;

import java.util.List;

public interface GenreDao {
    void insert(Genre book);

    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);
}
