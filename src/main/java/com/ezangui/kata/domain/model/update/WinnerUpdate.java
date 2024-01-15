package com.ezangui.kata.domain.model.update;

import com.ezangui.kata.domain.model.Player;

/**
 * @author ZANGUI Elmehdi
 */
public class WinnerUpdate extends GameUpdate<String> {
    private final String message;

    public WinnerUpdate(Player winner) {
        this.message = String.format("Player %s wins the game", winner.name());
    }

    @Override
    public String getUpdate() {
        return message;
    }

    @Override
    public String toString() {
        return getUpdate();
    }
}
