package monkeysdynamite.wildinvaders.game.tools;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import monkeysdynamite.wildinvaders.entities.Barrier;
import monkeysdynamite.wildinvaders.entities.Enemy;
import monkeysdynamite.wildinvaders.entities.Projectile;
import monkeysdynamite.wildinvaders.game.GameDatabase;

public class RenderTool {
    public void render (SpriteBatch batch, GameDatabase db) {

        renderPlayer(batch, db);

        renderEnemies(batch, db);

        renderProjectiles(batch, db);

        renderBarriers(batch, db);

        //renderFloatingScores(batch, db);
    }

    public void renderPlayer (SpriteBatch batch, GameDatabase db) {

        if (!db.player.isAlive) {
            return;
        }

        TextureRegion playerFrame;

        if (db.player.isAttacking) {
            playerFrame = db.assets.playerAttackAnimation.getKeyFrame(db.player.attackTimer, false);
        } else {
            playerFrame = db.assets.playerIdleAnimation.getKeyFrame(db.player.animationTimer, true);
        }

        batch.draw(playerFrame, db.player.getX(), db.player.getY(), db.player.getBounds().width, db.player.getBounds().height);

    }

    private void renderEnemies (SpriteBatch batch, GameDatabase db) {

        for (Enemy enemy : db.enemies) {
            if (!enemy.isAlive) {
                continue;
            }

            enemy.getSprite().setPosition(enemy.x, enemy.y);

            enemy.getSprite().setSize(enemy.width, enemy.height);

            enemy.getSprite().draw(batch);
        }

    }

    private void renderProjectiles (SpriteBatch batch, GameDatabase db) {
        for (Projectile projectile : db.projectiles) {
            if (!projectile.isActive) {
                continue;
            }

            batch.draw(projectile.getTexture(), projectile.x, projectile.y, projectile.getBounds().width, projectile.getBounds().height);
        }
    }

    private void renderBarriers(SpriteBatch batch, GameDatabase db) {
        for (Barrier barrier : db.barriers) {
            if (!barrier.isActive) {
                continue;
            }

            TextureRegion barrierFrame;

            if(barrier.isHit) {
                barrierFrame = db.assets.barrierHitAnimation.getKeyFrame(barrier.hitTimer, false);
            } else if (barrier.state == Barrier.BarrierState.DAMAGED) {
                barrierFrame = new TextureRegion(db.assets.barrierDamaged);
            } else {
                barrierFrame = new TextureRegion(db.assets.barrierIdle);
            }
            batch.draw(barrierFrame, barrier.x, barrier.y, barrier.width, barrier.height);
        }
    }

    /*private void renderFloatingScores (SpriteBatch batch, GameDatabase db) {
        for (var fs : db.floatingScores) {
            db.font.draw(batch, "+" + fs.value + fs.bonusText, fs.x, fs.y);
        }
    }*/
}
