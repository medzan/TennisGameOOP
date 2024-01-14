package com.ezangui.kata.adapter.client;

import com.ezangui.kata.adapter.client.mapper.GameMapper;
import com.ezangui.kata.domain.model.TennisGame;
import com.ezangui.kata.domain.model.Player;
import com.ezangui.kata.domain.port.api.TennisGameService;
import com.ezangui.kata.domain.port.spi.MessageEvent;

import java.util.List;

/**
 Basic Client Example,
 Translate input to models and delegate execution to application services.
 This could serve as a controller, for rest app instance.

 * @author ZANGUI Elmehdi
 */
public class SimpleTennisGameClient {

    private final TennisGameService tennisGameService;

    public SimpleTennisGameClient(TennisGameService tennisGameService) {
        this.tennisGameService = tennisGameService;
    }

    public String createGame(String firstPlayerName, String secondPlayerName) {
        Player firstPlayer = GameMapper.toPlayerDomainModel(firstPlayerName);
        Player secondPlayer = GameMapper.toPlayerDomainModel(secondPlayerName);

        TennisGame tennisGame = tennisGameService.createTennisGame(firstPlayer, secondPlayer);
        return tennisGame.getId();
    }
    public List<MessageEvent> runGame(String gameId, String playerWinOrder) {
        TennisGame tennisGame = tennisGameService.getGameById(gameId);

        int currentRoundIndice = 0;

        while(tennisGame.inProgress() && currentRoundIndice < playerWinOrder.toCharArray().length){
            String playerName = String.valueOf(playerWinOrder.charAt(currentRoundIndice));
            Player player = GameMapper.toPlayerDomainModel(playerName);

            tennisGame = tennisGameService.awardPlayerNewPoint(tennisGame, player);
            currentRoundIndice++;
        }
        return tennisGameService.getAllEvents(tennisGame);
    }

    public List<MessageEvent> createAndRunAGame(String firstPlayerName, String secondPlayerName, String playerWinOrder) {
        String gameId = createGame(firstPlayerName, secondPlayerName);
        return runGame(gameId, playerWinOrder);
    }
}
