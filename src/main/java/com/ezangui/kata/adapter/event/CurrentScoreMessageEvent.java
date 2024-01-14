package com.ezangui.kata.adapter.event;

import com.ezangui.kata.domain.model.view.PlayerScoreView;
import com.ezangui.kata.domain.port.spi.MessageEvent;

import java.util.List;

/**
 * @author ZANGUI Elmehdi
 */
public class CurrentScoreMessageEvent implements MessageEvent {
    private final String message;

    public CurrentScoreMessageEvent(List<PlayerScoreView> playerScoreViews) {
        PlayerScoreView firstPlayer = playerScoreViews.get(0);
        PlayerScoreView secondPlayer = playerScoreViews.get(1);

        this.message =  String.format("Player %s : %s / Player %s : %s", firstPlayer.player().name(), firstPlayer.scorePoint(),
               secondPlayer.player().name(), secondPlayer.scorePoint());

    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
