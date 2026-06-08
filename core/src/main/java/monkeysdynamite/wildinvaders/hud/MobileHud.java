package monkeysdynamite.wildinvaders.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.Texture;

import monkeysdynamite.wildinvaders.config.GameConfig;

public class MobileHud {

    public boolean isLeftPressed;
    public boolean isRightPressed;
    public boolean isShootPressed;
    public boolean isPausePressed;
    private boolean pauseLocked;

    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;

    private Texture leftButton1;
    private Texture leftButton2;

    private Texture rightButton1;
    private Texture rightButton2;

    private Texture shootButton1;
    private Texture shootButton2;

    private HudCamera hudCamera;

    // BUTTONS
    private Circle leftButton;
    private Circle rightButton;
    private Circle shootButton;
    private Circle pauseButton;

    public MobileHud(HudCamera hudCamera) {
        this.hudCamera = hudCamera;

        shapeRenderer = new ShapeRenderer();

        batch = new SpriteBatch();

        leftButton1 = new Texture(Gdx.files.internal("buttons/left/left_button_1.png"));
        leftButton2 = new Texture(Gdx.files.internal("buttons/left/left_button_2.png"));

        rightButton1 = new Texture(Gdx.files.internal("buttons/right/right_button_1.png"));
        rightButton2 = new Texture(Gdx.files.internal("buttons/right/right_button_2.png"));

        shootButton1 = new Texture(Gdx.files.internal("buttons/atk/atk_button_1.png"));
        shootButton2 = new Texture(Gdx.files.internal("buttons/atk/atk_button_2.png"));

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
        pauseButton = new Circle(50f, centerY + 540f, radius-30f);
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
       Gdx.gl.glEnable(GL20.GL_BLEND);
       Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

       shapeRenderer.setProjectionMatrix(hudCamera.getCamera().combined);
       shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        /*
       //LEFT
        shapeRenderer.setColor(isLeftPressed ? new Color(1, 1, 1, 0.8f) : new Color(0.25f, 0.25f, 0.25f, 0.8f));
        shapeRenderer.circle(leftButton.x, leftButton.y, leftButton.radius);

        //RIGHT
        shapeRenderer.setColor(isRightPressed ? new Color(1, 1, 1, 0.8f) : new Color(0.25f, 0.25f, 0.25f, 0.8f));
        shapeRenderer.circle(rightButton.x, rightButton.y, rightButton.radius);

        //SHOOT
        shapeRenderer.setColor(isShootPressed ? new Color(1, 0.647f, 0, 0.8f) : new Color(0.8f, 0, 0, 0.8f));
        shapeRenderer.circle(shootButton.x, shootButton.y, shootButton.radius);
        */

        //PAUSE
        shapeRenderer.setColor(isPausePressed ? new Color(1, 1, 0, 0.9f) : new Color(1, 0.843f, 0, 0.9f));
        shapeRenderer.circle(pauseButton.x, pauseButton.y, pauseButton.radius);

        shapeRenderer.setColor(Color.BLACK);

        float barWidth = 8f;
        float barHeight = 30f;

        shapeRenderer.rect(pauseButton.x - 12, pauseButton.y - 15, barWidth, barHeight);
        shapeRenderer.rect(pauseButton.x + 4, pauseButton.y - 15, barWidth, barHeight);

        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        batch.setProjectionMatrix(hudCamera.getCamera().combined);

        batch.begin();

        Texture leftTexture = isLeftPressed ? leftButton2 : leftButton1;
        Texture rightTexture = isRightPressed ? rightButton2 : rightButton1;
        Texture shootTexture =  isShootPressed ? shootButton2 : shootButton1;

        batch.draw(leftTexture, leftButton.x - leftButton.radius, leftButton.y - leftButton.radius, leftButton.radius * 2.5f, leftButton.radius * 2.5f);
        batch.draw(rightTexture, rightButton.x - rightButton.radius, rightButton.y - rightButton.radius, rightButton.radius * 2.5f, rightButton.radius * 2.5f);
        batch.draw(shootTexture, shootButton.x - shootButton.radius, shootButton.y - shootButton.radius, shootButton.radius * 2.5f, shootButton.radius * 2.5f);

        batch.end();
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

        leftButton1.dispose();
        leftButton2.dispose();

        rightButton1.dispose();
        rightButton2.dispose();

        shootButton1.dispose();
        shootButton2.dispose();
    }
}
