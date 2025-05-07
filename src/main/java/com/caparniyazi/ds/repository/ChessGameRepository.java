package com.caparniyazi.ds.repository;

import com.caparniyazi.ds.domain.ChessGame;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Like with all other Spring Data modules, you should define a repository for each aggregate
 * and not for each entity class.
 */
@Repository
public interface ChessGameRepository extends CrudRepository<ChessGame, Long> {
    /*
     Like Spring Data JPA, Spring Data JDBC generates a query based on the method name.
     It generates a query statement that selects all records in the chess_game table
     with a matching name for playerBlack.
     */
    List<ChessGame> findByPlayerBlack(String playerBlack);
}
