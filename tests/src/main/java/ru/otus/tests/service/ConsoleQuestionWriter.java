package ru.otus.tests.service;

import ru.otus.tests.domain.Question;

import java.util.List;

public class ConsoleQuestionWriter implements QuestionWriter {
    @Override
    public void writeQuestions(List<Question> questionList) {
        for (Question question : questionList) {
            System.out.println(question.getId() + ". " + question.getText());
        }
    }
}
