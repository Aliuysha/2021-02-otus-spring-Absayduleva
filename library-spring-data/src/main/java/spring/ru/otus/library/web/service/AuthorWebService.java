package spring.ru.otus.library.web.service;

import spring.ru.otus.library.dto.AuthorDto;

import java.util.List;

public interface AuthorWebService {
    List<AuthorDto> getAllAuthors();
}
