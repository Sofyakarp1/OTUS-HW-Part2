package group_2022_02.ru.otus.homework.dao;

import group_2022_02.ru.otus.homework.domain.Genre;

import java.util.List;

public interface GenreDao {

    Genre getById(long id);

    List<Genre> getAll();

    int count();

    void insert(Genre genre);

    void deleteById(long id);

    Genre getByName(String name);
}
