package com.ezangui.kata.domain.port.spi;

import com.ezangui.kata.domain.model.TennisGame;
import com.ezangui.kata.domain.model.update.GameUpdate;

import java.util.List;

/**
 * @author ZANGUI Elmehdi
 */
public interface GameStore {
    void addMessage(TennisGame tennisGame, GameUpdate message);

    List<GameUpdate> getMessages(TennisGame tennisGame);

    void createGame(TennisGame tennisGame);

    TennisGame getGameById(String gameId);
}
