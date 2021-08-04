package spring.ru.otus.library.web.service;

import org.springframework.stereotype.Service;
import spring.ru.otus.library.domain.Book;
import spring.ru.otus.library.dto.BookDto;
import spring.ru.otus.library.repositories.BookRepository;
import spring.ru.otus.library.service.EntityNotFoundException;
import spring.ru.otus.library.web.service.mapper.BookMapper;

import java.util.List;
import java.util.UUID;

@Service
public class BookWebServiceImpl implements BookWebService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookWebServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookMapper.convert(bookRepository.findAll());
    }

    @Override
    public BookDto getBookById(UUID id) {
        var book = bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return bookMapper.convert(book);
    }

    @Override
    public BookDto insertBook(BookDto bookDto) {
        Book save = bookRepository.save(bookMapper.convert(bookDto));
        return bookMapper.convert(save);
    }

    @Override
    public void deleteBookById(UUID id) {
        bookRepository.deleteById(id);
    }
}
