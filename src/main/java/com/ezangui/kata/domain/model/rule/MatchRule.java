package com.ezangui.kata.domain.model.rule;

import com.ezangui.kata.domain.model.PlayerScore;
import com.ezangui.kata.domain.model.Player;


import java.util.List;

/**
 * @author ZANGUI Elmehdi
 */
public abstract class MatchRule {
    public abstract void validatePlayers(List<Player> players);

    public abstract boolean determineWinner(PlayerScore scorePoint);
}
