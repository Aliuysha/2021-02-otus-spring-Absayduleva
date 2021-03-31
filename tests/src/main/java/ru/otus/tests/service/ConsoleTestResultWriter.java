package ru.otus.tests.service;

import org.springframework.stereotype.Component;
import ru.otus.tests.domain.TestResult;

@Component
public class ConsoleTestResultWriter implements TestResultWriter {
    private final MessageManager messageManager;

    public ConsoleTestResultWriter(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    @Override
    public void printResult(TestResult testResult) {
        System.out.println(testResult.getMessage(messageManager));
    }
}
