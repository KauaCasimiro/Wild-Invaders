package monkeysdynamite.wildinvaders.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class SaveManager {

    private static final String SAVE_NAME = "wild_invaders_save";
    private static final String HIGH_SCORE_KEY = "high-score";

    private static Preferences prefs = Gdx.app.getPreferences(SAVE_NAME);

    public static int loadHighScore() {
        return prefs.getInteger(HIGH_SCORE_KEY, 0);
    }

    public static void saveHighScore(int score) {

        int currentHighScore = loadHighScore();

        if (score > currentHighScore) {
            prefs.putInteger(HIGH_SCORE_KEY, score);
            prefs.flush();
        }
    }
}
