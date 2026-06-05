package monkeysdynamite.wildinvaders.game.sytems;

import monkeysdynamite.wildinvaders.entities.Enemy;
import monkeysdynamite.wildinvaders.game.GameDatabase;

public class WaveSystem implements GameSystem {
    @Override
    public void update(GameDatabase db, float delta) {
        if (db.waveTransition) {
            db.waveTransitionTimer += delta;

            db.sound.playWaveStart();
            if (db.waveTransitionTimer >= 1.5) {
                startNextWave(db);
            }
            return;
        }

        if (!db.waveTransition && isWaveCleared(db)) {
            starWaveTransition(db);
        }
    }

    private boolean isWaveCleared(GameDatabase db) {
        for (Enemy enemy : db.enemies) {
            if (enemy.isAlive) {
                return false;
            }
        }
        return true;
    }

    private void starWaveTransition(GameDatabase db) {
        db.wave++;
        db.runStats.finalWave = db.wave;
        db.waveTransition = true;
        db.waveTransitionTimer = 0f;
    }

    private void startNextWave(GameDatabase db) {
        db.projectiles.clear();
        db.enemies.clear();

        db.life.restoreFullLife(db);

        db.requestNextWave = true;

        db.difficultyMultiplier += 0.2f;
        db.formationSpeed += 10f;

        db.waveTransition = false;
    }
}
