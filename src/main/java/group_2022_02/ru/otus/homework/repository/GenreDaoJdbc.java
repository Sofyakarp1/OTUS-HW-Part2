package group_2022_02.ru.otus.homework.repository;

import group_2022_02.ru.otus.homework.dao.GenreDao;
import group_2022_02.ru.otus.homework.domain.Genre;
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
public class GenreDaoJdbc implements GenreDao {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Genre getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, name, description from genre where id = :id", params, new GenreMapper()
        );
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select id, name from genre", new GenreMapper());
    }

    @Override
    public int count() {
        Integer count = jdbc.queryForObject("select count(*) from genre", Integer.class);
        return count == null? 0: count;
    }

    @Override
    public void insert(Genre genre) {
        namedParameterJdbcOperations.update("insert into genre (id, name, description) values (:id, :name, :description)",
                Map.of("id", genre.getId(), "name", genre.getName(), "description", genre.getDescription()));
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from genre where id = :id", params
        );
    }

    @Override
    public Genre getByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        return namedParameterJdbcOperations.queryForObject(
                "select id, name, description from genre where name = :name", params, new GenreMapper()
        );
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            return new Genre(id, name, description);
        }
    }
}
