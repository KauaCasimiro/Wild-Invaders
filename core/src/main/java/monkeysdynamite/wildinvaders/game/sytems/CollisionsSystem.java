package monkeysdynamite.wildinvaders.game.sytems;

import monkeysdynamite.wildinvaders.entities.Enemy;
import monkeysdynamite.wildinvaders.entities.Projectile;
import monkeysdynamite.wildinvaders.game.GameDatabase;

public class CollisionsSystem implements GameSystem {
    @Override
    public void update(GameDatabase db, float delta) {

        handleDynamiteEnemyCollision(db);
        //handleProjectilePlayerCollision(db);
    }

    //Collison dynamite x enemies
    private void handleDynamiteEnemyCollision(GameDatabase db) {

        for (Projectile p : db.projectiles) {

            if (!p.isActive) {
                continue;
            }
            if (p.getType() != Projectile.ProjectileType.DYNAMITE) {
                continue;
            }

            for (Enemy enemy : db.enemies) {

                if (!enemy.isAlive) {
                    continue;
                }
                if (!p.getBounds().overlaps(enemy.getBounds())) {
                    continue;
                }

                Enemy front = db.frontEnemyByColumn.get(enemy.columnIndex);

                if (front == enemy) {
                    enemy.isAlive = false;
                    p.isActive = false;
                } else {
                    p.isActive = false;
                }

                break;
            }
        }
    }

    //Collison projectile enemies x player
    private void handleProjectilePlayerCollision(GameDatabase db) {

        for (Projectile p : db.projectiles) {

            if (!p.isActive) continue;
            if (p.getType() == Projectile.ProjectileType.DYNAMITE) continue;

            if (p.getBounds().overlaps(db.player.getBounds())) {
                db.player.isAlive = false;
                p.isActive = false;
                break;
            }
        }
    }
}
