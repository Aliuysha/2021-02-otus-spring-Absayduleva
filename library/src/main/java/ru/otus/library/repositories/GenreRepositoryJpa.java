package ru.otus.library.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.library.domain.Genre;

public interface GenreRepositoryJpa extends CrudRepository<Genre, Long> {
}
