package com.ezangui.kata.adapter.memory;

import com.ezangui.kata.domain.model.TennisGame;
import com.ezangui.kata.domain.port.spi.GameStorePort;
import com.ezangui.kata.domain.port.spi.MessageEvent;

import java.util.*;

/**
 * @author ZANGUI Elmehdi
 */
public class GameMemoryStoreAdapter implements GameStorePort {
    Map<String, List<MessageEvent>> messagesPerGame = new HashMap<>();
    List<TennisGame> games = new ArrayList<>();

    @Override
    public void addMessage(TennisGame tennisGame, MessageEvent message) {
        messagesPerGame.compute(tennisGame.getId(), (k, v) -> {
            if (v == null){
                v = new ArrayList<>();
            }
            v.add(message);
            return v;
        });
    }

    @Override
    public List<MessageEvent> getMessages(TennisGame tennisGame) {
        return messagesPerGame.get(tennisGame.getId());
    }

    @Override
    public void createGame(TennisGame tennisGame) {
        games.add(tennisGame);
    }

    @Override
    public TennisGame getGameById(String gameId) {
        return games.stream()
                .filter(g -> g.getId().equals(gameId))
                .findAny()
                .orElseThrow((() -> new IllegalArgumentException("Game " + gameId + "not found")));
    }

}
