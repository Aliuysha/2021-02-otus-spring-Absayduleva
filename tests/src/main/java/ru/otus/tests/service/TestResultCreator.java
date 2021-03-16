package ru.otus.tests.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.otus.tests.domain.TestResult;

import java.util.List;

@Component
@PropertySource("classpath:application.properties")
public class TestResultCreator {

    @Value("${test.success}")
    private Integer testSuccess;

    public TestResult getResult(List<String> answers, List<String> rightAnswers) {
        int rightCount = 0;
        for (int i = 0; i < rightAnswers.size(); i++) {
            if (answers.get(i).equals(rightAnswers.get(i))) {
                rightCount++;
            }
        }
        if (rightCount >= testSuccess) {
            return TestResult.SUCCESSFUL;
        } else {
            return TestResult.FAIL;
        }
    }
}
