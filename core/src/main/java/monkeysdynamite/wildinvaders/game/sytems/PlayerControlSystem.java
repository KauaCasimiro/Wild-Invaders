package monkeysdynamite.wildinvaders.game.sytems;

import monkeysdynamite.wildinvaders.game.GameDatabase;

public class PlayerControlSystem implements GameSystem {
    @Override
    public void update(GameDatabase db, float delta) {

        if (!db.isPlayerActive()) {
            return;
        }

        db.player.updateMovement(db.left, db.right);
        db.player.updateAnimation(delta);
    }

}
