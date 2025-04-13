package com.caparniyazi.ds.repository;

import com.caparniyazi.ds.domain.ToDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepositoryJPA extends CrudRepository<ToDo, String> {
}
