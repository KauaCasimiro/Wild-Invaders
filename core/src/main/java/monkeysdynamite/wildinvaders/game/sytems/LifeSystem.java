package monkeysdynamite.wildinvaders.game.sytems;
import monkeysdynamite.wildinvaders.game.GameDatabase;
import monkeysdynamite.wildinvaders.game.managers.SaveManager;

public class LifeSystem {

    public void damagePlayer(GameDatabase db, int damage) {
        if (!db.player.isAlive) {
            return;
        }

        db.playerLives -= damage;

        if (db.playerLives <= 0) {
            db.playerLives = 0;
            db.player.isAlive = false;
            db.isGameOver = true;
        }
    }

    public void healPlayer(GameDatabase db, int amount) {
        db.playerLives += amount;

        if (db.playerLives > db.maxPlayerLives) {
            db.maxPlayerLives = db.playerLives;
        }
    }

    public void increaseMaxLives(GameDatabase db, int amount) {
        db.maxPlayerLives += amount;
        db.playerLives += amount;
    }

    public void restoreFullLife(GameDatabase db) {
        db.playerLives = db.maxPlayerLives;
    }

}
