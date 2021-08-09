package spring.ru.otus.library.service;

import spring.ru.otus.library.dto.BookDto;

import java.util.List;
import java.util.UUID;

public interface BookService {

    String getAllBooksToString();

    String getBookById();

    String insertBook();

    void deleteBook();

    String getAllCommentsByBook();

    void updateNameById();

    List<BookDto> getAllBooks();

    BookDto getBookById(UUID id);

    BookDto insertBook(BookDto bookDto);

    void deleteBookById(UUID id);
}
