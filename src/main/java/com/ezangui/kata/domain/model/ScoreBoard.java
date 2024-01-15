package com.ezangui.kata.domain.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Administer and store scores for each player,
 * adhering to various tennis rules involving advantage and deuce scenarios.
 *
 * @author ZANGUI Elmehdi
 */
public class ScoreBoard {
    public record GamePlayerScore(Player player, TennisScore scorePoint) { }
    //linkedHashMap To preserve the orders of the players
    private final Map<Player, TennisScore> playerScoreMap = new LinkedHashMap<>();

    public ScoreBoard(List<Player> players) {
        players.forEach(player -> playerScoreMap.put(player, TennisScore.initScore()));
    }

    void scorePoint(Player player) {
        validCurrentRound(player);

        if (doesOpponentHaveAdvantage(player)) {
            resetScoresToDeuceForAllPlayers();
        } else {
            playerScoreMap.computeIfPresent(player,
                    (p, score) -> gameInDeuce() ? score.scoreAdvantagePoint() : score.scoreRegularPoint());
        }
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
        return playerScoreMap.entrySet().stream().allMatch(e -> e.getValue().hasMaxRegularPoints());
    }

    private void resetScoresToDeuceForAllPlayers() {
        playerScoreMap.replaceAll((player, score) -> score.deuce());
    }

    private boolean doesOpponentHaveAdvantage(Player player) {
        return playerScoreMap.entrySet()
                .stream()
                .anyMatch(e ->
                        !e.getKey().equals(player) && e.getValue().hasAdvantage());
    }

    List<GamePlayerScore> getCurrentScore() {
        return playerScoreMap.entrySet()
                .stream()
                .map(e -> new GamePlayerScore(new Player(e.getKey().name()), TennisScore.clone(e.getValue())))
                .toList();
    }


}
