package ru.otus.library.service;

public interface BookService {

    String getAllBooks();

    String getBookById();

    String insertBook();

    void deleteBook();

    String getAllCommentsByBook();

    void updateNameById();
}
