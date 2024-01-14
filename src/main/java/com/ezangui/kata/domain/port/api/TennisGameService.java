package com.ezangui.kata.domain.port.api;

import com.ezangui.kata.domain.model.TennisGame;
import com.ezangui.kata.domain.model.Player;
import com.ezangui.kata.domain.port.spi.MessageEvent;

import java.util.List;

/**
 * @author ZANGUI Elmehdi
 */
public interface TennisGameService {
    TennisGame createTennisGame(Player firstPlayer,
                                Player secondPlayer);

    TennisGame getGameById(String gameId);
    TennisGame awardPlayerNewPoint(TennisGame tennisGame, Player player);

    List<MessageEvent> getAllEvents(TennisGame tennisGame);

}
