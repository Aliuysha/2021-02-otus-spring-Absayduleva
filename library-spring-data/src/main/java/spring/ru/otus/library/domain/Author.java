package spring.ru.otus.library.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Data
@Document(collection = "authors")
public class Author {

    @Id
    private String id;

    @Field("name")
    private String name;

    public Author(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}
