package ru.otus.tests.service;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import ru.otus.tests.config.CsvConfig;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Locale;

@Component
public class CsvReader {
    private final CsvConfig csvConfig;

    public CsvReader(CsvConfig csvConfig) {
        this.csvConfig = csvConfig;
    }

    public Reader getReader() {
        InputStream inputStream;
        Locale locale = LocaleContextHolder.getLocale();
        if (locale.equals(Locale.forLanguageTag("ru-RU"))) {
            inputStream = csvConfig.getCsvInRu();
        } else {
            inputStream = csvConfig.getCsvIn();
        }
        return new BufferedReader(new InputStreamReader(inputStream));
    }
}
