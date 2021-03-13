package ru.otus.tests.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.otus.tests.domain.Person;
import ru.otus.tests.domain.Question;

import java.util.List;

@Service
public class SimpleTestReader implements TestReader {
    private final PersonReader personReader;
    private final QuestionReader questionReader;
    private final AnswerReader answerReader;

    public SimpleTestReader(
            @Qualifier("consolePersonReader") PersonReader personReader,
            @Qualifier("csvQuestionReader") QuestionReader questionReader,
            @Qualifier("consoleAnswerReader") AnswerReader answerReader
    ) {
        this.personReader = personReader;
        this.questionReader = questionReader;
        this.answerReader = answerReader;
    }

    @Override
    public Person readPerson() {
        return personReader.readPerson();
    }

    @Override
    public List<Question> readQuestions() {
        return questionReader.readQuestions();
    }

    @Override
    public String readAnswer() {
        return answerReader.readAnswer();
    }
}
