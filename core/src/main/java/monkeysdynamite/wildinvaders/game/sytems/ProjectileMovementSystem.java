package monkeysdynamite.wildinvaders.game.sytems;

import monkeysdynamite.wildinvaders.game.GameDatabase;
import monkeysdynamite.wildinvaders.entities.Projectile;
public class ProjectileMovementSystem implements GameSystem {

    @Override
    public void update(GameDatabase db, float delta) {
        for (Projectile p : db.projectiles) {
            p.update(delta);
        }
    }
}
