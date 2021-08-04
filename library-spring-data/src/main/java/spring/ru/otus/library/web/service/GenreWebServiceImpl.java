package spring.ru.otus.library.web.service;

import org.springframework.stereotype.Service;
import spring.ru.otus.library.dto.GenreDto;
import spring.ru.otus.library.repositories.GenreRepository;
import spring.ru.otus.library.web.service.mapper.BookMapper;

import java.util.List;

@Service
public class GenreWebServiceImpl implements GenreWebService {

    private final GenreRepository genreRepository;
    private final BookMapper bookMapper;

    public GenreWebServiceImpl(GenreRepository genreRepository, BookMapper bookMapper) {
        this.genreRepository = genreRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<GenreDto> getAllGenres() {
        return bookMapper.convertGenres(genreRepository.findAll());
    }
}
