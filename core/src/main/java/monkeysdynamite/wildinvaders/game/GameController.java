/*
* AVISO, REFATORA TUDO ISSO AQUI, TRANSFORMA TUDO EM CLASSES E MÉTODOS SE FOR NECESSÁRIO
* TENHA UTILIZAR A LÓGICA DE SCRIPTS DO GAMEMAKER, SÓ TIRA ESSE TANTO DE COISA DO GAME CONTROLLER
* VOLTA A COMENTAR AS COISAS, O CÓDIGO TA PODRE E CONFUSO
* */

package monkeysdynamite.wildinvaders.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import monkeysdynamite.wildinvaders.controllers.Controllers;
import monkeysdynamite.wildinvaders.entities.Enemy;
import monkeysdynamite.wildinvaders.config.GameConfig;
import monkeysdynamite.wildinvaders.entities.Player;
import monkeysdynamite.wildinvaders.entities.Projectile;
import monkeysdynamite.wildinvaders.game.managers.AssetManager;
import monkeysdynamite.wildinvaders.game.managers.GameManager;

public class GameController {
    private GameDatabase db;

    private Controllers controllers;

    private GameManager gameManager;

    private AssetManager assets;


    public GameController() {
       //DATABASE
        db = new GameDatabase();

       //INPUT
        controllers = new Controllers();

        //MANAGERS
        assets = new AssetManager();
        db.assets = assets;
        gameManager = new GameManager(assets);

        //PLAYER
        db.player = new Player(400.0f, GameConfig.GameArea.GAME_MIN_Y + 20, 48.0f, 48.0f, assets.playerTexture);

        //INITIALIZE GAMEPLAY
        gameManager.intializeEnemies(db);

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

        db.player.render(batch);

        for (Enemy enemy : db.enemies) {
            enemy.render(batch);
        }

        for (Projectile projectile : db.projectiles) {
            projectile.render(batch);
        }
    }

    public void dispose() {
       gameManager.dispose(db);
    }

    public GameDatabase getDb() {
        return db;
    }
}
