package monkeysdynamite.wildinvaders.game.sytems;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
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
            if (!e.isAlive) {
                continue;
            }

            if (e.getProjectileType() == null) {
                continue;
            }

            aliveEnemies.add(e);
        }
        if (aliveEnemies.isEmpty()) {
            return;
        }

        int index = (int) (Math.random() * aliveEnemies.size());
        Enemy shooter = aliveEnemies.get(index);
        shooter.isShooting = true;
        shooter.shootVisualTimer = 0.25f;
        shooter.rotation = 0;

        Projectile.ProjectileType type = shooter.getProjectileType();
        Enemy.EnemyType enemyType = shooter.getType();

        if (type == null) {
            return;
        }

        if (enemyType == Enemy.EnemyType.TRACTOR || enemyType == null) {
            return;
        }

        Texture texture;

        switch (type) {

            case BULLET:
                texture = db.assets.bulletTexture;
                break;

            case PICKAXE:
                texture = db.assets.pickaxeTexture;
                break;

            default:
                texture = db.assets.bulletTexture;
                break;
        }

        switch (enemyType) {
            case FARMER:
                db.sound.playShootFarmer();
                break;

            case MINER:
                db.sound.playShootMiner();
                break;
        
            default:
                break;
        }

        db.projectiles.add(new Projectile(shooter.x, shooter.y, type, texture));
    }
}
