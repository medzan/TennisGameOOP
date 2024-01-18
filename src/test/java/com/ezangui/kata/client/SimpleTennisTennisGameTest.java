package com.ezangui.kata.client;

import com.ezangui.kata.infrastructure.adapter.client.SimpleTennisGameClient;
import com.ezangui.kata.infrastructure.adapter.memory.GameMemoryStoreAdapter;
import com.ezangui.kata.application.TennisApplicationService;
import com.ezangui.kata.domain.model.update.ScoreUpdate;
import com.ezangui.kata.domain.port.api.TennisGameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class SimpleTennisTennisGameTest {
    @Test
    public void whenNoDeuce_playerScoresWinningPoint() {
        List<String> expected = Stream.of("Player A : 15 / Player B : 0",
                "Player A : 15 / Player B : 15",
                "Player A : 30 / Player B : 15",
                "Player A : 30 / Player B : 30",
                "Player A : 40 / Player B : 30",
                "Player A wins the game"
        ).toList();

        GameMemoryStoreAdapter gameMessageMemoryStore = new GameMemoryStoreAdapter();
        TennisGameService service = new TennisApplicationService(gameMessageMemoryStore);
        SimpleTennisGameClient consoleInputTennisGame = new SimpleTennisGameClient(service);

        List<ScoreUpdate> actualResults = consoleInputTennisGame.createAndRunAGame("A", "B", "ABABAA");

        Assertions.assertEquals(expected.size(), actualResults.size());

        for (int i = 0; i < expected.size(); i++) {
            Assertions.assertEquals(expected.get(i), actualResults.get(i).toString());
        }

    }

    @Test
    public void whenGameInDeuce_playerGainsAdvantage() {
        List<String> expected = Stream.of("Player A : 15 / Player B : 0",
                "Player A : 15 / Player B : 15",
                "Player A : 30 / Player B : 15",
                "Player A : 30 / Player B : 30",
                "Player A : 40 / Player B : 30",
                "Player A : 40 / Player B : 40", // Deuce
                "Player A : 40 / Player B : A", // Player B get advantage
                "Player B wins the game"
        ).toList();
        GameMemoryStoreAdapter gameMessageMemoryStore = new GameMemoryStoreAdapter();

        TennisGameService service = new TennisApplicationService(gameMessageMemoryStore);
        SimpleTennisGameClient consoleInputTennisGame = new SimpleTennisGameClient(service);
        List<ScoreUpdate> actualResults = consoleInputTennisGame.createAndRunAGame("A", "B", "ABABABBB");

        Assertions.assertEquals(expected.size(), actualResults.size());

        for (int i = 0; i < expected.size(); i++) {
            Assertions.assertEquals(expected.get(i), actualResults.get(i).toString());
        }

    }

    @Test
    public void whenAdvantagedPlayerLoses_resetToDeuceForAllPlayers() {
        List<String> expected = Stream.of("Player A : 15 / Player B : 0",
                "Player A : 15 / Player B : 15",
                "Player A : 30 / Player B : 15",
                "Player A : 30 / Player B : 30",
                "Player A : 40 / Player B : 30",
                "Player A : 40 / Player B : 40", // Deuce
                "Player A : 40 / Player B : A", // Player B get advantage
                "Player A : 40 / Player B : 40", // Player A win -> Player B loses the advantage -> back to deuce
                "Player A : A / Player B : 40", // Player A win Advantage
                "Player A wins the game"
        ).toList();

        GameMemoryStoreAdapter gameMessageMemoryStore = new GameMemoryStoreAdapter();

        TennisGameService service = new TennisApplicationService(gameMessageMemoryStore);
        SimpleTennisGameClient consoleInputTennisGame = new SimpleTennisGameClient(service);
        List<ScoreUpdate> actualResults = consoleInputTennisGame.createAndRunAGame("A", "B", "ABABABBAAA");

        Assertions.assertEquals(expected.size(), actualResults.size());

        for (int i = 0; i < expected.size(); i++) {
            Assertions.assertEquals(expected.get(i), actualResults.get(i).toString());
        }

    }
}