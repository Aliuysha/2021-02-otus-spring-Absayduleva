package spring.ru.otus.library.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.ru.otus.library.domain.Author;
import spring.ru.otus.library.domain.Book;
import spring.ru.otus.library.domain.Comment;
import spring.ru.otus.library.domain.Genre;
import spring.ru.otus.library.dto.BookDto;
import spring.ru.otus.library.repositories.AuthorRepository;
import spring.ru.otus.library.repositories.BookRepository;
import spring.ru.otus.library.repositories.GenreRepository;
import spring.ru.otus.library.web.service.mapper.BookMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final BookUI bookUI;
    private final BookMapper bookMapper;

    public BookServiceImpl(
            AuthorRepository authorRepository,
            BookRepository bookRepository,
            GenreRepository genreRepository,
            BookUI bookUI,
            BookMapper bookMapper
    ) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.bookUI = bookUI;
        this.bookMapper = bookMapper;
    }

    @Transactional
    @Override
    public String getAllBooksToString() {
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
                UUID.fromString(bookUI.getBookId())).orElseThrow(EntityNotFoundException::new
        );
        return Formatter.getBookNameFormat(book);
    }

    @Transactional
    @Override
    public String insertBook() {
        String name = bookUI.getBookName();

        List<Genre> genres = genreRepository.findAll();
        String genreId = bookUI.getGenreId(genres);

        List<Author> authors = authorRepository.findAll();
        String authorId = bookUI.getAuthorId(authors);

        String commentText = bookUI.getComment();

        Comment comment = new Comment();
        comment.setId(UUID.randomUUID());
        comment.setText(commentText);

        Author author = authorRepository.findById(UUID.fromString(authorId)).orElseThrow(EntityNotFoundException::new);
        Genre genre = genreRepository.findById(UUID.fromString(genreId)).orElseThrow(EntityNotFoundException::new);
        Book newBook = new Book();
        newBook.setId(UUID.randomUUID());
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
        String id = bookUI.getBookId();
        bookRepository.deleteById(UUID. fromString(id));
    }

    @Transactional
    @Override
    public String getAllCommentsByBook() {
        String id = bookUI.getBookId();
        Optional<Book> book = bookRepository.findById(UUID.fromString(id));
        return book.map(value -> Formatter.getCommentNameFormat(value.getComments()))
                .orElse(Consts.WRONG_DATA);
    }

    @Transactional
    @Override
    public void updateNameById() {
        String id = bookUI.getBookId();
        String bookName = bookUI.getBookName();
        Optional<Book> optionalBook = bookRepository.findById(UUID.fromString(id));
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setName(bookName);
            bookRepository.save(book);
        }
    }

    @Transactional
    @Override
    public List<BookDto> getAllBooks() {
        return bookMapper.convert(bookRepository.findAll());
    }

    @Transactional
    @Override
    public BookDto getBookById(UUID id) {
        var book = bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return bookMapper.convert(book);
    }

    @Transactional
    @Override
    public BookDto insertBook(BookDto bookDto) {
        Book save = bookRepository.save(bookMapper.convert(bookDto));
        return bookMapper.convert(save);
    }

    @Transactional
    @Override
    public void deleteBookById(UUID id) {
        bookRepository.deleteById(id);
    }
}

