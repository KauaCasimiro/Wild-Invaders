package monkeysdynamite.wildinvaders.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.badlogic.gdx.math.MathUtils;

import monkeysdynamite.wildinvaders.controllers.Controllers;
import monkeysdynamite.wildinvaders.entities.Barrier;
import monkeysdynamite.wildinvaders.entities.Player;
import monkeysdynamite.wildinvaders.entities.Enemy;
import monkeysdynamite.wildinvaders.entities.Projectile;
import monkeysdynamite.wildinvaders.game.managers.AssetManager;
import monkeysdynamite.wildinvaders.game.tools.FloatingScore;
import monkeysdynamite.wildinvaders.game.tools.ScoreRange;

public class GameDatabase {

    public Player player;

    public ArrayList<Projectile> projectiles;
    public ArrayList<Enemy> enemies;
    public List<Barrier> barriers;

    public Controllers controllers;

    public int formationDirection = 1;


    public float enemyShootTimer = 0f;
    public float enemyShootCooldown = 1.0f;
    public int maxEnemyProjectiles = 3;

    public AssetManager assets;

    public boolean left;
    public boolean right;
    public boolean shoot;

    public Map<Integer, Enemy> frontEnemyByColumn = new HashMap<>();

    public int playerLives = 3;

    public boolean isGameOver = false;

    public float gameTime = 0f;

    public int totalEnemies = 0;

    public float difficultyMultiplier = 1f;

    public float baseFormationSpeed = 50f;
     public float formationSpeed = 50f;

    public float baseEnemyShootCooldown = 1.0f;

    //SCORE
    public int score = 0;
    public int lastScoreGain = 0;
    public int highScore = 0;

    public int scoreTier = 0;

    public ScoreRange tractorScoreRange = new ScoreRange(10, 80);
    public ScoreRange farmerScoreRange = new ScoreRange(90, 150);
    public ScoreRange minerScoreRange = new ScoreRange(160, 220);

    public Map<Integer, Integer> alivesEnemiesByRow = new HashMap<>();
    public Map<Integer, Integer> alivesEnemiesByColumn = new HashMap<>();

    public ArrayList<FloatingScore> floatingScores = new ArrayList<>();

    public GameDatabase() {
        enemies = new ArrayList<>();
        projectiles = new ArrayList<>();
        controllers = new Controllers();
        barriers = new ArrayList<>();
    }

    public int getColumn(float x) {
        return Math.round(x / 28f);
    }

    public boolean isPlayerActive() {
        return player.isAlive && !isGameOver;
    }
}
