package spring.ru.otus.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDto {
    private UUID id;
    private String name;
    private List<AuthorDto> authors;
    private List<GenreDto> genres;
    private List<CommentDto> comments;
}
