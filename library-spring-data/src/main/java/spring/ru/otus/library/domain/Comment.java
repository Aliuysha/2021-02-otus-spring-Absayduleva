package spring.ru.otus.library.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@Document(collection = "comments")
@NoArgsConstructor
public class Comment {
    @Transient
    public static final String SEQUENCE_NAME = "comments_sequence";

    @Id
    private long id;

    @Field("text")
    private String text;
}
