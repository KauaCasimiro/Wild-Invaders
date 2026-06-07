package monkeysdynamite.wildinvaders.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import monkeysdynamite.wildinvaders.controllers.Controllers;
import monkeysdynamite.wildinvaders.entities.Barrier;
import monkeysdynamite.wildinvaders.entities.Player;
import monkeysdynamite.wildinvaders.entities.Enemy;
import monkeysdynamite.wildinvaders.entities.Projectile;
import monkeysdynamite.wildinvaders.game.managers.AssetManager;
import monkeysdynamite.wildinvaders.game.tools.FloatingScore;
import monkeysdynamite.wildinvaders.game.tools.ScoreRange;
import monkeysdynamite.wildinvaders.entities.EffectParticle;
import monkeysdynamite.wildinvaders.game.managers.SoundManager;
import monkeysdynamite.wildinvaders.game.sytems.LifeSystem;

public class GameDatabase {

    public Player player;

    public ArrayList<Projectile> projectiles;
    public ArrayList<Enemy> enemies;
    public List<Barrier> barriers;

    public Controllers controllers;

    public RunStats runStats = new RunStats();

    public int formationDirection = 1;

    public float enemyShootTimer = 0f;
    public float enemyShootCooldown = 1.0f;
    public int maxEnemyProjectiles = 3;

    public AssetManager assets;
    public SoundManager sound;
    public LifeSystem life;

    public boolean left;
    public boolean right;
    public boolean shoot;

    public Map<Integer, Enemy> frontEnemyByColumn = new HashMap<>();

    public int playerLives;
    public int maxPlayerLives;

    public boolean debugInfiniteShoot = false;

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
    public int nextLifeScore = 2000;
    public boolean scoreBonusFlash = false;
    public float scoreBonusFlashTimer = 0f;

    //WAVE
    public int wave = 1;
    public boolean waveTransition = false;
    public float waveTransitionTimer = 0f;
    public boolean requestNextWave = false;

    public ScoreRange tractorScoreRange = new ScoreRange(10, 80);
    public ScoreRange farmerScoreRange = new ScoreRange(90, 150);
    public ScoreRange minerScoreRange = new ScoreRange(160, 220);

    public Map<Integer, Integer> alivesEnemiesByRow = new HashMap<>();
    public Map<Integer, Integer> alivesEnemiesByColumn = new HashMap<>();

    public ArrayList<FloatingScore> floatingScores = new ArrayList<>();

    public float playerFlashTimer = 0f;
    public boolean playerDamageFlash = false;
    public ArrayList<EffectParticle> particles;

    public boolean isPaused = false;
    public boolean pause = false;

    public GameDatabase() {
        enemies = new ArrayList<>();
        projectiles = new ArrayList<>();
        controllers = new Controllers();
        barriers = new ArrayList<>();
        particles = new ArrayList<>();
    }

    public int getColumn(float x) {
        return Math.round(x / 28f);
    }

    public boolean isPlayerActive() {
        return player.isAlive && !isGameOver;
    }
}
