package spring.ru.otus.library.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import spring.ru.otus.library.domain.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
