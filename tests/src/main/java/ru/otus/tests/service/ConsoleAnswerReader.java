package ru.otus.tests.service;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleAnswerReader implements AnswerReader {
    private final Scanner scanner = new Scanner(System.in);
    @Override
    public String readAnswer() {
        System.out.println("Enter answer:");
        return scanner.nextLine();
    }
}
