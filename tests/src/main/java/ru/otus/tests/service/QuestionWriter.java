package ru.otus.tests.service;

import ru.otus.tests.domain.Question;

import java.util.List;

public interface QuestionWriter {

    void writeQuestions(List<Question> questionList);
}
