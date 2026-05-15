package monkeysdynamite.wildinvaders.game.sytems;

import monkeysdynamite.wildinvaders.entities.Barrier;
import monkeysdynamite.wildinvaders.game.GameDatabase;

public class BarrierSystem implements GameSystem {
    @Override
    public void update(GameDatabase db, float delta) {
        for (Barrier b : db.barriers) {
            b.update(delta);
        }
    }
}
