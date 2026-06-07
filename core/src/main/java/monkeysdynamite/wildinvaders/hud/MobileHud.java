package monkeysdynamite.wildinvaders.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;

import monkeysdynamite.wildinvaders.config.GameConfig;

public class MobileHud {

    public boolean isLeftPressed;
    public boolean isRightPressed;
    public boolean isShootPressed;
    public boolean isPausePressed;
    private boolean pauseLocked;

    private ShapeRenderer shapeRenderer;

    private HudCamera hudCamera;

    // BUTTONS
    private Circle leftButton;
    private Circle rightButton;
    private Circle shootButton;
    private Circle pauseButton;

    public MobileHud(HudCamera hudCamera) {
        this.hudCamera = hudCamera;

        shapeRenderer = new ShapeRenderer();

        resize();
    }

    public void resize() {
        float w = GameConfig.WorldConfig.WORLD_WIDTH;

        float hudHeight = GameConfig.HudConfig.HUD_BOTTOM_HEIGHT + 70;

        //BUTTON CONFIG
        float radius = 60f;

        float centerY = hudHeight / 2f;

        // LEFT BUTTON
        leftButton = new Circle(50f, centerY, radius);

        // RIGHT BUTTON
        rightButton = new Circle(230f, centerY, radius);

        // SHOOT BUTTON
        shootButton = new Circle(w - 90f, centerY, radius);

        // PAUSE BUTTON
        pauseButton = new Circle(w - 250f, centerY, radius);
    }

    public void update() {

        isLeftPressed = false;
        isRightPressed = false;
        isShootPressed = false;
        isPausePressed = false;
        boolean pauseTouched = false;


        for (int i = 0; i < 10; i++) {
            if (!Gdx.input.isTouched(i)) {
                continue;
            }

            // TOUCH IN SCREEN SPACE
            Vector3 touch = new Vector3(Gdx.input.getX(i), Gdx.input.getY(i),0);

            // CONVERT SCREEN -> HUD WORLD
            hudCamera.getViewport().unproject(touch);

            if (leftButton.contains(touch.x, touch.y)) { // LEFT BUTTON
                isLeftPressed = true;
            }

            if (rightButton.contains(touch.x, touch.y)) { // RIGHT BUTTON
                isRightPressed = true;
            }

            if (shootButton.contains(touch.x, touch.y)) { // SHOOT BUTTON
                isShootPressed = true;
            }

            if (pauseButton.contains(touch.x, touch.y)) { // PAUSE BUTTON
                pauseTouched = true;
            }
        }

        if (pauseTouched && !pauseLocked) {
            isPausePressed = true;
            pauseLocked = true;
        }

        if (!pauseTouched) {
            pauseLocked = false;
        }
    }

    public void render() {

       shapeRenderer.setProjectionMatrix(hudCamera.getCamera().combined);
       shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

       //LEFT
        shapeRenderer.setColor(isLeftPressed ? Color.WHITE : Color.DARK_GRAY);
        shapeRenderer.circle(leftButton.x, leftButton.y, leftButton.radius);

        //RIGHT
        shapeRenderer.setColor(isRightPressed ? Color.WHITE : Color.DARK_GRAY);
        shapeRenderer.circle(rightButton.x, rightButton.y, rightButton.radius);

        //SHOOT
        shapeRenderer.setColor(isShootPressed ? Color.ORANGE : Color.RED);
        shapeRenderer.circle(shootButton.x, shootButton.y, shootButton.radius);

        //PAUSE
        shapeRenderer.setColor(isPausePressed ? Color.YELLOW : Color.GOLD);
        shapeRenderer.circle(pauseButton.x, pauseButton.y, pauseButton.radius);

        shapeRenderer.end();
    }

    public Circle getLeftButton() {
        return leftButton;
    }

    public Circle getRightButton() {
        return rightButton;
    }

    public Circle getShootButton() {
        return shootButton;
    }

    public void dispose() {
        shapeRenderer.dispose();
    }
}
