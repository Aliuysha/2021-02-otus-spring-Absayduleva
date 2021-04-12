package ru.otus.tests.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;
import ru.otus.tests.service.TestCreator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@DisplayName("Тест команд shell ")
@SpringBootTest
public class ShellCommandsTest {

    @MockBean
    private TestCreator testCreator;

    @Autowired
    private Shell shell;

    private static final String GREETING_PATTERN = "Добро пожаловать: %s";
    private static final String END_TEST = "Тест закончен";
    private static final String DEFAULT_LOGIN = "AnyUser";
    private static final String CUSTOM_LOGIN = "Вася";
    private static final String COMMAND_LOGIN = "login";
    private static final String COMMAND_LOGIN_SHORT = "l";
    private static final String COMMAND_LOGIN_PATTERN = "%s %s";
    private static final String COMMAND_TEST = "test";
    private static final String COMMAND_TEST_SHORT = "t";


    @DisplayName(" должен возвращать приветствие для всех форм команды логина")
    @Test
    void shouldReturnExpectedGreetingAfterLoginCommandEvaluated() {
        String res = (String) shell.evaluate(() -> COMMAND_LOGIN);
        assertThat(res).isEqualTo(String.format(GREETING_PATTERN, DEFAULT_LOGIN));

        res = (String) shell.evaluate(() -> COMMAND_LOGIN_SHORT);
        assertThat(res).isEqualTo(String.format(GREETING_PATTERN, DEFAULT_LOGIN));

        res = (String) shell.evaluate(() -> String.format(COMMAND_LOGIN_PATTERN, COMMAND_LOGIN_SHORT, CUSTOM_LOGIN));
        assertThat(res).isEqualTo(String.format(GREETING_PATTERN, CUSTOM_LOGIN));
    }

    @DisplayName(" должен вернуть конец теста после прохождения теста")
    @Test
    void shouldReturnEndOfTestAfterTestCommand() {
        String res = (String) shell.evaluate(() -> COMMAND_TEST);
        assertThat(res).isEqualTo(END_TEST);

        res = (String) shell.evaluate(() -> COMMAND_TEST_SHORT);
        assertThat(res).isEqualTo(END_TEST);
    }

    @DisplayName(" должен вернуть вопросы после для команды логина")
    @Test
    void shouldReturnQuestionsAfterTestCommand() {
        shell.evaluate(() -> COMMAND_TEST);
        verify(testCreator).test();
    }
}
