package ru.otus.library.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.library.domain.Author;

public interface AuthorRepositoryJpa extends CrudRepository<Author, Long> {

}
