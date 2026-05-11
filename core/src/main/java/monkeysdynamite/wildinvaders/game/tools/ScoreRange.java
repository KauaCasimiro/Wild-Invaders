package monkeysdynamite.wildinvaders.game.tools;

import com.badlogic.gdx.math.MathUtils;

public class ScoreRange {

    private int min;
    private int max;

    public ScoreRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public void set(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public void increase(int amount) {
        this.min += amount;
        this.max += amount;
    }

    public int roll() {

        int faces = (max - min) / 10;

        return (MathUtils.random(0, faces) * 10) + min;
    }

}
