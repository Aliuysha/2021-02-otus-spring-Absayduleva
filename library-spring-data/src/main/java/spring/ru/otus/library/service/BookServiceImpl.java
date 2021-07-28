package spring.ru.otus.library.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.ru.otus.library.domain.Author;
import spring.ru.otus.library.domain.Book;
import spring.ru.otus.library.domain.Comment;
import spring.ru.otus.library.domain.Genre;
import spring.ru.otus.library.repositories.AuthorRepository;
import spring.ru.otus.library.repositories.BookRepository;
import spring.ru.otus.library.repositories.GenreRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final SequenceGeneratorService sequenceGeneratorService;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final BookUI bookUI;

    public BookServiceImpl(
            SequenceGeneratorService sequenceGeneratorService,
            AuthorRepository authorRepository,
            BookRepository bookRepository,
            GenreRepository genreRepository,
            BookUI bookUI
    ) {
        this.sequenceGeneratorService = sequenceGeneratorService;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.bookUI = bookUI;
    }

    @Transactional
    @Override
    public String getAllBooks() {
        List<Book> books = bookRepository.findAll();
        StringBuilder result = new StringBuilder();
        for (Book book : books) {
            result.append(Formatter.getBookNameFormat(book));
        }
        return result.toString();
    }

    @Transactional
    @Override
    public String getBookById() {
        Book book = bookRepository.findById(
                bookUI.getBookId()).orElseThrow(EntityNotFoundException::new
        );
        return Formatter.getBookNameFormat(book);
    }

    @Transactional
    @Override
    public String insertBook() {
        String name = bookUI.getBookName();

        List<Genre> genres = genreRepository.findAll();
        long genreId = bookUI.getGenreId(genres);

        if (genreId > genres.size()) {
            return Consts.WRONG_DATA;
        }

        List<Author> authors = authorRepository.findAll();
        long authorId = bookUI.getAuthorId(authors);
        if (authorId > authors.size()) {
            return Consts.WRONG_DATA;
        }

        String commentText = bookUI.getComment();

        Comment comment = new Comment();
        comment.setId(sequenceGeneratorService.getSequenceNumber(Comment.SEQUENCE_NAME));
        comment.setText(commentText);

        Author author = authorRepository.findById(authorId).orElseThrow(EntityNotFoundException::new);
        Genre genre = genreRepository.findById(genreId).orElseThrow(EntityNotFoundException::new);
        Book newBook = new Book();
        newBook.setId(sequenceGeneratorService.getSequenceNumber(Book.SEQUENCE_NAME));
        newBook.setName(name);
        newBook.setAuthors(Collections.singletonList(author));
        newBook.setGenres(Collections.singletonList(genre));
        newBook.setComments(Collections.singletonList(comment));

        bookRepository.save(newBook);
        return Consts.SUCCESSFUL_BOOK;
    }

    @Transactional
    @Override
    public void deleteBook() {
        long id = bookUI.getBookId();
        bookRepository.deleteById(id);
    }

    @Transactional
    @Override
    public String getAllCommentsByBook() {
        long id = bookUI.getBookId();
        Optional<Book> book = bookRepository.findById(id);
        return book.map(value -> Formatter.getCommentNameFormat(value.getComments()))
                .orElse(Consts.WRONG_DATA);
    }

    @Transactional
    @Override
    public void updateNameById() {
        long id = bookUI.getBookId();
        String bookName = bookUI.getBookName();
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setName(bookName);
            bookRepository.save(book);
        }
    }
}

