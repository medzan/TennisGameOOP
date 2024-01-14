package com.ezangui.kata.adapter.event;

import com.ezangui.kata.domain.model.Player;
import com.ezangui.kata.domain.port.spi.MessageEvent;

/**
 * @author ZANGUI Elmehdi
 */
public class WinnerEventMessage implements MessageEvent {
    private final String message;
    public WinnerEventMessage(Player winner) {
        this.message = String.format("Player %s wins the game", winner.name());
    }
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
