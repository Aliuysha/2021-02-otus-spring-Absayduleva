package spring.ru.otus.library.service;

import org.springframework.stereotype.Component;
import spring.ru.otus.library.domain.Author;
import spring.ru.otus.library.domain.Genre;

import java.util.List;

@Component
public class BookUIImpl implements BookUI {
    private final BookReader bookReader;
    private final BookWriter bookWriter;

    public BookUIImpl(BookReader bookReader, BookWriter bookWriter) {
        this.bookReader = bookReader;
        this.bookWriter = bookWriter;
    }

    @Override
    public String getBookName() {
        bookWriter.writeText(Consts.INTER_BOOK_NAME);
        return bookReader.readText();
    }

    @Override
    public String getBookId() {
        bookWriter.writeText(Consts.INTER_BOOK_ID);
        return bookReader.readStringId();
    }

    @Override
    public String getGenreId(List<Genre> genres) {
        bookWriter.writeText(Consts.CHOOSE_GENRE);
        bookWriter.writeText(Formatter.getGenreNameFormat(genres));
        return bookReader.readStringId();
    }

    @Override
    public String getAuthorId(List<Author> authors) {
        bookWriter.writeText(Consts.CHOOSE_AUTHOR);
        bookWriter.writeText(Formatter.getAuthorNameFormat(authors));
        return bookReader.readStringId();
    }

    @Override
    public String getComment() {
        bookWriter.writeText(Consts.LEAVE_COMMENT);
        return bookReader.readText();
    }
}
