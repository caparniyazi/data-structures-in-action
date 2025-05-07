package com.caparniyazi.ds.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * An entity class.
 */
@Data
@Table(name = "chess_move")
public class ChessMove {
    @Id
    private Long id;
    private Integer moveNumber;
    private MoveColor color;
    private String move;
}
