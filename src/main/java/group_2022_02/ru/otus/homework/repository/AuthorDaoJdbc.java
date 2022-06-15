package group_2022_02.ru.otus.homework.repository;

import group_2022_02.ru.otus.homework.dao.AuthorDao;
import group_2022_02.ru.otus.homework.domain.Author;
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
public class AuthorDaoJdbc implements AuthorDao {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }
    @Override
    public Author getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, name, genre, age from author where id = :id", params, new AuthorMapper()
        );
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select id, name, genre, age from author", new AuthorMapper());
    }

    @Override
    public int count() {
        Integer count = jdbc.queryForObject("select count(*) from author", Integer.class);
        return count == null? 0: count;
    }

    @Override
    public void insert(Author author) {
        namedParameterJdbcOperations.update("insert into author (id, name, genre, age) values (:id, :name, :genre, :age)",
                Map.of("id", author.getId(), "name", author.getName(), "genre", author.getGenre(), "age", author.getAge()));
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from author where id = :id", params
        );
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            int genre = resultSet.getInt("genre");
            int age = resultSet.getInt("age");
            return new Author(id, name, genre, age);
        }
    }
}
