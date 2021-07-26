package ru.otus.library.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.library.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с книгами ")
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BookRepositoryJpaImplTest {
    private static final int EXPECTED_NUMBER_OF_BOOKS = 3;
    private static final long EXISTING_BOOK_ID = 1;

    private static final String EXISTING_BOOK_NAME = "Оно";

    @Autowired
    private BookRepositoryJpa repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName(" должен загружать информацию о нужной книге по ее id")
    @Test
    void shouldFindExpectedBookById() {
        val optionalActualBook = repositoryJpa.findById(EXISTING_BOOK_ID);
        val expectedStudent = em.find(Book.class, EXISTING_BOOK_ID);
        assertThat(optionalActualBook).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedStudent);
    }

    @DisplayName(" должен загружать список всех книг с полной информацией о них")
    @Test
    void shouldReturnCorrectBooksListWithAllInfo() {
        val students = repositoryJpa.findAll();
        assertThat(students).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS);
    }

    @DisplayName(" должен загружать информацию о нужной книге по ее имени")
    @Test
    void shouldFindExpectedBookByName() {
        val firstBook = em.find(Book.class, EXISTING_BOOK_ID);
        List<Book> books = repositoryJpa.findByName(EXISTING_BOOK_NAME);
        assertThat(books).containsOnlyOnce(firstBook);
    }

    @DisplayName(" должен удалять заданную книгу по ее id")
    @Test
    void shouldDeleteStudentNameById() {
        val firstBook = em.find(Book.class, EXISTING_BOOK_ID);
        assertThat(firstBook).isNotNull();
        em.detach(firstBook);

        repositoryJpa.deleteById(EXISTING_BOOK_ID);
        val deletedBook = em.find(Book.class, EXISTING_BOOK_ID);

        assertThat(deletedBook).isNull();
    }

}
