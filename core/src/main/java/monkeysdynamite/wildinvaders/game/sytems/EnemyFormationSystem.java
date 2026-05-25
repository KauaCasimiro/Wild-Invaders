package monkeysdynamite.wildinvaders.game.sytems;

import monkeysdynamite.wildinvaders.config.GameConfig;
import monkeysdynamite.wildinvaders.entities.Enemy;
import monkeysdynamite.wildinvaders.game.GameDatabase;

public class EnemyFormationSystem implements GameSystem {
    @Override
    public void update(GameDatabase db, float delta) {

        boolean hitEdge = false;

        for (Enemy enemy  : db.enemies) {
            if (enemy.x > GameConfig.WorldConfig.WORLD_WIDTH - 64 || enemy.x < 0) {
                hitEdge = true;
                break;
            }
        }

        if (hitEdge) {
            db.formationDirection *= -1;

            for (Enemy enemy : db.enemies) {
                enemy.y -= 20;
            }
        }

        for (Enemy enemy : db.enemies) {
            if (!enemy.isShooting) {
                if (db.formationDirection > 0) {
                    enemy.rotation = 90;
                } else {
                    enemy.rotation = 270;
                }
            }
        }

        for (Enemy enemy :  db.enemies) {
            enemy.x += db.formationSpeed * db.formationDirection * delta;
            enemy.update(delta);
        }
    }
}
