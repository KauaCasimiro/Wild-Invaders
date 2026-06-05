package monkeysdynamite.wildinvaders.screen;

//Imports LibGDX
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

//Imports packages
import monkeysdynamite.wildinvaders.Main;
import monkeysdynamite.wildinvaders.config.GameCamera;
import monkeysdynamite.wildinvaders.config.GameConfig;
import monkeysdynamite.wildinvaders.game.GameController;
import monkeysdynamite.wildinvaders.game.GameDatabase;
import monkeysdynamite.wildinvaders.game.tools.DebugTool;
import monkeysdynamite.wildinvaders.hud.HudCamera;
import monkeysdynamite.wildinvaders.hud.MobileHud;



/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {
    private MobileHud mobile;
    private GameController gameController;
    private GameDatabase db;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;

    private DebugTool debugTool;
    private GameCamera gameCamera;
    private HudCamera hudCamera;

    private Main game;

    public FirstScreen(Main game) {
        this.game = game;
    }


    @Override
    public void show() {
        gameCamera = new GameCamera(GameConfig.WorldConfig.WORLD_WIDTH, GameConfig.WorldConfig.WORLD_HEIGHT);
        hudCamera = new HudCamera(GameConfig.WorldConfig.WORLD_WIDTH, GameConfig.WorldConfig.WORLD_HEIGHT);


        if (GameConfig.isMobile) {
            mobile = new MobileHud(hudCamera);
        }

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        gameController = new GameController();

        db = gameController.getDb();

        font = db.assets.mediumFont;

        debugTool = new DebugTool();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //----- INPUT DESKTOP -----
        boolean keyLeft = Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean keyRight = Gdx.input.isKeyPressed(Input.Keys.D)  || Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean keyShoot = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);

        //----- INPUT MOBILE -----
        boolean buttonLeft = false;
        boolean buttonRight = false;
        boolean buttonShoot = false;

        if (mobile != null) {
            mobile.update();
            buttonLeft = mobile.isLeftPressed;
            buttonRight = mobile.isRightPressed;
            buttonShoot = mobile.isShootPressed;
        }

        //-----UNIFY INPUT-----
        boolean left = keyLeft || buttonLeft;
        boolean right = keyRight || buttonRight;
        boolean shoot = keyShoot || buttonShoot;
        //-----UNIFY INPUT-----

        //-----UPDATE GAME-----
        gameController.update(left, right, shoot);
        debugTool.updateDebugInput(db);
        //-----UPDATE GAME-----

        if (db.isGameOver) {
            game.setScreen(new GameOverScreen(game, db.runStats));

            dispose();

            return;
        }

        //-----GAME CAMERA-----
        gameCamera.apply();

        batch.setProjectionMatrix(gameCamera.getCamera().combined);
        //-----GAME CAMERA-----

        //-----RENDER GAME-----
        batch.begin();

        batch.draw(db.assets.bgTexture, 0, 0,GameConfig.WorldConfig.WORLD_WIDTH, GameConfig.WorldConfig.WORLD_HEIGHT);

        gameController.render(batch);
        batch.end();
        //-----RENDER GAME-----

        shapeRenderer.setProjectionMatrix(gameCamera.getCamera().combined);
        gameController.renderEffect(shapeRenderer);

        debugTool.renderColliders(shapeRenderer, gameCamera.getCamera(), db);

        hudCamera.apply();
        batch.setProjectionMatrix(hudCamera.getCamera().combined);
        debugTool.renderHudAreas(shapeRenderer, hudCamera.getCamera());
        //debugTool.renderMobileHudDebug(shapeRenderer, hudCamera, mobile);


        batch.begin();
        debugTool.renderHudDebug(batch, db.assets.mediumFont, db);
        debugTool.renderWaveMessage(batch, db.assets.titleFont, db);
        //debugTool.renderMobileInputText(batch, font, hudCamera, mobile);
        debugTool.renderFloatingScores(batch, db.assets.smallFont, db);

        float lifeSize = 38f;
        float padding = 10f;

        float startX = padding + 80f;
        float startY = padding;

        TextureRegion playerFrame = db.assets.playerIdleAnimation.getKeyFrame(db.player.animationTimer, true);

        batch.draw(playerFrame, startX + 500f, startY, lifeSize, lifeSize);
        font.draw(batch, "x " + db.playerLives, startX + 500f + lifeSize + 5f, startY + lifeSize - 5f);

        batch.end();
        //-----MOBILE HUD-----
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

        gameCamera.resize(width, height);
        hudCamera.resize(width, height);
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

        batch.dispose();
        shapeRenderer.dispose();

        gameController.dispose();
    }
}
