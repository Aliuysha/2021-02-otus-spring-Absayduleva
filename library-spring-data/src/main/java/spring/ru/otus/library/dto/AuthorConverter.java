package spring.ru.otus.library.dto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.ru.otus.library.repositories.AuthorRepository;
import spring.ru.otus.library.service.EntityNotFoundException;
import spring.ru.otus.library.web.service.mapper.BookMapper;

import java.util.UUID;

@Component
public class AuthorConverter implements Converter<String, AuthorDto> {

    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    public AuthorConverter(AuthorRepository authorRepository, BookMapper bookMapper) {
        this.authorRepository = authorRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public AuthorDto convert(String s) {
        return bookMapper.convert(authorRepository.findById(UUID.fromString(s)).orElseThrow(EntityNotFoundException::new));
    }
}
