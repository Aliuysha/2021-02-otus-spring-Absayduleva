package spring.ru.otus.library.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.ru.otus.library.domain.Author;
import spring.ru.otus.library.domain.Book;
import spring.ru.otus.library.domain.Comment;
import spring.ru.otus.library.domain.Genre;
import spring.ru.otus.library.repositories.BookRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = BookController.class)
public class BookControllerTest {
    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private WebTestClient webClient;


    @Test
    public void testRoute() {
        Author author = new Author("author");
        Genre genre = new Genre("genre");
        Comment comment = new Comment("comment");
        Book book = new Book();
        book.setGenres(Collections.singletonList(genre));
        book.setAuthors(Collections.singletonList(author));
        book.setComments(Collections.singletonList(comment));
        book.setName("book1");

        List<Book> list = new ArrayList<>();
        list.add(book);

        Flux<Book> bookFlux = Flux.fromIterable(list);

        Mockito
                .when(bookRepository.save(book))
                .thenReturn(Mono.just(book));

        Mockito
                .when(bookRepository.findAll())
                .thenReturn(bookFlux);

        webClient.get()
                .uri("/api/books")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Book.class);

        Mockito.verify(bookRepository, times(1)).findAll();
    }
}
