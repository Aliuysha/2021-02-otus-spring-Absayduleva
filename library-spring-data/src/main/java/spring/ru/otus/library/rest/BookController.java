package spring.ru.otus.library.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.ru.otus.library.dto.BookDto;
import spring.ru.otus.library.service.BookService;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/books")
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }
}
