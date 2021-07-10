package ru.otus.library.repositories;

import ru.otus.library.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepositoryJpa {
    Comment save(Comment comment);

    Optional<Comment> findById(long id);

    List<Comment> findAll();

    List<Comment> findByText(String name);

    void updateTextById(long id, String text);

    void deleteById(long id);
}
