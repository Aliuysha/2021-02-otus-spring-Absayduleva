package ru.otus.tests.config;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;
import ru.otus.tests.domain.Question;
import ru.otus.tests.service.CsvReader;

@Component
public class CsvConfigMaster {
    private final CsvReader csvReader;

    public CsvConfigMaster(CsvReader csvReader) {
        this.csvReader = csvReader;
    }

    public CsvToBean<Question> getQuestions() {
        return new CsvToBeanBuilder<Question>(csvReader.getReader())
                .withType(Question.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSeparator(';')
                .build();
    }
}
