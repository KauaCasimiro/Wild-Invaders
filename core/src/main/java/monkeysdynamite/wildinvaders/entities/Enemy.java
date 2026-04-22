package monkeysdynamite.wildinvaders.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Enemy {
    public float x, y;
    public float width, height;

    public boolean isAlive;

    private static Texture texture;
    private Sprite sprite;
    private Rectangle bounds;

    public Enemy (float x, float y) {
        this.x = x;
        this.y = y;

        this.width = 64;
        this.height = 64;

        this.isAlive = true;

        if (texture == null) {
            texture = new Texture(Gdx.files.internal("player/trator.png"));
        }

        sprite = new Sprite(texture);

        bounds = new Rectangle(x, y, width, height);
    }

    public void render (SpriteBatch batch) {
        if (!isAlive) {
            return;
        }

        sprite.setPosition(x, y);
        sprite.setSize(width, height);
        sprite.setOriginCenter();
        sprite.draw(batch);
    }

    public void setDirection(int direction) {
        //direction: -1 (left) | 1 (right)

        if (direction < 0 && !sprite.isFlipX()) {
            sprite.flip(true, false);
        } else if (direction > 0 && !sprite.isFlipY()) {
            sprite.flip(true, false);
        }
    }

    public Rectangle getBounds() {
        return bounds;
    }
    public void update() {
        bounds.setPosition(x, y);
    }

    public static void disposeShared() {
        if (texture != null) {
            texture.dispose();
            texture = null;
        }
    }
}
