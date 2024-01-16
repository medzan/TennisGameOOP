package com.ezangui.kata.infrastructure.adapter.memory;

import com.ezangui.kata.domain.model.TennisGame;
import com.ezangui.kata.domain.model.update.GameUpdate;
import com.ezangui.kata.domain.port.spi.GameStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZANGUI Elmehdi
 */
public class GameMemoryStoreAdapter implements GameStore {
    Map<String, List<GameUpdate>> messagesPerGame = new HashMap<>();
    List<TennisGame> games = new ArrayList<>();

    @Override
    public void addMessage(TennisGame tennisGame, GameUpdate message) {
        messagesPerGame.compute(tennisGame.getId(), (k, v) -> {
            if (v == null) {
                v = new ArrayList<>();
            }
            v.add(message);
            return v;
        });
    }

    @Override
    public List<GameUpdate> getMessages(TennisGame tennisGame) {
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
                .orElseThrow((() -> new IllegalArgumentException("Game " + gameId + " not found")));
    }

}
