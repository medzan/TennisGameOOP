package com.ezangui.kata.domain.port.spi;

import com.ezangui.kata.domain.model.TennisGame;

import java.util.List;

/**
 * @author ZANGUI Elmehdi
 */
public interface GameStorePort {
    void addMessage(TennisGame tennisGame, MessageEvent message);

    List<MessageEvent> getMessages(TennisGame tennisGame);

    void createGame(TennisGame tennisGame);

    TennisGame getGameById(String gameId);
}
