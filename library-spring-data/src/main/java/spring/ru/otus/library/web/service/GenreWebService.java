package spring.ru.otus.library.web.service;

import spring.ru.otus.library.dto.GenreDto;

import java.util.List;

public interface GenreWebService {
    List<GenreDto> getAllGenres();
}
