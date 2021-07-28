package spring.ru.otus.library.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.ru.otus.library.domain.Book;

public interface BookRepository extends MongoRepository<Book, Long> {
}
