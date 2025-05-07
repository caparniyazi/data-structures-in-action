package com.caparniyazi.ds.service;

import com.caparniyazi.ds.domain.ChessGame;
import com.caparniyazi.ds.domain.ChessMove;
import com.caparniyazi.ds.domain.MoveColor;
import com.caparniyazi.ds.repository.ChessGameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ChessGameService {
//    private final ChessGameRepository repository;
//
//    public void createChessGame() {
//        ChessGame game = new ChessGame();
//        game.setPlayerWhite("Niyazi ÇAPAR");
//        game.setPlayerBlack("Other Player");
//
//        ChessMove move1White = new ChessMove();
//        move1White.setMoveNumber(1);
//        move1White.setColor(MoveColor.WHITE);
//        move1White.setMove("e4");
//        game.getMoves().add(move1White);
//
//        ChessMove move1Black = new ChessMove();
//        move1Black.setMoveNumber(1);
//        move1Black.setColor(MoveColor.BLACK);
//        move1Black.setMove("e5");
//        game.getMoves().add(move1Black);
//
//        repository.save(game);
//    }
}
