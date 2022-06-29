package group_2022_02.ru.otus.homework.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Genre {
    private final long id;
    private final String name;
    private final String description;
}
