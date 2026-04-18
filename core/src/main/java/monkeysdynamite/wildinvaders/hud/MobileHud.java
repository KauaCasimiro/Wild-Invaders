package monkeysdynamite.wildinvaders.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MobileHud {

    public boolean isLeftPressed;
    public boolean isRightPressed;
    public boolean isShootJustPreesed;
    private ShapeRenderer shapeRenderer;

    //area of buttons (x, y, size)
    private float leftx, lefty, size;
    private float rightx, righty;
    private float shootx, shooty;

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

        //shoot button
        shootx = rightx;
        shooty = h * 0.18f;
    }

    public void render() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // left button
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(leftx, lefty, size, size);

        // right button
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(rightx, righty, size, size);

        //shoot button
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(shootx, shooty, size, size);

        shapeRenderer.end();
    }

    public void update() {
        isLeftPressed = false;
        isRightPressed = false;
        isShootJustPreesed = false;

        if (Gdx.input.isTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();

            // In the LibGDX: the origin is top, HUD is base -> invert y
            float h = Gdx.graphics.getHeight();
            y = h - y;

            //check left button
            if (x >= leftx && x <= leftx + size && y >= lefty && y <= lefty + size) {
                isLeftPressed = true;
            }

            //check right button
            if (x >= rightx && x <= rightx + size && y >= righty && y <= righty + size) {
                isRightPressed = true;
            }

            if (x >= shootx && x <= shootx + size && y >= shooty && y <= shooty + size) {
                isShootJustPreesed = true;
            }
        }
    }

    public void dispose() {
        shapeRenderer.dispose();
    }
}
