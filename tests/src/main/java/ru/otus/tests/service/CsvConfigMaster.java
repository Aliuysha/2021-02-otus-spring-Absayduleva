package ru.otus.tests.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.tests.domain.Question;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class CsvConfigMaster {
    @Value("classpath:/questions.csv")
    private InputStream csvIn;

    public CsvToBean<Question> getQuestions() {
        return new CsvToBeanBuilder<Question>(new BufferedReader(new InputStreamReader(csvIn)))
                .withType(Question.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSeparator(';')
                .build();
    }
}
