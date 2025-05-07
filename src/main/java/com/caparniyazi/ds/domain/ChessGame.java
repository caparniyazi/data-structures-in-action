package com.caparniyazi.ds.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Aggregates are a group of entities.
 * And Entities in Spring Data JDBC are simple POJOs
 * hat only require an attribute with an @Id annotation to be identified as an entity class.
 */
@Data
@Table("chess_game")
public class ChessGame {    // The ChessGame entity class as the aggregate root and ChessMove entity class.
    /*
    Spring Data JDBC expects the primary key value to be managed by the database, e.g.,
    by an auto-incremented column and returned in response to the SQL INSERT statement.
     */
    @Id
    private Long id;
    private String playerWhite;
    private String playerBlack;

    // The mapping of the one-to-many association from ChessGame to the ChessMove entities.
    // In contrast to JPA, association mappings don’t require additional mapping annotations
    // in Spring Data JDBC.
    // That’s because it doesn’t support any bidirectional associations and
    // many-to-many associations.
    // A mapped association is always from the aggregate root to the dependent child entities,
    // and these can either be one-to-one or one-to-many associations.
    // A many-to-many association is always an association between 2 aggregates
    // and gets mapped via references.
    private List<ChessMove> moves = new ArrayList<>();
}
