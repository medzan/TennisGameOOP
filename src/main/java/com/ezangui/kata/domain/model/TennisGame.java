package com.ezangui.kata.domain.model;

import com.ezangui.kata.domain.model.rule.MatchRule;

import java.util.List;

/**
 * Handle the game's workflow, tracking states and winners,
 * while efficiently managing player-specific updates through delegation to the scoreboard.
 *
 * @author ZANGUI Elmehdi
 */
public class TennisGame {
    private enum GameState {
        CREATED, IN_PROGRESS, FINISHED
    }
    private final String id;
    private final MatchRule matchRules;
    private GameState state;
    private ScoreBoard scoreboard;
    private Player winner = null;
    private List<Player> players;

    public TennisGame(String id, MatchRule matchRules) {
        this.id = id;
        this.matchRules = matchRules;
        this.state = GameState.CREATED;
    }

    public void addPlayers(List<Player> players) {
        if (state != GameState.CREATED) {
            throw new IllegalStateException("Players can only join a recently created game." +
                    " The current game is in the following state: " + state);
        }
        matchRules.validatePlayers(players);

        this.players = players;
        this.scoreboard = new ScoreBoard(players);
        this.state = GameState.IN_PROGRESS;
    }

    public void scorePointForPlayer(Player player) {
        if (state != GameState.IN_PROGRESS) {
            throw new IllegalStateException("Cannot Play any more the game is in the following state " + state);
        }
        if (player == null) {
            throw new IllegalStateException();
        }
        scoreboard.scorePoint(player);
        ScoreBoard.GamePlayerScore currentScore = scoreboard.getCurrentScore(player);
        if (matchRules.determineWinner(currentScore)) {
            winner = player;
            state = GameState.FINISHED;
        }
    }

    public boolean HasPlayerWonTheGame(Player player) {
        return state == GameState.FINISHED && winner.equals(player);
    }

    public String getId() {
        return id;
    }

    public boolean inProgress() {
        return state != GameState.FINISHED;
    }

    public List<ScoreBoard.GamePlayerScore> getCurrentScore() {
        return players.stream()
                .map(player -> scoreboard.getCurrentScore(player))
                .toList();
    }



}
