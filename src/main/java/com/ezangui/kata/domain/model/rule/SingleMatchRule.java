package com.ezangui.kata.domain.model.rule;

import com.ezangui.kata.domain.model.PlayerScore;
import com.ezangui.kata.domain.model.Player;

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
    public boolean determineWinner(PlayerScore score) {
        return score.tennisScore().winnerScore();
    }

}
