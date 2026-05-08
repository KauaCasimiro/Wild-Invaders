package monkeysdynamite.wildinvaders.game.managers;


import com.badlogic.gdx.graphics.Texture;
import monkeysdynamite.wildinvaders.config.GameConfig;
import monkeysdynamite.wildinvaders.entities.Enemy;
import monkeysdynamite.wildinvaders.game.GameDatabase;
import monkeysdynamite.wildinvaders.game.sytems.*;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private List<GameSystem> systems;
    private AssetManager assets;

    public GameManager(AssetManager assets) {
        this.assets = assets;

        systems = new ArrayList<>();

        //Player systems
        systems.add(new PlayerControlSystem());
        systems.add(new PlayerShootingSystem());

        //Enemies systems
        systems.add(new EnemyFormationSystem());
        systems.add(new EnemyColumnSystem());
        systems.add(new EnemyShootingSystem());

        //Combat system
        systems.add(new ProjectileMovementSystem());
        systems.add(new CollisionsSystem());

        //Cleaning system
        systems.add(new CleanupSystem());
    }

    public void update(GameDatabase db, float delta) {
        for (GameSystem system : systems) {
            system.update(db, delta);
        }
    }

    public void intializeEnemies(GameDatabase db) {
        db.enemies.clear();

        int rows = 6;
        int cols = 11;
        int spacingX = 56;
        int spacingY = 48;
        float starX = 50;
        float starY = GameConfig.GameArea.GAME_MIN_Y + 200;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                float x =  starX + col * spacingX;
                float y = starY + row * spacingY;

                Enemy.EnemyType type;
                Texture texture;


                if (row < 2) {
                    type = Enemy.EnemyType.TRACTOR;
                    texture = assets.tractorTexture;
                } else if (row < 4) {
                    type = Enemy.EnemyType.FARMER;
                    texture = assets.farmerTexture;
                } else {
                    type = Enemy.EnemyType.MINER;
                    texture = assets.minerTexture;
                }

                Enemy enemy = new Enemy(x, y, type,  texture);
                enemy.rowIndex = row;
                enemy.columnIndex = col;

                db.enemies.add(enemy);
            }
        }

    }

    public void dispose(GameDatabase db) {
        assets.dispose();
    }
}
