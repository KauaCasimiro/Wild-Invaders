package monkeysdynamite.wildinvaders;

//Imports LibGDX
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

//Imports packages
import monkeysdynamite.wildinvaders.config.GameCamera;
import monkeysdynamite.wildinvaders.config.GameConfig;
import monkeysdynamite.wildinvaders.entities.Enemy;
import monkeysdynamite.wildinvaders.entities.Projectile;
import monkeysdynamite.wildinvaders.game.GameController;
import monkeysdynamite.wildinvaders.hud.HudCamera;
import monkeysdynamite.wildinvaders.hud.MobileHud;



/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {
    private MobileHud mobile;
    private GameController gameController;
    private SpriteBatch batch;

    private ShapeRenderer shapeRenderer;

    private GameCamera gameCamera;
    private HudCamera hudCamera;


    @Override
    public void show() {
        gameCamera = new GameCamera(GameConfig.WorldConfig.WORLD_WIDTH, GameConfig.WorldConfig.WORLD_HEIGHT);
        hudCamera = new HudCamera(GameConfig.WorldConfig.WORLD_WIDTH, GameConfig.WorldConfig.WORLD_HEIGHT);


        if (GameConfig.isMobile) {
            mobile = new MobileHud();
        }

        batch = new SpriteBatch();

        shapeRenderer = new ShapeRenderer();

        gameController = new GameController();

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

        gameCamera.apply();
        System.out.println("----- DEBUG -----");

// Tamanho da janela (window mode)
        System.out.println("Window: "
            + Gdx.graphics.getWidth() + " x "
            + Gdx.graphics.getHeight());

// Tamanho real do backbuffer (fullscreen / monitor)
        System.out.println("BackBuffer: "
            + Gdx.graphics.getBackBufferWidth() + " x "
            + Gdx.graphics.getBackBufferHeight());

// Viewport (mundo lógico)
        System.out.println("World (viewport): "
            + GameConfig.WorldConfig.WORLD_WIDTH + " x "
            + GameConfig.WorldConfig.WORLD_HEIGHT);

// Área real usada pela viewport na tela
        System.out.println("ScreenViewport area: "
            + gameCamera.getCamera().viewportWidth + " x "
            + gameCamera.getCamera().viewportHeight);

// Posição da câmera
        System.out.println("Camera pos: "
            + gameCamera.getCamera().position.x + ", "
            + gameCamera.getCamera().position.y);

        System.out.println("------------------");
        batch.setProjectionMatrix(gameCamera.getCamera().combined);


        batch.begin();
        gameController.render(batch);
        batch.end();

        shapeRenderer.setProjectionMatrix(gameCamera.getCamera().combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        OrthographicCamera cam = gameCamera.getCamera();

        float worldWidth = GameConfig.WorldConfig.WORLD_WIDTH;
        float worldHeight = GameConfig.WorldConfig.WORLD_HEIGHT;

        float x = 0;
        float y = 0;

        shapeRenderer.rect(x, y, worldWidth, worldHeight);

        // desenhar enemies
        for (Enemy enemy : gameController.getEnemies()) {
            shapeRenderer.rect(
                enemy.getBounds().x,
                enemy.getBounds().y,
                enemy.getBounds().width,
                enemy.getBounds().height
            );
        }

        // desenhar dynamite
        for (Projectile p : gameController.getProjectiles()) {
            shapeRenderer.rect(
                p.getBounds().x,
                p.getBounds().y,
                p.getBounds().width,
                p.getBounds().height
            );
        }

        shapeRenderer.end();

        hudCamera.apply();
        batch.setProjectionMatrix(hudCamera.getCamera().combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        // HUD TOPO
        shapeRenderer.rect(
            0,
            GameConfig.WorldConfig.WORLD_HEIGHT - GameConfig.HudConfig.HUD_TOP_HEIGHT,
            GameConfig.WorldConfig.WORLD_WIDTH,
            GameConfig.HudConfig.HUD_TOP_HEIGHT
        );

        // HUD BASE
        shapeRenderer.rect(
            0,
            0,
            GameConfig.WorldConfig.WORLD_WIDTH,
            GameConfig.HudConfig.HUD_BOTTOM_HEIGHT
        );

        shapeRenderer.end();


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

        gameController.dispose();
    }
}
