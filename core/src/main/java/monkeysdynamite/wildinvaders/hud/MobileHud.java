package monkeysdynamite.wildinvaders.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MobileHud {

    private ShapeRenderer shapeRenderer;

    //area of buttons (x, y, size)
    private float leftx, lefty, size;
    private float rightx, righty;

    public MobileHud() {
        shapeRenderer = new ShapeRenderer();

        resize();
    }

    public void resize() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        size = w * 0.18f; // size button

        // left button (bottom left corner)
        leftx = w * 0.05f;
        lefty = h * 0.08f;

        // right button (bottom right corner)
        rightx = w - size - (w * 0.05f);
        righty = h * 0.08f;

        //future shoot button
    }

    public void render() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // left button
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(leftx, lefty, size, size);

        // right button
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(rightx, righty, size, size);

        shapeRenderer.end();
    }

    public void dispose() {
        shapeRenderer.dispose();
    }
}
