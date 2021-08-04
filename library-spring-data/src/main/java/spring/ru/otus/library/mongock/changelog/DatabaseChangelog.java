package spring.ru.otus.library.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import io.changock.migration.api.annotations.ChangeLog;
import spring.ru.otus.library.domain.Author;
import spring.ru.otus.library.domain.Book;
import spring.ru.otus.library.domain.Genre;
import spring.ru.otus.library.repositories.AuthorRepository;
import spring.ru.otus.library.repositories.BookRepository;
import spring.ru.otus.library.repositories.GenreRepository;

import java.util.List;
import java.util.UUID;

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

        authorRepository.save(stivenKing);
        authorRepository.save(dariaDoncova);
    }

    @ChangeSet(order = "003", id = "insertGenres", author = "aliya")
    public void insertGenres(GenreRepository genreRepository) {
        Genre horror = new Genre("хоррор");

        Genre detective = new Genre("детектив");

        genreRepository.save(horror);
        genreRepository.save(detective);
    }

    @ChangeSet(order = "004", id = "insertBooks", author = "aliya")
    public void insertBooks(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository) {
        List<Genre> genres = genreRepository.findAll();
        List<Author> authors = authorRepository.findAll();

        Book book = new Book();
        book.setName("Книга 1");
        book.setAuthors(authors);
        book.setGenres(genres);
        book.setId(UUID.randomUUID());

        bookRepository.save(book);
    }
}
