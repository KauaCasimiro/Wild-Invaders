package monkeysdynamite.wildinvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import monkeysdynamite.wildinvaders.config.GameConfig;
import monkeysdynamite.wildinvaders.controllers.Controllers;
import monkeysdynamite.wildinvaders.entities.Player;
import monkeysdynamite.wildinvaders.hud.MobileHud;
import com.badlogic.gdx.Screen;


/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {
    private MobileHud mobile;
    private Controllers controllers;
    private Player player;
    private OrthographicCamera camera;

    private SpriteBatch batch;

    @Override
    public void show() {
        controllers = new Controllers();
        player = new Player(200.0f, 10.0f, 100.0f, 100.0f);
        camera = new OrthographicCamera();
        batch = new SpriteBatch();

        if (GameConfig.isMobile) {
            mobile = new MobileHud();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Input Keys
        boolean keyLeft = Gdx.input.isKeyPressed(Input.Keys.A);
        boolean keyRight = Gdx.input.isKeyPressed(Input.Keys.D);

        //Input buttons
        boolean buttonLeft = false;
        boolean buttonRight = false;

        if (mobile != null) {
            mobile.update();
            buttonLeft = mobile.isLeftPressed;
            buttonRight = mobile.isRightPressed;
        }

        //Unify in the class Controllers
        controllers.update(keyLeft, keyRight, buttonLeft, buttonRight);

        player.updateMovement(controllers);

        if (mobile != null) {
            mobile.render();
        }

        batch.begin();
        player.render(batch);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

        if(width <= 0 || height <= 0) {
            return;
        }

        if (mobile != null) {
            mobile.resize();
        }

        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        if (mobile != null) {
            mobile.dispose();
        }
        player.dispose();
    }
}
