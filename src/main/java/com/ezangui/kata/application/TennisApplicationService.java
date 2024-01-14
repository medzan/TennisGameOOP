package com.ezangui.kata.application;

import com.ezangui.kata.adapter.event.CurrentScoreMessageEvent;
import com.ezangui.kata.adapter.event.WinnerEventMessage;
import com.ezangui.kata.domain.model.TennisGame;
import com.ezangui.kata.domain.model.Player;
import com.ezangui.kata.domain.port.api.TennisGameService;
import com.ezangui.kata.domain.port.spi.MessageEvent;
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
    public TennisGame createTennisGame(Player firstPlayer,
                                       Player secondPlayer) {
        TennisGame newTennisGame = new TennisGame(UUID.randomUUID().toString());
        newTennisGame.addPlayers(firstPlayer, secondPlayer);
        store.createGame(newTennisGame);
        return newTennisGame;
    }

    @Override
    public TennisGame getGameById(String gameId) {
        return store.getGameById(gameId);
    }

    @Override
    public TennisGame awardPlayerNewPoint(TennisGame tennisGame, Player currentPlayer) {
        MessageEvent messageEvent;

        tennisGame.scorePointForPlayer(currentPlayer);

        if (tennisGame.getWinner().isPresent()) {
            messageEvent = new WinnerEventMessage(tennisGame.getWinner().get());
        } else {
            messageEvent = new CurrentScoreMessageEvent(tennisGame.getCurrentScore());
        }
        store.addMessage(tennisGame, messageEvent);

        return tennisGame;

    }

    @Override
    public List<MessageEvent> getAllEvents(TennisGame tennisGame) {
        return store.getMessages(tennisGame);
    }

}
