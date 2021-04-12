package ru.otus.tests.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Scanner;

@Configuration
public class AppConfig {

    @Bean
    public ResourceBundleMessageSource messageSource() {
        var source = new ResourceBundleMessageSource();
        source.setBasenames("messages");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
