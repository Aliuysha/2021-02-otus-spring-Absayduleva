package ru.otus.library.service;

import org.springframework.stereotype.Component;

@Component
public class BookWriterImpl implements BookWriter {

    private final Writer writer;

    public BookWriterImpl(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void writeText(String text) {
        writer.write(text);
    }
}