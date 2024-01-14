package com.ezangui.kata;

import com.ezangui.kata.adapter.client.SimpleTennisGameClient;
import com.ezangui.kata.adapter.memory.GameMemoryStoreAdapter;
import com.ezangui.kata.application.TennisApplicationService;
import com.ezangui.kata.domain.port.api.TennisGameService;
import com.ezangui.kata.domain.port.spi.GameStorePort;
import com.ezangui.kata.domain.port.spi.MessageEvent;

import java.util.List;

/**
 * @author ZANGUI Elmehdi
 */
public class DemoApplication {
    /**
     * “Player A : 15 / Player B : 0”
     * “Player A : 15 / Player B : 15”
     * “Player A : 30 / Player B : 15”
     * “Player A : 30 / Player B : 30”
     * “Player A : 40 / Player B : 30”
     * “Player A wins the game
     *
     * @param args
     */
    public static void main(String[] args) {
        GameStorePort messagesStore = new GameMemoryStoreAdapter();
        TennisGameService gameService = new TennisApplicationService(messagesStore);

        SimpleTennisGameClient client = new SimpleTennisGameClient(gameService);
        String gameId = client.createGame("A", "B");
        List<MessageEvent> messages = client.runGame(gameId, "ABABAA");
        messages.forEach(System.out::println);
        // Alternative way to run the game
        // client.createAndRunAGame("A", "B", "ABABAA");

    }
}
