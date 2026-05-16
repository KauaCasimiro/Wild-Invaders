package monkeysdynamite.wildinvaders.game.sytems;

import java.util.Iterator;

import monkeysdynamite.wildinvaders.game.GameDatabase;
import monkeysdynamite.wildinvaders.entities.Projectile;
import monkeysdynamite.wildinvaders.entities.Enemy;

public class CleanupSystem implements GameSystem {
    @Override
    public void update(GameDatabase db, float delta) {

        //Remove projectiles
        Iterator<Projectile> projectileIterator = db.projectiles.iterator();
        if (projectileIterator.hasNext()) {
            do {
                Projectile p = projectileIterator.next();
                if (!p.isActive) {
                    projectileIterator.remove();
                }
            } while (projectileIterator.hasNext());
        }

        //Remove enemies
        Iterator<Enemy> enemyIterator  = db.enemies.iterator();
        if (enemyIterator.hasNext()) {
            do {
                Enemy enemy = enemyIterator.next();
                if (!enemy.isAlive) {
                    enemyIterator.remove();
                }
            } while (enemyIterator.hasNext());
        }
    }
}
