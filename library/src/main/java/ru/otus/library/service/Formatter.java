package ru.otus.library.service;

import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;
import ru.otus.library.domain.Genre;

import java.util.List;

public class Formatter {

    private Formatter() {
    }

    public static String getBookNameFormat(Book book) {
        return book.getId() + " " +
                book.getName() +
                "\n" +
                "авторы: " +
                getAuthorNameFormat(book.getAuthors()) +
                "\n" +
                "жанры: " +
                getGenreNameFormat(book.getGenres()) +
                "\n" +
                "комментарии: " +
                getCommentNameFormat(book.getComments()) +
                "\n";
    }

    public static String getAuthorNameFormat(Iterable<Author> authors) {
        StringBuilder str = new StringBuilder();
        for (Author author : authors) {
            str.append(author.getId()).append(" ").append(author.getName()).append(" \n");
        }
        return str.toString();
    }

    public static String getGenreNameFormat(Iterable<Genre> genres) {
        StringBuilder str = new StringBuilder();
        for (Genre genre : genres) {
            str.append(genre.getId()).append(" ").append(genre.getName()).append(" \n");
        }
        return str.toString();
    }

    public static String getCommentNameFormat(Iterable<Comment> comments) {
        StringBuilder str = new StringBuilder();
        for (Comment comment : comments) {
            str.append(comment.getId()).append(" ").append(comment.getText()).append(" \n");
        }
        return str.toString();
    }
}
