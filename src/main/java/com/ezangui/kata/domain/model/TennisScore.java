package com.ezangui.kata.domain.model;

/**
 * Value object Address various scenarios associated with updating a tennis score value,
 * managing transitions between different score values.
 *
 * @author ZANGUI Elmehdi
 */
public final class TennisScore{
    private enum Score {
        ZERO_POINT("0"), ONE_POINT("15"),
        TWO_POINTS("30"), THREE_POINTS("40"),
        ADVANTAGE("A"), WIN("WINNER");
        private final String label;

        Score(String label) {
            this.label = label;
        }
    }

    private final Score score;

    private TennisScore(Score score) {
        this.score = score;
    }

    static TennisScore initScore() {
        return new TennisScore(Score.ZERO_POINT);
    }

    static TennisScore clone(TennisScore scorePoint) {
        return new TennisScore(scorePoint.score);
    }

    boolean hasAdvantage() {
        return score.equals(Score.ADVANTAGE);
    }

    TennisScore deuce() {
        return new TennisScore(Score.THREE_POINTS);
    }

    public boolean winnerScore() {
        return score.equals(Score.WIN);
    }

    boolean hasMaxRegularPoints() {
        return score.equals(Score.THREE_POINTS);
    }

    TennisScore scoreAdvantagePoint() {
        if (score != Score.THREE_POINTS) {
            throw new IllegalStateException("Point not eligible for advantage score" + score);
        }
        return new TennisScore(Score.ADVANTAGE);
    }

    TennisScore scoreRegularPoint() {
        if (score == Score.WIN) {
            throw new IllegalStateException("Cannot score any additional point" + score);
        }
        Score newScore = switch (score) {
            case ZERO_POINT -> Score.ONE_POINT;
            case ONE_POINT -> Score.TWO_POINTS;
            case TWO_POINTS -> Score.THREE_POINTS;
            case THREE_POINTS, ADVANTAGE -> Score.WIN;
            default -> throw new IllegalStateException("Unexpected value: " + score);
        };
        return new TennisScore(newScore);
    }

    @Override
    public String toString() {
        return score.label;
    }


}
