package ru.otus.library.service;

import org.springframework.stereotype.Service;
import ru.otus.library.dao.AuthorDao;
import ru.otus.library.dao.BookDao;
import ru.otus.library.dao.GenreDao;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;

import java.util.List;

@Service
public class BookService {
    private static final String WRONG_DATA = "Неверные данные";
    private final Reader reader;
    private final Writer writer;
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookService(Reader reader, Writer writer, BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.reader = reader;
        this.writer = writer;
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    private String getBookNameFormat(Book book) {
        return book.getId() + " " +
                book.getName() +
                "\n" +
                "автор: " +
                book.getAuthor().getName() +
                "\n" +
                "жанр: " +
                book.getGenre().getName() +
                "\n";
    }

    private String getAuthorNameFormat(Author author) {
        return author.getId() + " " +
                author.getName() +
                "\n";
    }

    private String getGenreNameFormat(Genre genre) {
        return genre.getId() + " " +
                genre.getName() +
                "\n";
    }

    public String getAllBooks() {
        List<Book> books = bookDao.getAll();
        StringBuilder result = new StringBuilder();
        for (Book book : books) {
            result.append(getBookNameFormat(book));
        }
        return result.toString();
    }

    public String getBookById() {
        writer.write("Введите id книги:");
        String id = reader.read();
        return getBookNameFormat(bookDao.getById(Long.parseLong(id)));
    }

    public String insertBook() {
        writer.write("Введите название книги:");
        String name = reader.read();

        writer.write("Выберите жанр (id):");
        List<Genre> genres = genreDao.getAll();
        StringBuilder genreString = new StringBuilder();
        for (Genre genre : genres) {
            genreString.append(getGenreNameFormat(genre));
        }
        writer.write(genreString.toString());
        long genreId = Long.parseLong(reader.read());
        if (genreId > genres.size()) {
            return WRONG_DATA;
        }

        writer.write("Выберите автора (id):");
        List<Author> authors = authorDao.getAll();
        StringBuilder authorsString = new StringBuilder();
        for (Author author : authors) {
            authorsString.append(getAuthorNameFormat(author));
        }
        writer.write(authorsString.toString());
        long authorId = Long.parseLong(reader.read());
        if (authorId > authors.size()) {
            return WRONG_DATA;
        }

        long books = bookDao.getAll().size();
        Author author = authorDao.getById(authorId);
        Genre genre = genreDao.getById(genreId);

        bookDao.insert(new Book(books + 1, name, author, genre));
        return "Книга успешно добавлена";
    }

    public void deleteBook() {
        writer.write("Введите id книги, которую хотите удалить: ");
        long id = Long.parseLong(reader.read());
        bookDao.deleteById(id);
    }
}
