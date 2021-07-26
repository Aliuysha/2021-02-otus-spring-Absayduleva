package ru.otus.library.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.library.domain.Book;

import java.util.List;

public interface BookRepositoryJpa extends CrudRepository<Book, Long> {
    List<Book> findByName(String name);
}
