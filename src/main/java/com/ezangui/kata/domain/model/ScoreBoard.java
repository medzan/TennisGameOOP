package com.ezangui.kata.domain.model;

import com.ezangui.kata.domain.model.view.PlayerScoreView;

import java.util.*;

/**
 Administer and store scores for each player,
 adhering to various tennis rules involving advantage and deuce scenarios.
 * @author ZANGUI Elmehdi
 */
public class ScoreBoard {
    //linkedHashMap To preserve the orders of the players
    private final Map<Player, TennisScore> playerScoreMap = new LinkedHashMap<>();

    public ScoreBoard(Player firstPlayer, Player secondPlayer) {
        playerScoreMap.put(firstPlayer, TennisScore.initScore());
        playerScoreMap.put(secondPlayer, TennisScore.initScore());

    }

    TennisScore winPointForPlayer(Player player) {
        validCurrentRound(player);

        if (doesOpponentHaveAdvantage(player)) {
            resetScoresToDeuceForAllPlayers();
        }else {
            playerScoreMap.computeIfPresent(player,
                    (p, score) -> gameInDeuce() ? score.scoreAdvantagePoint() : score.scoreRegularPoint());
        }
        return TennisScore.clone(playerScoreMap.get(player));
    }

    private void validCurrentRound(Player player) {
        if (!playerScoreMap.containsKey(player)) {
            throw new IllegalStateException("Player Do not exist");
        }
        if (playerScoreMap.get(player).winnerScore()) {
            throw new IllegalStateException("Player is a winner, can't win a new point");
        }
    }
    private boolean gameInDeuce() {
        return playerScoreMap.entrySet().stream().allMatch(e -> e.getValue().hasMaxPoints());
    }

    private void resetScoresToDeuceForAllPlayers() {
        for (var p : playerScoreMap.entrySet()) {
            p.getValue().toDeuce();
        }
    }

    private boolean doesOpponentHaveAdvantage(Player player) {
        return playerScoreMap.entrySet()
                .stream()
                .anyMatch(e ->
                !e.getKey().equals(player) && e.getValue().hasAdvantage());
    }

    List<PlayerScoreView> getCurrentScore() {
        return playerScoreMap.entrySet()
                .stream()
                .map(e -> new PlayerScoreView(e.getKey(), e.getValue()))
                .toList();
    }


}
