package monkeysdynamite.wildinvaders.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import monkeysdynamite.wildinvaders.controllers.Controllers;

public class Player {
    private Texture naveImagem;
    private Rectangle nave;

    public Player(float x, float y, float width, float height) {
        naveImagem = new Texture(Gdx.files.internal("player/nave.png"));
        nave = new Rectangle(x, y, width, height);
        //nave.setSize(width, height);
    }

    public void updateMovement(Controllers controllers) {
        if (controllers.left) {
            nave.x -= 200 * Gdx.graphics.getDeltaTime();
        }
        if (controllers.right) {
            nave.x += 200 * Gdx.graphics.getDeltaTime();
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(naveImagem, nave.x, nave.y,  nave.width, nave.height);
    }

    public void dispose() {
        naveImagem.dispose();
    }

}
