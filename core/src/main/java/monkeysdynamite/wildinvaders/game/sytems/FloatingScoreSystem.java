package monkeysdynamite.wildinvaders.game.sytems;

import monkeysdynamite.wildinvaders.game.GameDatabase;
import monkeysdynamite.wildinvaders.game.tools.FloatingScore;

import java.util.Iterator;

public class FloatingScoreSystem implements GameSystem {

    @Override
    public void update(GameDatabase db, float delta) {
        Iterator<FloatingScore> iterator = db.floatingScores.iterator();

        while (iterator.hasNext()) {
            FloatingScore score = iterator.next();

            score.y += 20f * delta;

            score.timer -= delta;

            if (score.timer <= 0) {
                iterator.remove();
            }
        }
    }
}
