package monkeysdynamite.wildinvaders.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import monkeysdynamite.wildinvaders.controllers.Controllers;
import monkeysdynamite.wildinvaders.entities.Dynamite;
import monkeysdynamite.wildinvaders.entities.Player;
import monkeysdynamite.wildinvaders.entities.Enemy;
import monkeysdynamite.wildinvaders.config.GameConfig;

import java.util.ArrayList;
import java.util.Iterator;

public class GameController {
    private Controllers controllers;
    private Player player;
    private Dynamite dynamite;
    private  boolean canShoot;

    private ArrayList <Enemy> enemies;

    private float formationSpeed = 50.0f;
    private int direction = 1;


    public GameController() {
        controllers = new Controllers();
        player = new Player(400.0f, 10.0f, 64.0f, 64.0f);

        enemies = new ArrayList<>();

        int rows = 6;
        int cols = 9;
        int spacingX = 80;
        int spacingY = 60;
        float starX = 100;
        float starY = 180;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                float x =  starX + col * spacingX;
                float y = starY + row * spacingY;

                enemies.add(new Enemy(x, y));
            }
        }

        canShoot = true;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public Dynamite getDynamite() {
        return dynamite;
    }

    public void update(boolean left, boolean right, boolean shoot) {

        //Unify in the class Controllers
        controllers.update(left, right, shoot);
        player.updateMovement(controllers);


        float delta = Gdx.graphics.getDeltaTime();

        boolean hitEdge = false;

        for (Enemy enemy : enemies) {

            if (enemy.x > GameConfig.WorldConfig.WORLD_WIDTH - 64 || enemy.x < 0) {
                hitEdge = true;
                break;
            }
        }

        if (hitEdge) {
            direction *= -1;

            for (Enemy enemy :  enemies) {
                enemy.y -= 20;
                enemy.setDirection(direction);
            }
        }

        for (Enemy enemy : enemies) {
            enemy.x += formationSpeed * direction * delta;
            enemy.update();
        }


        //-----SHOOT-----
        if (shoot && canShoot) {
            dynamite = new Dynamite(player.getX(), player.getY());
            //System.out.println("DYNAMITE SPAWNED at X: " + dynamite.x + " Y: " + dynamite.y);

            canShoot = false;
        }

        //-----UPDATE DYNAMITE-----
        if (dynamite != null) {
            dynamite.update(delta);

            if (!dynamite.isActive) {
                //System.out.println("DYNAMITE DESTROYED at X: " + dynamite.x + " Y: " + dynamite.y);
                dynamite = null;
                canShoot = true;
            }
        }

        //-----CHECK COLLISON-----
        if (dynamite != null && dynamite.isActive) {

            for (Enemy enemy : enemies) {

                if (!dynamite.isActive) {
                    continue;
                }
                if (dynamite.getBounds().overlaps(enemy.getBounds())) {
                    enemy.isAlive = false;
                    dynamite.isActive = false;
                    dynamite = null;
                    canShoot = true;
                    //System.out.println("HIT at X: " + enemy.x + " Y: " + enemy.y);
                    break;
                }
            }
        }

        //-----REMOVE ENEMIES-----
        Iterator<Enemy> it = enemies.iterator();

        while (it.hasNext()) {
            Enemy enemy =  it.next();

            if (!enemy.isAlive) {
                //System.out.println("REMOVING ENEMY at X: " + enemy.x + " Y: " + enemy.y);
                it.remove();
            }
        }

       //System.out.println("ENEMIES ALIVE: " + enemies.size());
    }



    public void render(SpriteBatch batch) {

        player.render(batch);
        for (Enemy enemy : enemies) {
            enemy.render(batch);
        }

        if (dynamite != null) {
            dynamite.render(batch);
        }
    }

    public void dispose() {
        player.dispose();

        if (dynamite != null && dynamite.isActive) {
            dynamite.dispose();
        }

        Enemy.disposeShared();

    }

}
