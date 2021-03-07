package ru.otus.test.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.tests.domain.Question;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс Question")
public class QuestionTest {
    @DisplayName("корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        Question question = new Question(1,"I really _ your dress.", "like");

        assertEquals(1, question.getId());
        assertEquals("I really _ your dress.", question.getText());
        assertEquals("like", question.getAnswer());
    }
}
