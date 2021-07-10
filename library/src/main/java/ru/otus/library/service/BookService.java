package ru.otus.library.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;
import ru.otus.library.domain.Genre;
import ru.otus.library.repositories.AuthorRepositoryJpa;
import ru.otus.library.repositories.BookRepositoryJpa;
import ru.otus.library.repositories.GenreRepositoryJpa;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class BookService {
    private static final String WRONG_DATA = "Неверные данные";
    private final Reader reader;
    private final Writer writer;

    private final AuthorRepositoryJpa authorRepositoryJpa;
    private final BookRepositoryJpa bookRepositoryJpa;
    private final GenreRepositoryJpa genreRepositoryJpa;

    public BookService(
            Reader reader,
            Writer writer,
            AuthorRepositoryJpa authorRepositoryJpa,
            BookRepositoryJpa bookRepositoryJpa,
            GenreRepositoryJpa genreRepositoryJpa
    ) {
        this.reader = reader;
        this.writer = writer;
        this.authorRepositoryJpa = authorRepositoryJpa;
        this.bookRepositoryJpa = bookRepositoryJpa;
        this.genreRepositoryJpa = genreRepositoryJpa;
    }

    private String getBookNameFormat(Book book) {
        return book.getId() + " " +
                book.getName() +
                "\n" +
                "авторы: " +
                getAuthorNameFormat(book.getAuthors()) +
                "\n" +
                "жанры: " +
                getGenreNameFormat(book.getGenres()) +
                "\n" +
                "комментарии: " +
                getCommentNameFormat(book.getComments()) +
                "\n";
    }

    private String getAuthorNameFormat(List<Author> authors) {
        StringBuilder str = new StringBuilder();
        for (Author author : authors) {
            str.append(author.getId()).append(" ").append(author.getName()).append(" \n");
        }
        return str.toString();
    }

    private String getGenreNameFormat(List<Genre> genres) {
        StringBuilder str = new StringBuilder();
        for (Genre genre : genres) {
            str.append(genre.getId()).append(" ").append(genre.getName()).append(" \n");
        }
        return str.toString();
    }

    private String getCommentNameFormat(List<Comment> comments) {
        StringBuilder str = new StringBuilder();
        for (Comment comment : comments) {
            str.append(comment.getId()).append(" ").append(comment.getText()).append(" \n");
        }
        return str.toString();
    }

    public String getAllBooks() {
        List<Book> books = bookRepositoryJpa.findAll();
        StringBuilder result = new StringBuilder();
        for (Book book : books) {
            result.append(getBookNameFormat(book));
        }
        return result.toString();
    }

    public String getBookById() {
        writer.write("Введите id книги:");
        String id = reader.read();
        Book book = bookRepositoryJpa.findById(Long.parseLong(id)).orElseThrow(EntityNotFoundException::new);
        return getBookNameFormat(book);
    }

    public String insertBook() {
        writer.write("Введите название книги:");
        String name = reader.read();

        writer.write("Выберите жанр (id):");
        List<Genre> genres = genreRepositoryJpa.findAll();

        writer.write(getGenreNameFormat(genres));
        long genreId = Long.parseLong(reader.read());
        if (genreId > genres.size()) {
            return WRONG_DATA;
        }

        writer.write("Выберите автора (id):");
        List<Author> authors = authorRepositoryJpa.findAll();

        writer.write(getAuthorNameFormat(authors));
        long authorId = Long.parseLong(reader.read());
        if (authorId > authors.size()) {
            return WRONG_DATA;
        }

        writer.write("Оставьте комментарий:");
        String commentText = reader.read();

        Comment comment = new Comment();
        comment.setText(commentText);

        Author author = authorRepositoryJpa.findById(authorId).orElseThrow(EntityNotFoundException::new);
        Genre genre = genreRepositoryJpa.findById(genreId).orElseThrow(EntityNotFoundException::new);
        Book newBook = new Book();
        newBook.setName(name);
        newBook.setAuthors(Collections.singletonList(author));
        newBook.setGenres(Collections.singletonList(genre));
        newBook.setComments(Collections.singletonList(comment));

        bookRepositoryJpa.save(newBook);
        return "Книга успешно добавлена";
    }

    public void deleteBook() {
        writer.write("Введите id книги, которую хотите удалить: ");
        long id = Long.parseLong(reader.read());
        bookRepositoryJpa.deleteById(id);
    }
}
