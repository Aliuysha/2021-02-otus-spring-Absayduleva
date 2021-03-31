package ru.otus.tests.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

@ConfigurationProperties("input")
@Configuration
public class CsvConfig {
    private InputStream csvIn;
    private InputStream csvInRu;

    public InputStream getCsvIn() {
        return csvIn;
    }

    public void setCsvIn(InputStream csvIn) {
        this.csvIn = csvIn;
    }

    public InputStream getCsvInRu() {
        return csvInRu;
    }

    public void setCsvInRu(InputStream csvInRu) {
        this.csvInRu = csvInRu;
    }
}
