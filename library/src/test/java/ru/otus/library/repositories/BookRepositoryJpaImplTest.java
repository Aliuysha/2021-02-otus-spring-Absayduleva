package ru.otus.library.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;
import ru.otus.library.domain.Genre;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с книгами ")
@DataJpaTest
@Import(BookRepositoryJpaImpl.class)
public class BookRepositoryJpaImplTest {
    private static final int EXPECTED_NUMBER_OF_BOOKS = 3;
    private static final long EXISTING_BOOK_ID = 1;

    private static final String EXISTING_BOOK_NAME = "Оно";
    private static final String BOOK_NAME = "Книга от проблем";

    private static final String AUTHOR_NAME = "Топовый автор";
    private static final String GENRE_NAME = "Симпл Димпл";
    private static final String COMMENT_TEXT = "Классная книга, хочу еще";

    @Autowired
    private BookRepositoryJpaImpl repositoryJpa;

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

    @DisplayName(" должен корректно сохранять всю информацию о книге")
    @Test
    void shouldSaveAllBookInfo() {

        val author = new Author(0, AUTHOR_NAME);
        val genre = new Genre(0, GENRE_NAME);
        val comment = new Comment(0, COMMENT_TEXT);

        val authors = Collections.singletonList(author);
        val genres = Collections.singletonList(genre);
        val comments = Collections.singletonList(comment);

        val ono = new Book(0, BOOK_NAME, authors, genres, comments);
        repositoryJpa.save(ono);
        assertThat(ono.getId()).isGreaterThan(0);

        val actualBook = em.find(Book.class, ono.getId());
        assertThat(actualBook).isNotNull().matches(b -> !b.getName().equals(""))
                .matches(b -> b.getAuthors() != null && b.getAuthors().size() > 0 && b.getAuthors().get(0).getId() > 0)
                .matches(b -> b.getGenres() != null && b.getGenres().size() > 0 && b.getGenres().get(0).getId() > 0)
                .matches(b -> b.getComments() != null && b.getComments().size() > 0 && b.getComments().get(0).getId() > 0)
                .matches(b -> b.getName() != null);
    }

    @DisplayName(" должен загружать информацию о нужной книге по ее имени")
    @Test
    void shouldFindExpectedBookByName() {
        val firstBook = em.find(Book.class, EXISTING_BOOK_ID);
        List<Book> books = repositoryJpa.findByName(EXISTING_BOOK_NAME);
        assertThat(books).containsOnlyOnce(firstBook);
    }

    @DisplayName(" должен изменять имя заданной книги по ее id")
    @Test
    void shouldUpdateStudentNameById() {
        val firstBook = em.find(Book.class, EXISTING_BOOK_ID);
        String oldName = firstBook.getName();
        em.detach(firstBook);

        repositoryJpa.updateNameById(EXISTING_BOOK_ID, BOOK_NAME);
        val updatedBook = em.find(Book.class, EXISTING_BOOK_ID);

        assertThat(updatedBook.getName()).isNotEqualTo(oldName).isEqualTo(BOOK_NAME);
    }

    @DisplayName(" должен удалять заданной книги по ее id")
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
