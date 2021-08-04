package spring.ru.otus.library.dto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommentConverter implements Converter<String, CommentDto> {

    @Override
    public CommentDto convert(String s) {
        return new CommentDto(UUID.randomUUID(), s);
    }
}
