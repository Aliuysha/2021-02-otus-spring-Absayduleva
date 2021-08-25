package spring.ru.otus.library.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import spring.ru.otus.library.domain.Author;
import spring.ru.otus.library.domain.Book;
import spring.ru.otus.library.domain.Comment;
import spring.ru.otus.library.domain.Genre;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Test
    public void shouldSetIdOnSave() {
        Author author = new Author("author");
        Genre genre = new Genre("genre");
        Comment comment = new Comment("comment");
        Book book = new Book();
        book.setGenres(Collections.singletonList(genre));
        book.setAuthors(Collections.singletonList(author));
        book.setComments(Collections.singletonList(comment));
        book.setName("book1");

        Mono<Book> bookMono = repository.save(book);

        StepVerifier
                .create(bookMono)
                .assertNext(book1 -> assertNotNull(book1.getId()))
                .expectComplete()
                .verify();
    }
}
