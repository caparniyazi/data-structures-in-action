package com.caparniyazi.ds.controller;

import com.caparniyazi.ds.domain.ChessGame;
import com.caparniyazi.ds.domain.ChessMove;
import com.caparniyazi.ds.domain.MoveColor;
import com.caparniyazi.ds.repository.ChessGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ChessGameController {
    // Data fields
    private final ChessGameRepository repository;

    @RequestMapping(value = "/chess-game", method = {RequestMethod.POST})
    public void createChessGame() {
        ChessGame game = new ChessGame();
        game.setPlayerWhite("Niyazi ÇAPAR");
        game.setPlayerBlack("Other Player");

        ChessMove move1White = new ChessMove();
        move1White.setId(1L);
        move1White.setMoveNumber(1);
        move1White.setColor(MoveColor.WHITE);
        move1White.setMove("e4");
        game.getMoves().add(move1White);

        ChessMove move1Black = new ChessMove();
        move1Black.setId(2L);
        move1Black.setMoveNumber(1);
        move1Black.setColor(MoveColor.BLACK);
        move1Black.setMove("e5");
        game.getMoves().add(move1Black);

        repository.save(game);
    }
}
