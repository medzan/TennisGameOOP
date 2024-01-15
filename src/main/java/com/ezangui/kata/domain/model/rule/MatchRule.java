package com.ezangui.kata.domain.model.rule;

import com.ezangui.kata.domain.model.Player;
import com.ezangui.kata.domain.model.ScoreBoard;


import java.util.List;

/**
 * @author ZANGUI Elmehdi
 */
public abstract class MatchRule {
    public abstract void validatePlayers(List<Player> game);

    public abstract boolean determineWinner(List<ScoreBoard.GamePlayerScore> scorePoint);
}
