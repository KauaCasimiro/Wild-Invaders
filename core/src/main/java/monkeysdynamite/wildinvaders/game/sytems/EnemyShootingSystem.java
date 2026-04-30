package monkeysdynamite.wildinvaders.game.sytems;

import java.util.ArrayList;

import monkeysdynamite.wildinvaders.game.GameDatabase;
import monkeysdynamite.wildinvaders.entities.Enemy;
import monkeysdynamite.wildinvaders.entities.Projectile;

public class EnemyShootingSystem implements GameSystem{
    @Override
    public void update(GameDatabase db, float delta) {

        db.enemyShootTimer +=  delta;

        if (db.enemyShootTimer >= db.enemyShootCooldown) {
            tryEnemyShoot(db);
            db.enemyShootTimer = 0;
        }
    }

    private void tryEnemyShoot(GameDatabase db) {

        int enemyShots = 0;

        for (Projectile p : db.projectiles) {
            if (p.getType() != Projectile.ProjectileType.DYNAMITE && p.isActive) {
                enemyShots++;
            }
        }


        if (enemyShots >= db.maxEnemyProjectiles) {
            return;
        }

        ArrayList<Enemy> aliveEnemies = new ArrayList<>();


        for (Enemy e : db.enemies) {
            if (e.isAlive) {
                aliveEnemies.add(e);
            }
        }
        if (aliveEnemies.isEmpty()) {
            return;
        }

        int index = (int) (Math.random() * aliveEnemies.size());
        Enemy shooter = aliveEnemies.get(index);

        Projectile.ProjectileType type = shooter.getProjectileType();

        db.projectiles.add(new Projectile(shooter.x, shooter.y, type));
    }
}
