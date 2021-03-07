package ru.otus.tests;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.tests.service.QuestionReader;
import ru.otus.tests.service.QuestionWriter;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionReader reader = context.getBean(QuestionReader.class);
        QuestionWriter writer = context.getBean(QuestionWriter.class);
        writer.writeQuestions(reader.readQuestions());
    }
}
