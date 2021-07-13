package ru.otus.library.service;

import ru.otus.library.domain.Author;
import ru.otus.library.domain.Genre;

import java.util.List;

public interface BookUI {
    String getBookName();

    long getBookId();

    long getGenreId(List<Genre> genres);

    long getAuthorId(List<Author> authors);

    String getComment();

}