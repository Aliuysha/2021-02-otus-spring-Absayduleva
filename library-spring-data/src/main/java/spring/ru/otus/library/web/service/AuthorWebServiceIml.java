package spring.ru.otus.library.web.service;

import org.springframework.stereotype.Service;
import spring.ru.otus.library.dto.AuthorDto;
import spring.ru.otus.library.repositories.AuthorRepository;
import spring.ru.otus.library.web.service.mapper.BookMapper;

import java.util.List;

@Service
public class AuthorWebServiceIml implements AuthorWebService {

    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    public AuthorWebServiceIml(AuthorRepository authorRepository, BookMapper bookMapper) {
        this.authorRepository = authorRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        return bookMapper.convertAuthors(authorRepository.findAll());
    }
}
