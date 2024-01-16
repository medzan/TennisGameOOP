package com.ezangui.kata.domain.model.update;

import com.ezangui.kata.domain.model.Player;

/**
 * @author ZANGUI Elmehdi
 */
public class WinnerUpdate extends GameUpdate {
    private final String message;

    private WinnerUpdate(Player winner) {
        this.message = String.format("Player %s wins the game", winner.name());
    }
    public static GameUpdate create(Player player) {
        return new WinnerUpdate(player);
    }
    @Override
    public String getTextUpdate() {
        return message;
    }

    @Override
    public String toString() {
        return getTextUpdate();
    }
}
