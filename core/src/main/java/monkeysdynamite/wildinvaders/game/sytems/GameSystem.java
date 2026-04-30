package monkeysdynamite.wildinvaders.game.sytems;
import monkeysdynamite.wildinvaders.game.GameDatabase;
public interface GameSystem {
    void update(GameDatabase db, float delta);
}
