package spring.ru.otus.library.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.ru.otus.library.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, Long> {
}
