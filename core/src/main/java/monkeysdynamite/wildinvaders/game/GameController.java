package monkeysdynamite.wildinvaders.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import monkeysdynamite.wildinvaders.controllers.Controllers;
import monkeysdynamite.wildinvaders.entities.Player;
import monkeysdynamite.wildinvaders.entities.Enemy;
import monkeysdynamite.wildinvaders.entities.Enemy.EnemyType;
import monkeysdynamite.wildinvaders.config.GameConfig;
import monkeysdynamite.wildinvaders.entities.Projectile;

import java.util.ArrayList;
import java.util.Iterator;

public class GameController {
    private Controllers controllers;
    private Player player;

    private ArrayList <Projectile> projectiles;

    private boolean hasDynamite;

    private ArrayList <Enemy> enemies;

    private float formationSpeed = 50.0f;
    private int direction = 1;

    private float enemyShootTimer = 0;
    private float enemyShootCooldown = 1.0f;
    private int maxEnemyProjectiles = 3;


    public GameController() {
        controllers = new Controllers();
        player = new Player(400.0f, 10.0f, 80.0f, 80.0f);
        projectiles = new ArrayList<>();

        enemies = new ArrayList<>();

        int rows = 6;
        int cols = 11;
        int spacingX = 80;
        int spacingY = 60;
        float starX = 50;
        float starY = 400;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                float x =  starX + col * spacingX;
                float y = starY + row * spacingY;

                Enemy.EnemyType type;

                if (row < 2) {
                    type = Enemy.EnemyType.TRACTOR;
                } else if (row < 4) {
                    type = Enemy.EnemyType.FARMER;
                } else {
                    type = Enemy.EnemyType.MINER;
                }

                enemies.add(new Enemy(x, y, type));
            }
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
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

        enemyShootTimer += delta;

        if (enemyShootTimer >= enemyShootCooldown) {
            tryEnemyShoot();
            enemyShootTimer = 0;
        }

        int enemyShots = 0;

        for (Projectile p : projectiles) {
            if (p.getType() != Projectile.ProjectileType.DYNAMITE  && p.isActive) {
                enemyShots++;
            }
        }


        //-----SHOOT-----
        hasDynamite = false;

        for (Projectile p : projectiles) {
            if (p.getType() ==  Projectile.ProjectileType.DYNAMITE && p.isActive) {
                hasDynamite = true;
                break;
            }
        }

        if (shoot && !hasDynamite) {
            projectiles.add(new Projectile(player.getX(), player.getY(), Projectile.ProjectileType.DYNAMITE));
            //System.out.println("DYNAMITE SPAWNED at X: " + dynamite.x + " Y: " + dynamite.y);

            hasDynamite = false;
        }

        //-----UPDATE DYNAMITE-----
        for (Projectile p : projectiles) {
            p.update(delta);
        }

        //-----CHECK COLLISON WITH ENEMIES-----
        for (Projectile p : projectiles) {

            if (p.getType() != Projectile.ProjectileType.DYNAMITE) {
                continue;
            }

            if (!p.isActive) {
                continue;
            }

            for (Enemy enemy :  enemies) {
                if (p.getBounds().overlaps(enemy.getBounds())) {
                    enemy.isAlive = false;
                    p.isActive = false;
                    break;
                }
            }
        }

        for (Projectile p : projectiles) {

            if (!p.isActive) {
                continue;
            }

            if (p.getType() == Projectile.ProjectileType.DYNAMITE) {
                continue;
            }

            /*System.out.println(
                "PLAYER -> x: " + player.getBounds().x +
                    " y: " + player.getBounds().y
            );*/

            if (p.getBounds().overlaps(player.getBounds())) {
                /*System.out.println(
                    "COLLISION CHECK -> type: " + p.getType() +
                        " active: " + p.isActive +
                        " x: " + p.getBounds().x +
                        " y: " + p.getBounds().y
                );*/
                player.isAlive = false;
                p.isActive = false;
                break;
            }
        }

        //-----REMOVE PROJECTILES-----
        Iterator<Projectile> iterator = projectiles.iterator();
        while (iterator.hasNext()) {
            Projectile p = iterator.next();
            if (!p.isActive) {
                iterator.remove();
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

    private void tryEnemyShoot() {
        int enemyShots = 0;

        for  (Projectile p : projectiles) {
            if (p.getType() != Projectile.ProjectileType.DYNAMITE && p.isActive) {
                enemyShots++;
            }
        }

        if (enemyShots >= maxEnemyProjectiles) {
            return;
        }

        ArrayList<Enemy> aliveEnemies = new ArrayList<>();

        for (Enemy e: enemies) {
            if (e.isAlive) {
                aliveEnemies.add(e);
            }
        }

        if (aliveEnemies.isEmpty()) {
            return;
        }

        int index = (int)(Math.random() * aliveEnemies.size());
        Enemy shooter = aliveEnemies.get(index);

        Projectile.ProjectileType type = shooter.getProjectileType();

        projectiles.add(new Projectile(shooter.x, shooter.y, type));

    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void render(SpriteBatch batch) {

        player.render(batch);
        for (Enemy enemy : enemies) {
            enemy.render(batch);
        }

        for (Projectile p : projectiles) {
            p.render(batch);
        }
    }

    public void dispose() {
        player.dispose();

        Enemy.disposeShared();
        Projectile.disposeShared();

    }

}
