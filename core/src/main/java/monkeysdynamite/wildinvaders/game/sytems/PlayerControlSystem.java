package monkeysdynamite.wildinvaders.game.sytems;

import monkeysdynamite.wildinvaders.game.GameDatabase;

public class PlayerControlSystem implements GameSystem {
    @Override
    public void update(GameDatabase db, float delta) {
        db.player.updateMovement(db.controllers);
    }

}
