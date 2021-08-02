package spring.ru.otus.library.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Data
@Document(collection = "genres")
public class Genre {
    @Id
    private String id;

    @Field("name")
    private String name;

    public Genre(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}
