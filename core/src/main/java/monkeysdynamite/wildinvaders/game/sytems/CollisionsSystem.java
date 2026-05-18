package monkeysdynamite.wildinvaders.game.sytems;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;

import monkeysdynamite.wildinvaders.entities.Barrier;
import monkeysdynamite.wildinvaders.entities.Enemy;
import monkeysdynamite.wildinvaders.entities.Projectile;
import monkeysdynamite.wildinvaders.game.GameDatabase;

public class CollisionsSystem implements GameSystem {

    private final ScoreSystem scoreSystem;

    public CollisionsSystem(ScoreSystem scoreSystem) {
        this.scoreSystem = scoreSystem;
    }

    @Override
    public void update(GameDatabase db, float delta) {

        handleDynamiteEnemyCollision(db);

        handleProjectileBarrierCollision(db);

        handleProjectilePlayerCollision(db);

        handleProjectileCollision(db);
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
                    scoreSystem.onEnemyKilled(enemy, db);
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

            if (!p.isActive) {
                continue;
            }
            if (p.getType() == Projectile.ProjectileType.DYNAMITE) continue;

            if (p.getBounds().overlaps(db.player.getBounds())) {
                db.playerLives--;
                if (db.playerLives <= 0) {
                    db.player.isAlive = false;
                    db.isGameOver = true;
                }
                p.isActive = false;
                break;
            }
        }
    }

    //Collison projectile x barrier
    private void handleProjectileBarrierCollision(GameDatabase db) {
        for (Projectile p : db.projectiles) {
            if (!p.isActive) {
                continue;
            }

            for (Barrier b : db.barriers) {
                if (!b.isActive) {
                    continue;
                }

                if (!p.getBounds().overlaps(b.getBounds())) {
                    continue;
                }

                b.hit--;

                b.isHit = true;
                b.hitTimer = 0f;

                p.isActive = false;

                if (b.hit <= 0) {
                    b.isActive = false;
                }
                break;
            }

        }
    }

    private void handleProjectileCollision(GameDatabase db) {
        for (Projectile p : db.projectiles) {

            if (!p.isActive) {
                continue;
            }

            for (Projectile p2 : db.projectiles) {
                if (!p2.isActive) {
                    continue;
                }

                if (p.getType() == p2.getType()) {
                    continue;
                }

                if (p.getBounds().overlaps(p2.getBounds())) {
                    p.isActive = false;
                    p2.isActive = false;
                }
            }
        }
    }
}
