package com.caparniyazi.ds.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ChessGame {
    @Id
    private Long id;
    private String playerWhite;
    private String playerBlack;
    private List<ChessMove> moves = new ArrayList<>();
}
