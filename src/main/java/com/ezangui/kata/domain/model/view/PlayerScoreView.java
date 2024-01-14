package com.ezangui.kata.domain.model.view;

import com.ezangui.kata.domain.model.Player;
import com.ezangui.kata.domain.model.TennisScore;

/**
 * @author ZANGUI Elmehdi
 */
public record PlayerScoreView(Player player, TennisScore scorePoint) {
}
