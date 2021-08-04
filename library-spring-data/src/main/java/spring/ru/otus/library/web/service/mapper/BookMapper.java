package spring.ru.otus.library.web.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import spring.ru.otus.library.domain.Author;
import spring.ru.otus.library.domain.Book;
import spring.ru.otus.library.domain.Genre;
import spring.ru.otus.library.dto.AuthorDto;
import spring.ru.otus.library.dto.BookDto;
import spring.ru.otus.library.dto.GenreDto;

import java.util.List;


@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface BookMapper {

    List<BookDto> convert(List<Book> books);

    List<AuthorDto> convertAuthors(List<Author> authors);

    List<GenreDto> convertGenres(List<Genre> genres);

    BookDto convert(Book book);

    Book convert(BookDto book);

    AuthorDto convert(Author author);

    GenreDto convert(Genre genre);

}
