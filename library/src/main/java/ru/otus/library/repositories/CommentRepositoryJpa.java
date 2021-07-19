package ru.otus.library.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.library.domain.Comment;

public interface CommentRepositoryJpa extends CrudRepository<Comment, Long> {
}
