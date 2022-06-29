package group_2022_02.ru.otus.homework.repository;

import group_2022_02.ru.otus.homework.dao.BookDao;
import group_2022_02.ru.otus.homework.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Dao для работы с книгами должно")
@JdbcTest
@Import(BookDaoJdbc.class)
public class BookDaoJdbcTest {

    @Autowired
    private BookDao book;

    @BeforeTransaction
    void beforeTransaction(){
        System.out.println("beforeTransaction");
    }

    @AfterTransaction
    void afterTransaction(){
        System.out.println("afterTransaction");
    }

    @DisplayName("возвращать ожидаемое количество книг в БД")
    @Test
    void shouldReturnExpectedBookCount() {
        int actualBooksCount = book.count();
        assertThat(actualBooksCount).isEqualTo(1);
    }

    @DisplayName("возвращать ожидаемую книгу")
    @Test
    void shouldReturnExpectedBook(){
        Book expectedBook = new Book(1, "It", 564, 1, 3);
        assertThat(book.getById(1)).isEqualTo(expectedBook);
    }

    @DisplayName("удалять книгу")
    @Test
    void shouldDeleteBook(){
        book.deleteById(1);
        Exception exception = assertThrows(Exception.class, () -> {
            book.getById(1);
        });
        String expectedMessage = "Incorrect result size: expected 1, actual 0";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @DisplayName("добавлять книгу")
    @Test
    void shouldAddBook(){
        Book newBook = new Book(2, "Pentagram", 434, 2, 2);
        book.insert(newBook);
        assertEquals(book.getById(2), newBook);
    }
}
