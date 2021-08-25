package spring.ru.otus.library.dto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.ru.otus.library.domain.Comment;

@Component
public class CommentConverter implements Converter<String, Comment> {

    @Override
    public Comment convert(String text) {
        return new Comment(text);
    }
}
