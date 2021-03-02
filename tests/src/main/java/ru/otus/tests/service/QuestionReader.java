package ru.otus.tests.service;

import ru.otus.tests.domain.Question;

import java.util.List;

public interface QuestionReader {

    List<Question> readQuestions();
}
