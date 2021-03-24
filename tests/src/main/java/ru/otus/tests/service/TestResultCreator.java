package ru.otus.tests.service;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.otus.tests.domain.TestResult;

import java.util.List;

@ConfigurationProperties(prefix = "test")
@Configuration
public class TestResultCreator {
    private int success;

    public void setSuccess(int success) {
        this.success = success;
    }

    public TestResult getResult(List<String> answers, List<String> rightAnswers) {
        int rightCount = 0;
        for (int i = 0; i < rightAnswers.size(); i++) {
            if (answers.get(i).equals(rightAnswers.get(i))) {
                rightCount++;
            }
        }
        if (rightCount >= success) {
            return TestResult.SUCCESSFUL;
        } else {
            return TestResult.FAIL;
        }
    }
}
