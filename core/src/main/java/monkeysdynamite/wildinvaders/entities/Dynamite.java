package monkeysdynamite.wildinvaders.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Dynamite {
    public boolean isActive;
    private Texture texture;
    // vertical speed
    private float vspd = 300f;

    private int width = 16;
    private int height = 32;


    private Rectangle bounds;

    //position
    public float y;
    public float x;

    //Construtor in the Dynamite object
    public Dynamite(float x, float y) {
        this.x = x;
        this.y = y;
        this.isActive = true;
        texture = new Texture(Gdx.files.internal("player/dynamite.png"));
        bounds = new Rectangle(x, y, width, height);
    }

    public Texture getTexture() {
        return texture;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    //method responsable for verify existing the object in the screen and updating the movemment vertical him
    public void update(float delta) {
        if (!isActive) {
            return;
        }

        y += vspd * delta;
        if (y > Gdx.graphics.getHeight()) {
            isActive = false;
        }

        bounds.setPosition(x, y);
    }

    public void render(SpriteBatch batch) {
        if (!isActive) {
            return;
        }

        batch.draw(texture, x, y, width, height);
    }

    public void dispose() {
        texture.dispose();
    }
}
