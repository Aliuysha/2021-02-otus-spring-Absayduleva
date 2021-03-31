package ru.otus.tests.service;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleAnswerReader implements AnswerReader {
    private final MessageManager messageSource;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleAnswerReader(MessageManager messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String readAnswer() {
        System.out.println(messageSource.getMessage("test.enter.answer"));
        return scanner.nextLine();
    }
}
