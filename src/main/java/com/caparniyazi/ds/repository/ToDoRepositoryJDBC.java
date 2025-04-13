package com.caparniyazi.ds.repository;

import com.caparniyazi.ds.domain.ToDo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//@Repository
@RequiredArgsConstructor
public class ToDoRepositoryJDBC implements CommonRepository<ToDo> {
    //Data fields
    private static final String SQL_QUERY_FIND_ALL = "select id, description, created, modified, completed from todo";
    private static final String SQL_QUERY_FIND_BY_ID = SQL_QUERY_FIND_ALL + " where id = :id";
    private static final String SQL_INSERT
            = "insert into todo(id, description, created, modified, completed) values(:id,:description,:created,:modified,:completed)";
    private static final String SQL_UPDATE
            = "update todo set description = :description, modified = :modified, completed = :completed where id = :id";
    private static final String SQL_DELETE = "delete from todo where id = :id";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final RowMapper<ToDo> rowMapper = (ResultSet rs, int rowNum) -> {
        ToDo todo = new ToDo();
        todo.setId(rs.getString("id"));
        todo.setDescription(rs.getString("description"));
        todo.setModified(rs.getTimestamp("modified").toLocalDateTime());
        todo.setCreated(rs.getTimestamp("created").toLocalDateTime());
        todo.setCompleted(rs.getBoolean("completed"));

        return todo;
    };

    @Override
    public ToDo save(final ToDo domain) {
        ToDo result = findById(domain.getId());

        if (result != null) {
            result.setDescription(domain.getDescription());
            result.setModified(domain.getModified());
            result.setCompleted(domain.isCompleted());

            return upsert(result, SQL_UPDATE);
        }

        return upsert(domain, SQL_INSERT);
    }

    private ToDo upsert(final ToDo toDo, final String sql) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", toDo.getId());
        params.put("description", toDo.getDescription());
        params.put("modified", Timestamp.valueOf(toDo.getModified()));
        params.put("created", Timestamp.valueOf(toDo.getCreated()));
        params.put("completed", toDo.isCompleted());
        jdbcTemplate.update(sql, params);

        return findById(toDo.getId());
    }

    @Override
    public Iterable<ToDo> save(Collection<ToDo> domains) {
        domains.forEach(this::save);
        return findAll();
    }

    @Override
    public void delete(ToDo domain) {
        Map<String, String> params = Collections.singletonMap("id", domain.getId());
        jdbcTemplate.update(SQL_DELETE, params);
    }

    @Override
    public ToDo findById(String id) {
        Map<String, String> params = Collections.singletonMap("id", id);

        try {
            return jdbcTemplate.queryForObject(SQL_QUERY_FIND_BY_ID, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Iterable<ToDo> findAll() {
        return jdbcTemplate.query(SQL_QUERY_FIND_ALL, rowMapper);
    }
}
