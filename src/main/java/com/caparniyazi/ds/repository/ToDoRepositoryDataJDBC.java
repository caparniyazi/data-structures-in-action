package com.caparniyazi.ds.repository;

import com.caparniyazi.ds.domain.ToDo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

//@Repository
public interface ToDoRepositoryDataJDBC extends CrudRepository<ToDo, String> {
    List<ToDo> findByDescription(String description);

    /**
     * Inside the @Query annotation, we add our SQL command.
     * In Spring Data JDBC, we write queries in plain SQL.
     * We don’t use any higher-level query language like JPQL.
     * As a result, the application becomes tightly coupled with the database vendor.
     * <p/>
     * Spring Data JDBC does not support the referencing of parameters with index numbers.
     * We’re able only to reference parameters by name.
     *
     * @param id          The Primary Key
     * @param description The ToDo description
     * @return true/false
     */
    @Modifying
    @Query("update todo set description = :description where id = :id")
    boolean updateByDescription(@Param("id") String id, @Param("description") String description);
}
