package ru.otus.tests.service;

import org.springframework.stereotype.Component;
import ru.otus.tests.domain.Person;

import java.util.Scanner;

@Component
public class ConsolePersonReader implements PersonReader {
    private final MessageManager messageManager;

    private final Scanner scanner;

    public ConsolePersonReader(MessageManager messageManager, Scanner scanner) {
        this.messageManager = messageManager;
        this.scanner = scanner;
    }

    @Override
    public Person readPerson() {
        System.out.println(messageManager.getMessage("test.person.lastName"));
        String lastName = scanner.nextLine();
        System.out.println(messageManager.getMessage("test.person.firstName"));
        String firstName = scanner.nextLine();
        return new Person(lastName, firstName);
    }
}
