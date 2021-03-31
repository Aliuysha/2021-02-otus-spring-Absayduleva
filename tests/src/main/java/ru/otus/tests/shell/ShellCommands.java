package ru.otus.tests.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.tests.service.TestCreator;

@ShellComponent
public class ShellCommands {
    private final TestCreator testCreator;

    public ShellCommands(TestCreator testCreator) {
        this.testCreator = testCreator;
    }

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "AnyUser") String userName) {
        return String.format("Добро пожаловать: %s", userName);
    }

    @ShellMethod(value = "Test command", key = {"t", "test"})
    public String test() {
        testCreator.test();
        return "Тест закончен";
    }
}
