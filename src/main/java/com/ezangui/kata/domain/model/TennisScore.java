package com.ezangui.kata.domain.model;

/**
 * Address various scenarios associated with updating a tennis score value,
 * managing transitions between different score values.
 * @author ZANGUI Elmehdi
 */
public final class TennisScore {

     enum Score {
        ZERO_POINT("0"), ONE_POINT("15"),
        TWO_POINTS("30"), THREE_POINTS("40"),
        ADVANTAGE("A"), WIN("WINNER");
        private final String label;

        Score(String label) {
            this.label = label;
        }
    }
    private Score score;

    TennisScore(Score score) {
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

    void toDeuce() {
        score = Score.THREE_POINTS;
    }

    boolean winnerScore() {
        return score.equals(Score.WIN);
    }

    boolean hasMaxPoints() {
        return score.equals(Score.THREE_POINTS);
    }

    TennisScore scoreAdvantagePoint() {
        if (score != Score.THREE_POINTS) {
            throw new IllegalStateException("Point not eligible for advantage score" + score);
        }
        score = Score.ADVANTAGE;
        return this;
    }

    TennisScore scoreRegularPoint() {
        if (score == Score.WIN) {
            throw new IllegalStateException("Cannot score any additional point" + score);
        }
        score = switch (score) {
            case ZERO_POINT -> Score.ONE_POINT;
            case ONE_POINT -> Score.TWO_POINTS;
            case TWO_POINTS -> Score.THREE_POINTS;
            case THREE_POINTS, ADVANTAGE -> Score.WIN;
            default -> throw new IllegalStateException("Unexpected value: " + score);
        };
        return this;

    }

    Score getScore() {
        return score;
    }

    @Override
    public String toString() {
        return score.label;
    }

}
