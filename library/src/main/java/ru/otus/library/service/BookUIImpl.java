package ru.otus.library.service;

import org.springframework.stereotype.Component;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Genre;

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
    public long getBookId() {
        bookWriter.writeText(Consts.INTER_BOOK_ID);
        return bookReader.readId();
    }

    @Override
    public long getGenreId(List<Genre> genres) {
        bookWriter.writeText(Consts.CHOOSE_GENRE);
        bookWriter.writeText(Formatter.getGenreNameFormat(genres));
        return bookReader.readId();
    }

    @Override
    public long getAuthorId(List<Author> authors) {
        bookWriter.writeText(Consts.CHOOSE_AUTHOR);
        bookWriter.writeText(Formatter.getAuthorNameFormat(authors));
        return bookReader.readId();
    }

    @Override
    public String getComment() {
        bookWriter.writeText(Consts.LEAVE_COMMENT);
        return bookReader.readText();
    }
}
