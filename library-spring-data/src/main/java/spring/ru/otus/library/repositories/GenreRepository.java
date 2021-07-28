package spring.ru.otus.library.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.ru.otus.library.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, Long> {
}
