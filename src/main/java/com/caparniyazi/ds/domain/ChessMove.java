package com.caparniyazi.ds.domain;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class ChessMove {
    private Integer moveNumber;
    private MoveColor color;
    private String move;
}
