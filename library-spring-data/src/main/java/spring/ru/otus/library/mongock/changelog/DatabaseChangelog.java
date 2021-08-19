package spring.ru.otus.library.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import io.changock.migration.api.annotations.ChangeLog;
import reactor.core.publisher.Flux;
import spring.ru.otus.library.domain.Author;
import spring.ru.otus.library.domain.Book;
import spring.ru.otus.library.domain.Comment;
import spring.ru.otus.library.domain.Genre;
import spring.ru.otus.library.repositories.AuthorRepository;
import spring.ru.otus.library.repositories.BookRepository;
import spring.ru.otus.library.repositories.GenreRepository;

import java.util.Arrays;
import java.util.Collections;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "aliya", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "aliya")
    public void insertAuthors(AuthorRepository authorRepository) {
        Author stivenKing = new Author("Stiven King");
        Author dariaDoncova = new Author("Дарья донцова");

        authorRepository.saveAll(Arrays.asList(
                stivenKing, dariaDoncova
        )).subscribe();
    }

    @ChangeSet(order = "003", id = "insertGenres", author = "aliya")
    public void insertGenres(GenreRepository genreRepository) {
        Genre horror = new Genre("хоррор");
        Genre detective = new Genre("детектив");

        genreRepository.saveAll(Arrays.asList(
                horror, detective
        )).subscribe();
    }

    @ChangeSet(order = "004", id = "insertBooks", author = "aliya")
    public void insertBooks(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository) {
        Flux<Genre> genres = genreRepository.findAll();
        Flux<Author> authors = authorRepository.findAll();

        Book book = new Book();
        book.setName("Книга 1");
        book.setAuthors(authors.collectList().block());
        book.setGenres(genres.collectList().block());

        Comment comment = new Comment();
        comment.setText("text");

        book.setComments(Collections.singletonList(comment));
        bookRepository.save(book).subscribe();
    }
}
