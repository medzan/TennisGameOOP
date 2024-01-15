package com.ezangui.kata.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScoreScoreTest {
    @Test
    public void resetScoreToZeroOnNewPoint() {
        TennisScore scorePoint = TennisScore.initScore();
        Assertions.assertEquals("0", scorePoint.toString());
    }
    @Test
    public void whenScoreIsLessThen40_thenIncrementRegularScore() {
        TennisScore scorePoint = TennisScore.initScore();
        scorePoint = scorePoint.scoreRegularPoint();
        scorePoint = scorePoint.scoreRegularPoint();
        scorePoint = scorePoint.scoreRegularPoint();

        Assertions.assertEquals("40", scorePoint.toString());
    }
    @Test
    public void whenScoreIsWin_disallowAnyAdditionalPoints() {
        TennisScore scorePoint = TennisScore.initScore();
        scorePoint = scorePoint.scoreRegularPoint();//15
        scorePoint = scorePoint.scoreRegularPoint();//30
        scorePoint = scorePoint.scoreRegularPoint();//40
        scorePoint = scorePoint.scoreRegularPoint();// WIN

        Assertions.assertTrue(scorePoint.winnerScore());
        Assertions.assertThrows(IllegalStateException.class, scorePoint::scoreRegularPoint);

    }
    @Test
    public void whenPointsIfScoreIsLessThan40_thenDisallowAdvantagePoint() {
        TennisScore scorePoint = TennisScore.initScore();
        scorePoint.scoreRegularPoint();//15
        Assertions.assertThrows(IllegalStateException.class, scorePoint::scoreAdvantagePoint);

    }


}