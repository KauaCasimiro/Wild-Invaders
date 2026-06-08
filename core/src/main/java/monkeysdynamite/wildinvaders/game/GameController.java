package monkeysdynamite.wildinvaders.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import monkeysdynamite.wildinvaders.controllers.Controllers;
import monkeysdynamite.wildinvaders.config.GameConfig;
import monkeysdynamite.wildinvaders.entities.Player;
import monkeysdynamite.wildinvaders.game.managers.AssetManager;
import monkeysdynamite.wildinvaders.game.managers.SoundManager;
import monkeysdynamite.wildinvaders.game.managers.GameManager;
import monkeysdynamite.wildinvaders.game.tools.RenderTool;

public class GameController {
    private GameDatabase db;

    private Controllers controllers;

    private GameManager gameManager;

    private AssetManager assets;

    private SoundManager sounds;

    private RenderTool renderTool;


    public GameController(int savedHighScore) {
       //DATABASE
        db = new GameDatabase();
        db.highScore = savedHighScore;
        db.runStats.highScore = savedHighScore;

       //INPUT
        controllers = new Controllers();

        //MANAGERS
        assets = new AssetManager();
        sounds = new SoundManager();
        db.assets = assets;
        db.sound = sounds;
        gameManager = new GameManager(assets);
        db.life = gameManager.getLifeSystem();
        renderTool = new RenderTool();

        //PLAYER
        db.player = new Player(400.0f, GameConfig.GameArea.GAME_MIN_Y + 20, 48.0f, 48.0f);

        //INITIALIZE GAMEPLAY
        gameManager.initializeEnemies(db);
        gameManager.initializeBarriers(db);

        db.playerLives = 3;
        db.maxPlayerLives = 3;

        db.sound.playGameMusic();

    }

    public void update(boolean left, boolean right, boolean shoot, boolean pause) {
        controllers.update(left, right, shoot, pause);

        db.left = controllers.left;
        db.right = controllers.right;
        db.shoot = controllers.shoot;
        db.pause = controllers.pause;

        float delta = Gdx.graphics.getDeltaTime();

        gameManager.update(db, delta);
    }

    public void render(SpriteBatch batch) {

        renderTool.render(batch, db);
    }

    public void renderEffect(ShapeRenderer shapeRenderer) {
        renderTool.renderParticles(shapeRenderer, db);
    }

    public void dispose() {
       gameManager.dispose(db);
       sounds.dispose();
       assets.dispose();
    }

    public GameDatabase getDb() {
        return db;
    }
}
