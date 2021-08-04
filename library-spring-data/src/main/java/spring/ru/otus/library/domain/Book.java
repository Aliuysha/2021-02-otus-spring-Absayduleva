package spring.ru.otus.library.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "books")
@NoArgsConstructor
public class Book {
    @Id
    private UUID id;

    @Field("name")
    private String name;

    @Field("authors")
    private List<Author> authors;

    @Field("genres")
    private List<Genre> genres;

    @Field("comments")
    private List<Comment> comments;
}
