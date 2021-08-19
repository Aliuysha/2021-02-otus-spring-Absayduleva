package spring.ru.otus.library.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import spring.ru.otus.library.domain.Author;


public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
}
