package monkeysdynamite.wildinvaders.game.sytems;

import monkeysdynamite.wildinvaders.entities.Barrier;
import monkeysdynamite.wildinvaders.entities.EffectParticle;
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

        handleEnemyPlayerCollison(db);

        handleEnemyBarrierCollison(db);
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
                    for(int i = 0; i < 10; i++) {
                        float vx = (float)(Math.random() * 100f - 50f);
                        float vy = (float)(Math.random() * 100f - 50f);

                        db.particles.add(new EffectParticle(enemy.x + enemy.width / 2, enemy.y + enemy.height / 2, vx, vy, 0.4f));
                    }

                    enemy.isAlive = false;
                    p.isActive = false;
                    db.sound.playHitEnemy();
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
                db.life.damagePlayer(db, 1);

                db.playerDamageFlash = true;
                db.playerFlashTimer = 0f;

                db.sound.playHitPlayer();

                p.isActive = false;
                break;
            }
        }
    }

    //Collison enemys x player
    private void handleEnemyPlayerCollison(GameDatabase db) {
        for (Enemy enemy : db.enemies) {
            if (!enemy.isAlive) {
                continue;
            }

            if (enemy.getBounds().overlaps(db.player.getBounds())) {
                db.player.isAlive = false;
                db.isGameOver = true;
                break;
            }
        }
    }

    private void handleEnemyBarrierCollison(GameDatabase db) {
        for (Enemy enemy : db.enemies) {
            if (!enemy.isAlive) {
                continue;
            }

            for (Barrier b : db.barriers) {
                if (!b.isActive) {
                    continue;
                }

                if (!enemy.getBounds().overlaps(b.getBounds())) {
                    continue;
                }

                b.hit = 0;

                for(int i = 0; i < 10; i++) {
                        float vx = (float)(Math.random() * 100f - 50f);
                        float vy = (float)(Math.random() * 100f - 50f);

                        db.particles.add(new EffectParticle(b.x + b.width / 2, b.y + b.height / 2, vx, vy, 0.4f));
                }
                
                db.sound.playBarrierHit();

                if (b.hit <= 0) {
                    b.isActive = false;
                }
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

                for(int i = 0; i < 10; i++) {
                        float vx = (float)(Math.random() * 100f - 50f);
                        float vy = (float)(Math.random() * 100f - 50f);

                        db.particles.add(new EffectParticle(b.x + b.width / 2, b.y + b.height / 2, vx, vy, 0.4f));
                    }

                b.isHit = true;
                b.hitTimer = 0f;

                p.isActive = false;

                db.sound.playBarrierHit();

                if (b.hit <= 0) {
                    b.isActive = false;
                }
                break;
            }

        }
    }

    //Collison projectile x projectile
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
