package spring.ru.otus.library.dto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.ru.otus.library.domain.Genre;
import spring.ru.otus.library.repositories.GenreRepository;

@Component
public class GenreConverter implements Converter<String, Genre> {

    private final GenreRepository genreRepository;

    public GenreConverter(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre convert(String id) {
        return genreRepository.findById(id).block();
    }
}
