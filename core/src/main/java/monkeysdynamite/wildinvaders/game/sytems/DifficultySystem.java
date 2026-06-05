package monkeysdynamite.wildinvaders.game.sytems;

import monkeysdynamite.wildinvaders.entities.Enemy;
import monkeysdynamite.wildinvaders.game.GameDatabase;

public class DifficultySystem implements GameSystem {
    @Override
    public void update(GameDatabase db, float delta){
        updateGameTime(db, delta);

        int aliveEnemies = countAliveEnemies(db);

        updateDifficulty(db, aliveEnemies);

        applyDifficulty(db);
    }

    private void updateGameTime(GameDatabase db, float delta) {
        db.gameTime += delta;
        db.runStats.totalGameTime = db.gameTime;
    }

    private int countAliveEnemies(GameDatabase db) {
        int alive = 0;
        for (Enemy e : db.enemies) {
            if (e.isAlive) {
                alive++;
            }
        }
        return alive;
    }

    private void updateDifficulty(GameDatabase db, int aliveEnemies) {

    float timeFactor = (int)(db.gameTime / 15f);

    float aliveRatio = (float) aliveEnemies / db.totalEnemies;

    float waveFactor = (1f - aliveRatio);

    db.difficultyMultiplier = 1f + (waveFactor * 0.5f) + (timeFactor * 0.5f);
}

    private void applyDifficulty(GameDatabase db) {

    int timeSteps = (int)(db.gameTime / 15f);

    // SPEED
    db.formationSpeed = Math.min(db.baseFormationSpeed + (timeSteps * 10f)+ ((db.difficultyMultiplier - 1f) * 30f),140f);

    // SHOOT COOLDOWN
    db.enemyShootCooldown = Math.max(db.baseEnemyShootCooldown - (timeSteps * 0.08f) - ((db.difficultyMultiplier - 1f) * 0.20f),0.25f);
}
}
