package group_2022_02.ru.otus.homework.dao;

import group_2022_02.ru.otus.homework.domain.Author;
import group_2022_02.ru.otus.homework.domain.Genre;

import java.util.List;

public interface AuthorDao {

    Author getById(long id);

    List<Author> getAll();

    int count();

    void insert(Author author);

    void deleteById(long id);
}
