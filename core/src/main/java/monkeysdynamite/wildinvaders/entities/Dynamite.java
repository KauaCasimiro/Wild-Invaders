package monkeysdynamite.wildinvaders.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class Dynamite {
    public boolean isActive;
    private Texture texture;
    // vertical speed
    private float vspd = 300f;

    //position
    public float y;
    public float x;

    //Construtor in the Dynamite object
    public Dynamite(float x, float y) {
        this.x = x;
        this.y = y;
        this.isActive = true;
        texture = new Texture(Gdx.files.internal("player/dynamite.png"));
    }

    public Texture getTexture() {
        return texture;
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

    }
}
