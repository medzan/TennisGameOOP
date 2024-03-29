package com.ezangui.kata.application;

import com.ezangui.kata.domain.model.Player;
import com.ezangui.kata.domain.model.TennisGame;
import com.ezangui.kata.domain.model.rule.MatchRule;
import com.ezangui.kata.domain.model.rule.SingleMatchRule;
import com.ezangui.kata.domain.model.update.ScoreUpdate;
import com.ezangui.kata.domain.port.api.TennisGameService;
import com.ezangui.kata.domain.port.spi.GameStore;

import java.util.List;
import java.util.UUID;

/**
 * @author ZANGUI Elmehdi
 */
public class TennisApplicationService implements TennisGameService {
    private final GameStore store;

    public TennisApplicationService(GameStore store) {
        this.store = store;
    }

    @Override
    public TennisGame createSingleMatchTennisGame(List<Player> players) {
        //Type of rule that could be loaded from a configuration service
        MatchRule matchRule = new SingleMatchRule();
        TennisGame newTennisGame = new TennisGame(UUID.randomUUID().toString(), matchRule);
        newTennisGame.addPlayers(players);
        store.createGame(newTennisGame);
        return newTennisGame;
    }

    @Override
    public TennisGame getGameById(String gameId) {
        return store.getGameById(gameId);
    }

    @Override
    public TennisGame awardPlayerNewPoint(TennisGame tennisGame, Player currentPlayer) {

        ScoreUpdate scoreUpdate = tennisGame.scorePointForPlayer(currentPlayer);

        store.addUpdate(tennisGame, scoreUpdate);

        return tennisGame;

    }

    @Override
    public List<ScoreUpdate> getAllUpdates(TennisGame tennisGame) {
        return store.getUpdates(tennisGame);
    }

}
