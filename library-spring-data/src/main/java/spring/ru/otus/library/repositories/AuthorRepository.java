package spring.ru.otus.library.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.ru.otus.library.domain.Author;

import java.util.UUID;

public interface AuthorRepository extends MongoRepository<Author, UUID> {
}
