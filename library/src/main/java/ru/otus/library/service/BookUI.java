package ru.otus.library.service;

import ru.otus.library.domain.Author;
import ru.otus.library.domain.Genre;

public interface BookUI {
    String getBookName();

    long getBookId();

    long getGenreId(Iterable<Genre> genres);

    long getAuthorId(Iterable<Author> authors);

    String getComment();

}