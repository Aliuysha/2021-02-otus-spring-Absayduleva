package spring.ru.otus.library.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Data
@Document(collection = "comments")
@NoArgsConstructor
public class Comment {

    @Id
    private UUID id;

    @Field("text")
    private String text;
}
