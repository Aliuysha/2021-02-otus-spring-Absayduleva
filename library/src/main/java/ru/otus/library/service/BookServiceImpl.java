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
public class BookServiceImpl implements BookService {
    private final AuthorRepositoryJpa authorRepositoryJpa;
    private final BookRepositoryJpa bookRepositoryJpa;
    private final GenreRepositoryJpa genreRepositoryJpa;
    private final BookUI bookUI;

    public BookServiceImpl(
            AuthorRepositoryJpa authorRepositoryJpa,
            BookRepositoryJpa bookRepositoryJpa,
            GenreRepositoryJpa genreRepositoryJpa,
            BookUI bookUI
    ) {
        this.authorRepositoryJpa = authorRepositoryJpa;
        this.bookRepositoryJpa = bookRepositoryJpa;
        this.genreRepositoryJpa = genreRepositoryJpa;
        this.bookUI = bookUI;
    }

    @Override
    public String getAllBooks() {
        List<Book> books = bookRepositoryJpa.findAll();
        StringBuilder result = new StringBuilder();
        for (Book book : books) {
            result.append(Formatter.getBookNameFormat(book));
        }
        return result.toString();
    }

    @Override
    public String getBookById() {
        Book book = bookRepositoryJpa.findById(
                bookUI.getBookId()).orElseThrow(EntityNotFoundException::new
        );
        return Formatter.getBookNameFormat(book);
    }

    @Transactional
    @Override
    public String insertBook() {
        String name = bookUI.getBookName();

        List<Genre> genres = genreRepositoryJpa.findAll();
        long genreId = bookUI.getGenreId(genres);

        if (genreId > genres.size()) {
            return Consts.WRONG_DATA;
        }

        List<Author> authors = authorRepositoryJpa.findAll();
        long authorId = bookUI.getAuthorId(authors);
        if (authorId > authors.size()) {
            return Consts.WRONG_DATA;
        }

        String commentText = bookUI.getComment();

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
        return Consts.SUCCESSFUL_BOOK;
    }

    @Transactional
    @Override
    public void deleteBook() {
        long id = bookUI.getBookId();
        bookRepositoryJpa.deleteById(id);
    }
}

