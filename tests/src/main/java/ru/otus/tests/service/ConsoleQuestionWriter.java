package ru.otus.tests.service;

import org.springframework.stereotype.Component;
import ru.otus.tests.domain.Question;

@Component
public class ConsoleQuestionWriter implements QuestionWriter {
    @Override
    public void writeQuestion(Question question) {
        System.out.println("Question №" + question.getId() + ". " + question.getText());
    }
}
