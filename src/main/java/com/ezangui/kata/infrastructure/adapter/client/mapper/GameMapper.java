package com.ezangui.kata.infrastructure.adapter.client.mapper;

import com.ezangui.kata.domain.model.Player;

/**
 * @author ZANGUI Elmehdi
 */
public class GameMapper {
    public static Player toPlayerDomainModel(String playerName) {
        return new Player(playerName);
    }

}
