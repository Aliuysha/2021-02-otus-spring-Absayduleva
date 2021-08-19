package spring.ru.otus.library.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import spring.ru.otus.library.domain.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {
}
