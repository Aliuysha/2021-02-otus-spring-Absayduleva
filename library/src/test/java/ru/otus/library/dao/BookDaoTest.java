package ru.otus.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("dao для работы с книгами дложно ")
@JdbcTest
@Import(BookDaoJdbc.class)
public class BookDaoTest {
    private static final int EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_NAME = "It";

    private static final String EXISTING_AUTHOR = "Stiven King";
    private static final int EXISTING_AUTHOR_ID = 1;

    private static final String EXISTING_GENRE = "horror";
    private static final int EXISTING_GENRE_ID = 1;

    @Autowired
    private BookDaoJdbc bookDao;


    @BeforeTransaction
    void beforeTransaction() {
        System.out.println("beforeTransaction");
    }

    @AfterTransaction
    void afterTransaction() {
        System.out.println("afterTransaction");
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        Author author = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR);
        Genre genre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE);
        Book expectedBook = new Book(2, "It2", author, genre);
        bookDao.insert(expectedBook);
        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать ожидаемую книгу по его id")
    @Test
    void shouldReturnExpectedBookById() {
        Author author = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR);
        Genre genre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE);
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME, author, genre);
        Book actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("удалять заданную книгу по ее id")
    @Test
    void shouldCorrectDeleteBookById() {
        assertThatCode(() -> bookDao.getById(EXISTING_BOOK_ID))
                .doesNotThrowAnyException();

        bookDao.deleteById(EXISTING_BOOK_ID);

        assertThatThrownBy(() -> bookDao.getById(EXISTING_BOOK_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBookList() {
        Author author = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR);
        Genre genre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE);
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME, author, genre);
        List<Book> actualBookList = bookDao.getAll();
        assertThat(actualBookList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedBook);
    }
}
