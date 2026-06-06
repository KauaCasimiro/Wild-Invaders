    package monkeysdynamite.wildinvaders.game.sytems;

    import monkeysdynamite.wildinvaders.entities.Projectile;
    import monkeysdynamite.wildinvaders.game.GameDatabase;

    public class PlayerShootingSystem implements GameSystem {

        @Override
        public void update(GameDatabase db, float delta) {

            if (!db.isPlayerActive()) {
                return;
            }

            boolean hasDynamite = false;

            for (Projectile p : db.projectiles) {
                if (p.getType() == Projectile.ProjectileType.DYNAMITE && p.isActive) {
                    hasDynamite = true;
                    break;
                }
            }

            if (db.shoot && (!hasDynamite || db.debugInfiniteShoot)) {
                db.player.isAttacking = true;
                db.player.attackTimer = 0f;
                db.sound.playShootPlayer();
                db.projectiles.add(new Projectile(db.player.getX(), db.player.getY(), Projectile.ProjectileType.DYNAMITE));
            }
        }
    }
