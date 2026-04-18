package monkeysdynamite.wildinvaders;

//Imports LibGDX
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;

//Imports packages
import monkeysdynamite.wildinvaders.config.GameConfig;
import monkeysdynamite.wildinvaders.game.GameController;
import monkeysdynamite.wildinvaders.hud.MobileHud;



/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {
    private MobileHud mobile;
    private GameController gameController;
    private OrthographicCamera camera;
    private SpriteBatch batch;


    @Override
    public void show() {
        camera = new OrthographicCamera();
        batch = new SpriteBatch();

        gameController = new GameController();

        if (GameConfig.isMobile) {
            mobile = new MobileHud();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //----- INPUT DESKTOP -----
        boolean keyLeft = Gdx.input.isKeyPressed(Input.Keys.A);
        boolean keyRight = Gdx.input.isKeyPressed(Input.Keys.D);
        boolean keyShoot = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);

        //----- INPUT MOBILE -----
        boolean buttonLeft = false;
        boolean buttonRight = false;
        boolean buttonShoot = false;

        if (mobile != null) {
            mobile.update();
            buttonLeft = mobile.isLeftPressed;
            buttonRight = mobile.isRightPressed;
            buttonShoot = mobile.isShootJustPreesed;
        }

        //-----UNIFY INPUT-----
        boolean left = keyLeft || buttonLeft;
        boolean right = keyRight || buttonRight;
        boolean shoot = keyShoot || buttonShoot;

        gameController.update(left, right, shoot);

        batch.begin();
        gameController.render(batch);
        batch.end();

        if (mobile != null) {
            mobile.render();
        }
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
    }
}
