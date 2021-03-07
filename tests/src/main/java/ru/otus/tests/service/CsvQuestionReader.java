package ru.otus.tests.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import ru.otus.tests.domain.Question;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

public class CsvQuestionReader implements QuestionReader {

    private final InputStream csvIn;

    public CsvQuestionReader(InputStream csvIn) {
        this.csvIn = csvIn;
    }

    public List<Question> readQuestions() {

        try (Reader reader = new BufferedReader(new InputStreamReader(csvIn))) {
            CsvToBean<Question> questions =
                    new CsvToBeanBuilder<Question>(reader)
                            .withType(Question.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .withSeparator(';')
                            .build();
            return questions.parse();
        } catch (Exception ignore) {
        }
        return Collections.emptyList();
    }
}
