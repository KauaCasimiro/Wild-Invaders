package monkeysdynamite.wildinvaders.game.tools;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import monkeysdynamite.wildinvaders.entities.Barrier;
import monkeysdynamite.wildinvaders.entities.Enemy;
import monkeysdynamite.wildinvaders.entities.Projectile;
import monkeysdynamite.wildinvaders.game.GameDatabase;
import monkeysdynamite.wildinvaders.entities.EffectParticle;

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

        if (db.playerDamageFlash) {

            if ((int) (db.player.invulnerableTimer * 20) % 2 == 0) {
                return;
            }
        }

        TextureRegion playerFrame;

        if (db.player.isAttacking) {
            playerFrame = db.assets.playerAttackAnimation.getAnimation().getKeyFrame(db.player.attackTimer, false);
        } else {
            playerFrame = db.assets.playerIdleAnimation.getAnimation().getKeyFrame(db.player.animationTimer, true);
        }

        batch.draw(playerFrame, db.player.getX(), db.player.getY(), db.player.getBounds().width, db.player.getBounds().height);

    }

    private void renderEnemies (SpriteBatch batch, GameDatabase db) {

        for (Enemy enemy : db.enemies) {
            if (!enemy.isAlive) {
                continue;
            }

            TextureRegion frame = null;

            switch (enemy.getType()) {
                case TRACTOR:
                    frame = db.assets.tractorAnimation.getAnimation().getKeyFrame(enemy.animationTimer, true);
                    break;

                case FARMER:
                    frame = db.assets.farmerAnimation.getAnimation().getKeyFrame(enemy.animationTimer, true);
                    break;

                case MINER:
                    frame = db.assets.minerAnimation.getAnimation().getKeyFrame(enemy.animationTimer, true);
                    break;
            }

            enemy.getSprite().setRegion(frame);
            enemy.getSprite().setPosition(enemy.x, enemy.y);
            enemy.getSprite().setSize(enemy.width, enemy.height);
            enemy.getSprite().setOriginCenter();
            enemy.getSprite().setRotation(enemy.rotation);
            enemy.getSprite().draw(batch);
        }

    }

    private void renderProjectiles (SpriteBatch batch, GameDatabase db) {
        for (Projectile projectile : db.projectiles) {
            if (!projectile.isActive) {
                continue;
            }

            TextureRegion projectileFrame = null;

                switch (projectile.getType()) {
                    case DYNAMITE:
                        projectileFrame = db.assets.dynamiteAnimation.getAnimation().getKeyFrame(projectile.animationTimer, true);
                        break;
    
                    case BULLET:
                        projectileFrame = db.assets.bulletAnimation.getAnimation().getKeyFrame(projectile.animationTimer, true);
                        break;
    
                    case PICKAXE:
                        projectileFrame = db.assets.pickaxeAnimation.getAnimation().getKeyFrame(projectile.animationTimer, true);
                        break;
                }

            batch.draw(projectileFrame, projectile.x, projectile.y, projectile.getWidth(), projectile.getHeight());
        }
    }

    private void renderBarriers(SpriteBatch batch, GameDatabase db) {
        for (Barrier barrier : db.barriers) {
            if (!barrier.isActive) {
                continue;
            }


            TextureRegion barrierFrame;

            if(barrier.isHit) {
                barrierFrame = db.assets.barrierHitAnimation.getAnimation().getKeyFrame(barrier.hitTimer, false);
            } else if (barrier.state == Barrier.BarrierState.DAMAGED) {
                barrierFrame = new TextureRegion(db.assets.barrierDamaged);
            } else {
                barrierFrame = new TextureRegion(db.assets.barrierIdle);
            }
            batch.draw(barrierFrame, barrier.x, barrier.y, barrier.width, barrier.height);
        }
    }

    public void renderParticles(ShapeRenderer shapeRenderer, GameDatabase db) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for (EffectParticle p : db.particles) {

            if (!p.isActive) {
                continue;
            }

            shapeRenderer.rect(p.x, p.y, 4, 4);
        }

        shapeRenderer.end();
    }
}
