package monkeysdynamite.wildinvaders.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import monkeysdynamite.wildinvaders.controllers.Controllers;

public class Player {
    private Texture texture;
    private Rectangle player;

    private Rectangle bounds;

    public boolean isAlive;

    public Player(float x, float y, float width, float height, Texture texture) {
        this.texture = texture;

        player = new Rectangle(x, y, width, height);
        bounds = new Rectangle(x, y, width, height);

        isAlive = true;
    }

    public void updateMovement(boolean left, boolean right) {

        if (left) {
            player.x -= 200 * Gdx.graphics.getDeltaTime();
        }
        if (right) {
            player.x += 200 * Gdx.graphics.getDeltaTime();
        }

        bounds.setPosition(player.x, player.y);
    }

    public float getX() {
        return player.x;
    }

    public float getY() {
        return player.y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void render(SpriteBatch batch) {
        if (!isAlive) {
            return;
        }

        batch.draw(texture, player.x, player.y,  player.width, player.height);
    }
}
