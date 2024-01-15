package com.ezangui.kata.application;

import com.ezangui.kata.domain.model.Player;
import com.ezangui.kata.domain.model.TennisGame;
import com.ezangui.kata.domain.model.rule.MatchRule;
import com.ezangui.kata.domain.model.rule.SingleMatchRule;
import com.ezangui.kata.domain.model.update.GameUpdate;
import com.ezangui.kata.domain.model.update.ScoreUpdate;
import com.ezangui.kata.domain.model.update.WinnerUpdate;
import com.ezangui.kata.domain.port.api.TennisGameService;
import com.ezangui.kata.domain.port.spi.GameStorePort;

import java.util.List;
import java.util.UUID;

/**
 * @author ZANGUI Elmehdi
 */
public class TennisApplicationService implements TennisGameService {
    private final GameStorePort store;

    public TennisApplicationService(GameStorePort store) {
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
        GameUpdate gameUpdate;

        tennisGame.scorePointForPlayer(currentPlayer);

        if (tennisGame.HasPlayerWonTheGame(currentPlayer)) {
            gameUpdate =  new WinnerUpdate(currentPlayer);
        } else {
            gameUpdate = new ScoreUpdate(tennisGame.getCurrentScore());
        }
        store.addMessage(tennisGame, gameUpdate);

        return tennisGame;

    }

    @Override
    public List<GameUpdate> getAllUpdates(TennisGame tennisGame) {
        return store.getMessages(tennisGame);
    }

}
