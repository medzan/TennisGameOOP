package com.ezangui.kata.domain.port.api;

import com.ezangui.kata.domain.model.Player;
import com.ezangui.kata.domain.model.TennisGame;
import com.ezangui.kata.domain.model.update.GameUpdate;

import java.util.List;

/**
 * @author ZANGUI Elmehdi
 */
public interface TennisGameService {
    TennisGame createSingleMatchTennisGame(List<Player> players);

    TennisGame getGameById(String gameId);

    TennisGame awardPlayerNewPoint(TennisGame tennisGame, Player player);

    List<GameUpdate> getAllUpdates(TennisGame tennisGame);

}
