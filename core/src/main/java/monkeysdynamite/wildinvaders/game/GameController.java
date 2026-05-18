package monkeysdynamite.wildinvaders.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import monkeysdynamite.wildinvaders.controllers.Controllers;
import monkeysdynamite.wildinvaders.config.GameConfig;
import monkeysdynamite.wildinvaders.entities.Player;
import monkeysdynamite.wildinvaders.game.managers.AssetManager;
import monkeysdynamite.wildinvaders.game.managers.GameManager;
import monkeysdynamite.wildinvaders.game.tools.RenderTool;

public class GameController {
    private GameDatabase db;

    private Controllers controllers;

    private GameManager gameManager;

    private AssetManager assets;

    private RenderTool renderTool;


    public GameController() {
       //DATABASE
        db = new GameDatabase();

       //INPUT
        controllers = new Controllers();

        //MANAGERS
        assets = new AssetManager();
        db.assets = assets;
        gameManager = new GameManager(assets);
        renderTool = new RenderTool();

        //PLAYER
        db.player = new Player(400.0f, GameConfig.GameArea.GAME_MIN_Y + 20, 48.0f, 48.0f);

        //INITIALIZE GAMEPLAY
        gameManager.initializeEnemies(db);
        gameManager.initializeBarriers(db);

    }

    public void update(boolean left, boolean right, boolean shoot) {
        controllers.update(left, right, shoot);

        db.left = controllers.left;
        db.right = controllers.right;
        db.shoot = controllers.shoot;

        float delta = Gdx.graphics.getDeltaTime();

        gameManager.update(db, delta);
    }

    public void render(SpriteBatch batch) {

        renderTool.render(batch, db);
    }

    public void dispose() {
       gameManager.dispose(db);
    }

    public GameDatabase getDb() {
        return db;
    }
}
