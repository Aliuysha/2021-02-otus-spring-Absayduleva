package spring.ru.otus.library.web.service;

import spring.ru.otus.library.dto.BookDto;

import java.util.List;
import java.util.UUID;

public interface BookWebService {

    List<BookDto> getAllBooks();

    BookDto getBookById(UUID id);

    BookDto insertBook(BookDto bookDto);

    void deleteBookById(UUID id);
}
