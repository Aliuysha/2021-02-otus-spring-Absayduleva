package ru.otus.library.service;

import org.springframework.stereotype.Component;

@Component
public class ConsoleWriter implements Writer {
    @Override
    public void write(String text) {
        System.out.println(text);
    }
}
