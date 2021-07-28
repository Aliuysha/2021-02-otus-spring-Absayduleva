package spring.ru.otus.library.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "books")
@NoArgsConstructor
public class Book {
    @Transient
    public static final String SEQUENCE_NAME = "books_sequence";

    @Id
    private long id;

    @Field("name")
    private String name;

    @Field("authors")
    private List<Author> authors;

    @Field("genres")
    private List<Genre> genres;

    @Field("comments")
    private List<Comment> comments;
}
