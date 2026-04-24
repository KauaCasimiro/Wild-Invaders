package monkeysdynamite.wildinvaders.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import monkeysdynamite.wildinvaders.controllers.Controllers;

public class Player {
    private Texture texture;
    private Rectangle nave;

    private Rectangle bounds;

    public boolean isAlive;

    public Player(float x, float y, float width, float height) {
        texture = new Texture(Gdx.files.internal("player/george_idle_1.png"));
        nave = new Rectangle(x, y, width, height);
        bounds = new Rectangle(x, y, width, height);
        this.isAlive = true;
        //nave.setSize(width, height);
    }

    public void updateMovement(Controllers controllers) {
        if (controllers.left) {
            nave.x -= 200 * Gdx.graphics.getDeltaTime();
        }
        if (controllers.right) {
            nave.x += 200 * Gdx.graphics.getDeltaTime();
        }

        bounds.setPosition(nave.x, nave.y);
    }

    public float getX() {
        return nave.x;
    }

    public float getY() {
        return nave.y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void render(SpriteBatch batch) {
        if (!isAlive) {
            return;
        }

        batch.draw(texture, nave.x, nave.y,  nave.width, nave.height);
    }

    public void dispose() {
        texture.dispose();
    }

}
