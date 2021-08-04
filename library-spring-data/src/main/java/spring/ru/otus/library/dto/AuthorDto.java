package spring.ru.otus.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthorDto {
    private UUID id;
    private String name;
}
