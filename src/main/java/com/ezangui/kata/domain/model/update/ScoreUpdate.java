package com.ezangui.kata.domain.model.update;


import com.ezangui.kata.domain.model.PlayerScore;

import java.util.List;

/**
 * @author ZANGUI Elmehdi
 */
public class ScoreUpdate extends GameUpdate {
    private final String currentScore;

    private ScoreUpdate(List<PlayerScore> playerScoreViews) {
        var firstPlayer = playerScoreViews.get(0);
        var secondPlayer = playerScoreViews.get(1);

        this.currentScore = String.format("Player %s : %s / Player %s : %s",
                firstPlayer.player().name(), firstPlayer.tennisScore(),
                secondPlayer.player().name(), secondPlayer.tennisScore());

    }

    public static GameUpdate create(List<PlayerScore> playerScoreViews) {
        return new ScoreUpdate(playerScoreViews);
    }
    @Override
    public String getTextUpdate() {
        return currentScore;
    }

    @Override
    public String toString() {
        return getTextUpdate();
    }
}
