package com.ezangui.kata.domain.model;

import com.ezangui.kata.domain.model.view.PlayerScoreView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TennisGameTest {
    @Mock
    Player firstPlayer;
    @Mock
    Player secondPlayer;

    @Test
    public void gameDoNotStart_beforePlayersInit() {
        TennisGame tennisGame = new TennisGame("1");
        Assertions.assertThrows(IllegalStateException.class,
                () -> tennisGame.scorePointForPlayer(firstPlayer));
    }
    @Test
    public void whenNewPlayersAdded_GameChangeStateToInProgress() {
        TennisGame tennisGame = new TennisGame("1");
        tennisGame.addPlayers(firstPlayer, secondPlayer);
        Assertions.assertTrue(tennisGame.inProgress());
    }
    @Test
    public void afterMultipleRounds_GameUpdateTheCurrentScore() {
        TennisGame tennisGame = new TennisGame("1");
        tennisGame.addPlayers(firstPlayer, secondPlayer);

        tennisGame.scorePointForPlayer(firstPlayer);
        tennisGame.scorePointForPlayer(secondPlayer);
        tennisGame.scorePointForPlayer(secondPlayer);
        List<PlayerScoreView> currentScore = tennisGame.getCurrentScore();
        Assertions.assertEquals(2, currentScore.size());

        Assertions.assertEquals("15",currentScore.get(0).scorePoint().toString());
        Assertions.assertEquals("30",currentScore.get(1).scorePoint().toString());


    }
}