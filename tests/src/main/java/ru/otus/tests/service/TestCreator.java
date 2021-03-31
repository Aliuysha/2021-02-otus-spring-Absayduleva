package ru.otus.tests.service;

import org.springframework.stereotype.Service;
import ru.otus.tests.domain.Person;
import ru.otus.tests.domain.Question;
import ru.otus.tests.domain.TestResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestCreator {
    private final TestReader testReader;
    private final TestWriter testWriter;
    private final TestResultCreator testResultCreator;

    public TestCreator(
            TestReader testReader,
            TestWriter testWriter,
            TestResultCreator testResultCreator
    ) {
        this.testReader = testReader;
        this.testWriter = testWriter;
        this.testResultCreator = testResultCreator;
    }

    public void test() {
        Person person = testReader.readPerson();
        List<Question> questions = testReader.readQuestions();
        List<String> answers = new ArrayList<>();
        for (Question question : questions) {
            testWriter.writeQuestion(question);
            answers.add(testReader.readAnswer());
        }
        List<String> rightAnswers = questions.stream()
                .map(Question::getAnswer).collect(Collectors.toList());
        TestResult testResult = testResultCreator.getResult(answers, rightAnswers);
        testWriter.printResult(testResult);
    }
}
