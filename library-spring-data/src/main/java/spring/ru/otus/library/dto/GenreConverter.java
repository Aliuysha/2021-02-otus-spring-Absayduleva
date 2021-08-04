package spring.ru.otus.library.dto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.ru.otus.library.repositories.GenreRepository;
import spring.ru.otus.library.service.EntityNotFoundException;
import spring.ru.otus.library.web.service.mapper.BookMapper;

import java.util.UUID;

@Component
public class GenreConverter implements Converter<String, GenreDto> {

    private final GenreRepository genreRepository;
    private final BookMapper bookMapper;

    public GenreConverter(GenreRepository genreRepository, BookMapper bookMapper) {
        this.genreRepository = genreRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public GenreDto convert(String s) {
        return bookMapper.convert(genreRepository.findById(UUID.fromString(s)).orElseThrow(EntityNotFoundException::new));
    }
}
