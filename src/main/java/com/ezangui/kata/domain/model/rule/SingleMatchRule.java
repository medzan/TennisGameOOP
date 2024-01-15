package com.ezangui.kata.domain.model.rule;

import com.ezangui.kata.domain.model.Player;
import com.ezangui.kata.domain.model.ScoreBoard;
import com.ezangui.kata.domain.model.TennisScore;

import java.util.List;

/**
 * @author ZANGUI Elmehdi
 */
public class SingleMatchRule extends MatchRule {
    private final int NUMBER_OF_PLAYERS = 2;

    public void validatePlayers(List<Player> players) {
        if (players.size() != NUMBER_OF_PLAYERS) {
            throw new IllegalArgumentException("Invalid number of players ");
        }
    }

    @Override
    public boolean determineWinner(List<ScoreBoard.GamePlayerScore> scoreView) {
        return scoreView.stream()
                .map(ScoreBoard.GamePlayerScore::scorePoint)
                .anyMatch(TennisScore::winnerScore);
    }

}
