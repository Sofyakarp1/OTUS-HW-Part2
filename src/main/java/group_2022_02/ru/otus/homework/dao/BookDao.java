package group_2022_02.ru.otus.homework.dao;

import group_2022_02.ru.otus.homework.domain.Book;

import java.util.List;

public interface BookDao {

    Book getById(long id);

    List<Book> getAll();

    int count();

    void insert(Book book);

    void deleteById(long id);

    Boolean checkId(long id);
}
