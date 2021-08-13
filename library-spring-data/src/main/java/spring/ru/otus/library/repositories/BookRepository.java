package spring.ru.otus.library.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.ru.otus.library.domain.Book;

import java.util.UUID;

public interface BookRepository extends MongoRepository<Book, UUID> {
}
