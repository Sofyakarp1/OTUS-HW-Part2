package group_2022_02.ru.otus.homework.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Book {
    private final long id;
    private final String name;
    private final int page;
    private final int author;
    private final int genre;
}
