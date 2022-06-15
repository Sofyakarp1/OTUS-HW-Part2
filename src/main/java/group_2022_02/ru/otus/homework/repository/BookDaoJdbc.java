package group_2022_02.ru.otus.homework.repository;

import group_2022_02.ru.otus.homework.dao.BookDao;
import group_2022_02.ru.otus.homework.domain.Book;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, name, page, author, genre from book where id = :id", params, new BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select id, name, page, author, genre from book", new BookMapper());
    }

    @Override
    public int count() {
        Integer count = jdbc.queryForObject("select count(*) from book", Integer.class);
        return count == null? 0: count;
    }

    @Override
    public void insert(Book book) {
        namedParameterJdbcOperations.update("insert into book (id, name, page, author, genre) values (:id, :name, :page, :author, :genre)",
                Map.of("id", book.getId(), "name", book.getName(), "page", book.getPage(), "author", book.getAuthor(), "genre", book.getGenre()));
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from book where id = :id", params
        );
    }

    @Override
    public Boolean checkId(long id) {
        try {
            Map<String, Object> params = Collections.singletonMap("id", id);
            namedParameterJdbcOperations.queryForObject(
                    "select id, name, page, author, genre from book where id = :id", params, new BookMapper()
            );
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            int page_count = resultSet.getInt("page");
            int id_author = resultSet.getInt("author");
            int id_genre = resultSet.getInt("genre");
            return new Book(id, name, page_count, id_author, id_genre);
        }
    }
}
