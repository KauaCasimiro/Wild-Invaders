package monkeysdynamite.wildinvaders;

//Imports LibGDX
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

//Imports packages
import monkeysdynamite.wildinvaders.config.GameCamera;
import monkeysdynamite.wildinvaders.config.GameConfig;
import monkeysdynamite.wildinvaders.entities.Enemy;
import monkeysdynamite.wildinvaders.entities.Projectile;
import monkeysdynamite.wildinvaders.game.GameController;
import monkeysdynamite.wildinvaders.game.GameDatabase;
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
        font = new BitmapFont();
        gameController = new GameController();

        db = gameController.getDb();
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
        //-----UNIFY INPUT-----

        //-----UPDATE GAME-----
        gameController.update(left, right, shoot);
        //-----UPDATE GAME-----

        //-----GAME CAMERA-----
        gameCamera.apply();

        batch.setProjectionMatrix(gameCamera.getCamera().combined);
        //-----GAME CAMERA-----

        //-----RENDER GAME-----
        batch.begin();
        gameController.render(batch);
        batch.end();
        //-----RENDER GAME-----

        //-----DEBUG COLLIDERS-----
        shapeRenderer.setProjectionMatrix(gameCamera.getCamera().combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        OrthographicCamera cam = gameCamera.getCamera();

        float worldWidth = GameConfig.WorldConfig.WORLD_WIDTH;
        float worldHeight = GameConfig.WorldConfig.WORLD_HEIGHT;

        shapeRenderer.rect(0, 0, worldWidth, worldHeight);
        //-----DEBUG COLLIDERS-----

        //-----ENEMIES-----
        for (Enemy enemy : db.enemies) {
            shapeRenderer.rect(enemy.getBounds().x, enemy.getBounds().y, enemy.getBounds().width, enemy.getBounds().height);
        }
        //-----ENEMIES-----

        // PROJECTILES
        for (Projectile p : db.projectiles) {
            shapeRenderer.rect(
                p.getBounds().x,
                p.getBounds().y,
                p.getBounds().width,
                p.getBounds().height
            );
        }
        // PROJECTILES

        shapeRenderer.end();

        //-----HUD CAMERA-----

        hudCamera.apply();
        batch.setProjectionMatrix(hudCamera.getCamera().combined);
        batch.begin();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        //-----HUD TOP-----
        shapeRenderer.rect(0, GameConfig.WorldConfig.WORLD_HEIGHT - GameConfig.HudConfig.HUD_TOP_HEIGHT, GameConfig.WorldConfig.WORLD_WIDTH, GameConfig.HudConfig.HUD_TOP_HEIGHT);
        //-----HUD TOP-----
        float topY = GameConfig.WorldConfig.WORLD_HEIGHT - 10;
        float x = 10;

        //TIME
        font.draw(batch, "Time: " + (int)gameController.getDb().gameTime, x, topY);
        x += 120;

        //ENEMIES LEFT
        int aliveEnemies = 0;
        for (Enemy e : gameController.getDb().enemies) {
            if (e.isAlive) {
                aliveEnemies++;
            }
        }
        font.draw(batch, "Alives: " + aliveEnemies, x, topY);
        x += 120;

        font.draw(batch, "Score: " + db.score, x, topY);
        x += 120;

        //DIFFICULTY MULTIPLIER
        font.draw(batch, "Difficulty: " + String.format("%.2f", gameController.getDb().difficultyMultiplier), x, topY);
        x += 120;

        //TIME FACTOR
        float timeFactor = (int)(gameController.getDb().gameTime / 30) * 0.5f;
        font.draw(batch, "Time Factor: " + String.format("%.2f", timeFactor), x, topY);
        x += 120;

        //MOVEMENT 
        font.draw(batch, "Move: " + (int)gameController.getDb().formationSpeed, x, topY);
        x += 120;

        //COOLDOWN
        font.draw(batch, "Cooldown: " + String.format("%.2f", gameController.getDb().enemyShootCooldown), x, topY);
        x += 120;

        //FPS
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), x, topY);


        //-----HUD BOTTOM-----
        shapeRenderer.rect(0, 0, GameConfig.WorldConfig.WORLD_WIDTH, GameConfig.HudConfig.HUD_BOTTOM_HEIGHT);
        //-----HUD BOTTOM-----

        float lifeSize = 38f;
        float padding = 10f;

        float startX = padding;
        float starY = padding;

        for (int i = 0; i < gameController.getDb().playerLives; i++) {
            batch.draw(gameController.getDb().assets.playerTexture, startX + i * (lifeSize + padding), starY, lifeSize, lifeSize);
        }
        
        batch.end();

        shapeRenderer.end();

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
