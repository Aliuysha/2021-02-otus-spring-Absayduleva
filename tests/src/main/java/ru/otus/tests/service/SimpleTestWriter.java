package ru.otus.tests.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.otus.tests.domain.Question;
import ru.otus.tests.domain.TestResult;

@Service
public class SimpleTestWriter implements TestWriter {
    private final QuestionWriter questionWriter;
    private final TestResultWriter testResultWriter;

    public SimpleTestWriter(
            @Qualifier("consoleQuestionWriter") QuestionWriter questionWriter,
            @Qualifier("consoleTestResultWriter") TestResultWriter testResultWriter
    ) {
        this.questionWriter = questionWriter;
        this.testResultWriter = testResultWriter;
    }

    @Override
    public void writeQuestion(Question question) {
        questionWriter.writeQuestion(question);
    }

    @Override
    public void printResult(TestResult testResult) {
        testResultWriter.printResult(testResult);
    }
}
