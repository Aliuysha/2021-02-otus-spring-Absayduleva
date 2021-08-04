package spring.ru.otus.library.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.ru.otus.library.domain.Genre;

import java.util.UUID;

public interface GenreRepository extends MongoRepository<Genre, UUID> {
}
