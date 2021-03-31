package ru.otus.tests.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест чтения csv-файла ")
@SpringBootTest
public class CsvReaderTest {

    @Autowired
    private CsvReader csvReader;

    @DisplayName(" должен возвращать вопросы на русском с локалью ru-RU")
    @Test
    void shouldReturnCsvFileRuWithLocaleRu() throws IOException {
        LocaleContextHolder.setLocale(Locale.forLanguageTag("ru-RU"));
        BufferedReader bufferedReader = new BufferedReader(csvReader.getReader());
        assertThat(bufferedReader.readLine().chars()
                .filter(Character::isLetter)
                .mapToObj(Character.UnicodeBlock::of)
                .allMatch(b -> b.equals(Character.UnicodeBlock.CYRILLIC))).isTrue();
    }

    @DisplayName(" должен возвращать вопросы на английском с локалью en-EN")
    @Test
    void shouldReturnCsvFileEnWithLocaleEn() throws IOException {
        LocaleContextHolder.setLocale(Locale.forLanguageTag("en-EN"));
        BufferedReader bufferedReader = new BufferedReader(csvReader.getReader());
        assertThat(bufferedReader.readLine().chars()
                .filter(Character::isLetter)
                .mapToObj(Character.UnicodeBlock::of)
                .allMatch(b -> b.equals(Character.UnicodeBlock.BASIC_LATIN))).isTrue();
    }
}
