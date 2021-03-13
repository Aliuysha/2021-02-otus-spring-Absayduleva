package ru.otus.tests.domain;

public enum TestResult {
    SUCCESSFUL("You passed the test successfully!"),
    FAIL("You have failed the test!");

    private final String message;

    TestResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
