package monkeysdynamite.wildinvaders.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MobileHud {

    public boolean isLeftPressed;
    public boolean isRightPressed;
    public boolean isShootJustPreesed;
    private ShapeRenderer shapeRenderer;

    // Screen zones
    private float leftZoneWidth;
    private float rightZoneWidth;
    private float shootZoneX;

    public MobileHud() {
        shapeRenderer = new ShapeRenderer();

        resize();
    }


    public void resize() {
        float w = Gdx.graphics.getWidth();
        // LEFT MOVEMENT = 15%
        leftZoneWidth = w * 0.15f;

        // RIGHT MOVEMENT = 15%
        rightZoneWidth = w * 0.15f;

        // SHOOT ZONE START = 70%
        shootZoneX = w * 0.70f;
    }

    public void render() {

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        float buttonSize = 200f;
        float hudHeight = h * 0.25f;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // LEFT BUTTON (15% da largura, mas 30x30 fixo)
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(
            (w * 0.15f) / 2f - buttonSize / 2f,
            hudHeight / 2f - buttonSize / 2f,
            buttonSize,
            buttonSize
        );

        // RIGHT BUTTON (15% da largura)
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.rect(
            w * 0.15f + (w * 0.15f) / 2f - buttonSize / 2f,
            hudHeight / 2f - buttonSize / 2f,
            buttonSize,
            buttonSize
        );

        // SHOOT BUTTON (30% da tela direita)
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(
            w * 0.70f + (w * 0.30f) / 2f - buttonSize / 2f,
            hudHeight / 2f - buttonSize / 2f,
            buttonSize,
            buttonSize
        );

        shapeRenderer.end();
    }

    public void update() {
        isLeftPressed = false;
        isRightPressed = false;
        isShootJustPreesed = false;

        if (!Gdx.input.isTouched()) {
            return;
        }

        float x = Gdx.input.getX();

        // LEFT ZONE
        if (x <= leftZoneWidth) {

            isLeftPressed = true;
        }

        // RIGHT MOVE ZONE
        else if (x <= leftZoneWidth + rightZoneWidth) {

            isRightPressed = true;
        }

        // SHOOT ZONE
        else if (x >= shootZoneX) {

            isShootJustPreesed = true;
        }
    }

    public void dispose() {
        shapeRenderer.dispose();
    }
}
