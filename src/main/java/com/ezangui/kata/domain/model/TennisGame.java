package com.ezangui.kata.domain.model;

import com.ezangui.kata.domain.model.view.PlayerScoreView;

import java.util.List;
import java.util.Optional;

/**
 * Handle the game's workflow, tracking states and winners,
 * while efficiently managing player-specific updates through delegation to the scoreboard.
 *
 * @author ZANGUI Elmehdi
 */
public class TennisGame {
    private final String id;
    private GameState state;
    private Player winner = null;
    private ScoreBoard scoreboard;

    enum GameState {
        CREATED, IN_PROGRESS, FINISHED
    }

    public TennisGame(String id) {
        this.id = id;
        this.state = GameState.CREATED;
    }

    public void addPlayers(Player firstPlayer, Player secondPlayer) {
        if (state != GameState.CREATED) {
            throw new IllegalStateException("Players can only join a recently created game." +
                    " The current game is in the following state: " + state);
        }
        this.scoreboard = new ScoreBoard(firstPlayer, secondPlayer);
        this.state = GameState.IN_PROGRESS;

    }

    public TennisScore scorePointForPlayer(Player player) {
        if (state != GameState.IN_PROGRESS) {
            throw new IllegalStateException("Cannot Play any more the game is in the following state " + state);
        }
        if (player == null) {
            throw new IllegalStateException();
        }
        TennisScore scorePoint = scoreboard.winPointForPlayer(player);
        if (scorePoint.winnerScore()) {
            winner = player;
            state = GameState.FINISHED;
        }
        return scorePoint;
    }

    public Optional<Player> getWinner() {
        return Optional.ofNullable(winner);
    }
    public String getId() {
        return id;
    }
    public boolean inProgress() {
        return state != GameState.FINISHED;
    }
    public List<PlayerScoreView> getCurrentScore() {
            return  scoreboard.getCurrentScore();
    }

}
