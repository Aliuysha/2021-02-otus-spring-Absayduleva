package ru.otus.tests.service;

import com.opencsv.bean.CsvToBean;
import org.springframework.stereotype.Component;
import ru.otus.tests.config.CsvConfigMaster;
import ru.otus.tests.domain.Question;

import java.util.Collections;
import java.util.List;

@Component
public class CsvQuestionReader implements QuestionReader {

    private final CsvConfigMaster csvConfigMaster;

    public CsvQuestionReader(CsvConfigMaster csvConfigMaster) {
        this.csvConfigMaster = csvConfigMaster;
    }

    @Override
    public List<Question> readQuestions() {
        CsvToBean<Question> questions = csvConfigMaster.getQuestions();
        if (questions != null) {
            return questions.parse();
        }
        return Collections.emptyList();
    }
}
