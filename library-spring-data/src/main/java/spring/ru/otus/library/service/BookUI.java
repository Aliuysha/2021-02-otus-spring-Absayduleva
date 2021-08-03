package spring.ru.otus.library.service;

import spring.ru.otus.library.domain.Author;
import spring.ru.otus.library.domain.Genre;

import java.util.List;

public interface BookUI {
    String getBookName();

    String getBookId();

    String getGenreId(List<Genre> genres);

    String getAuthorId(List<Author> authors);

    String getComment();

}