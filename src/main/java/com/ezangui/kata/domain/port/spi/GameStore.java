package com.ezangui.kata.domain.port.spi;

import com.ezangui.kata.domain.model.TennisGame;
import com.ezangui.kata.domain.model.update.ScoreUpdate;

import java.util.List;

/**
 * @author ZANGUI Elmehdi
 */
public interface GameStore {
    void addUpdate(TennisGame tennisGame, ScoreUpdate update);

    List<ScoreUpdate> getUpdates(TennisGame tennisGame);

    void createGame(TennisGame tennisGame);

    TennisGame getGameById(String gameId);
}
