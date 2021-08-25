package spring.ru.otus.library.dto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.ru.otus.library.domain.Author;
import spring.ru.otus.library.repositories.AuthorRepository;

@Component
public class AuthorConverter implements Converter<String, Author> {

    private final AuthorRepository authorRepository;

    public AuthorConverter(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author convert(String id) {
        return authorRepository.findById(id).block();
    }
}
