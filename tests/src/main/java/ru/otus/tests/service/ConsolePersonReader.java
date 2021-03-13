package ru.otus.tests.service;

import org.springframework.stereotype.Component;
import ru.otus.tests.domain.Person;

import java.util.Scanner;

@Component
public class ConsolePersonReader implements PersonReader {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Person readPerson() {
        System.out.println("Your last name:");
        String lastName = scanner.nextLine();
        System.out.println("Your first name:");
        String firstName = scanner.nextLine();
        return new Person(lastName, firstName);
    }
}
