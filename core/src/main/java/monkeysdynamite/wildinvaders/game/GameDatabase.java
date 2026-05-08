package monkeysdynamite.wildinvaders.game;

import java.util.ArrayList;

import monkeysdynamite.wildinvaders.controllers.Controllers;
import monkeysdynamite.wildinvaders.entities.Player;
import monkeysdynamite.wildinvaders.entities.Enemy;
import monkeysdynamite.wildinvaders.entities.Projectile;
import monkeysdynamite.wildinvaders.game.managers.AssetManager;

public class GameDatabase {

    public Player player;

    public ArrayList<Projectile> projectiles;
    public ArrayList<Enemy> enemies;

    public Controllers controllers;

    public int formationDirection = 1;
    public float formationSpeed = 50f;

    public float enemyShootTimer = 0f;
    public float enemyShootCooldown = 1.0f;
    public int maxEnemyProjectiles = 3;

    public AssetManager assets;

    public boolean left;
    public boolean right;
    public boolean shoot;

    public GameDatabase() {
        enemies = new ArrayList<>();
        projectiles = new ArrayList<>();
        controllers = new Controllers();
    }

}
