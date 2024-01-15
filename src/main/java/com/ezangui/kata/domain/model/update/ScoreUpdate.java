package com.ezangui.kata.domain.model.update;


import com.ezangui.kata.domain.model.ScoreBoard;

import java.util.List;

/**
 * @author ZANGUI Elmehdi
 */
public class ScoreUpdate extends GameUpdate<String> {
    private final String currentScore;

    public ScoreUpdate(List<ScoreBoard.GamePlayerScore> playerScoreViews) {
        ScoreBoard.GamePlayerScore firstPlayer = playerScoreViews.get(0);
        ScoreBoard.GamePlayerScore secondPlayer = playerScoreViews.get(1);

        this.currentScore = String.format("Player %s : %s / Player %s : %s",
                firstPlayer.player().name(), firstPlayer.scorePoint(),
                secondPlayer.player().name(), secondPlayer.scorePoint());

    }

    @Override
    public String getUpdate() {
        return currentScore;
    }

    @Override
    public String toString() {
        return getUpdate();
    }
}
