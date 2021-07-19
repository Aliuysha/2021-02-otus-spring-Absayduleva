package ru.otus.library.service;

import org.springframework.stereotype.Component;

@Component
public class BookReaderImpl implements BookReader {
    private final Reader reader;

    public BookReaderImpl(Reader reader) {
        this.reader = reader;
    }

    @Override
    public long readId() {
        return Long.parseLong(reader.read());
    }

    @Override
    public String readText() {
        return reader.read();
    }
}
