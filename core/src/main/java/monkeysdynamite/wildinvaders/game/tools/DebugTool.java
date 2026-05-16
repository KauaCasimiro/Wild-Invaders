package monkeysdynamite.wildinvaders.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import monkeysdynamite.wildinvaders.config.GameConfig;
import monkeysdynamite.wildinvaders.entities.Barrier;
import monkeysdynamite.wildinvaders.entities.Enemy;
import monkeysdynamite.wildinvaders.entities.Player;
import monkeysdynamite.wildinvaders.entities.Projectile;
import monkeysdynamite.wildinvaders.game.GameDatabase;


public class DebugTool {
    public void renderColliders(ShapeRenderer shapeRenderer, OrthographicCamera camera, GameDatabase db) {
        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        float worldWidth = GameConfig.WorldConfig.WORLD_WIDTH;
        float worldHeight = GameConfig.WorldConfig.WORLD_HEIGHT;

        //WORLD
        shapeRenderer.rect(0,0, worldWidth, worldHeight);

        for (Enemy enemy : db.enemies) {

            shapeRenderer.rect(enemy.getBounds().x, enemy.getBounds().y, enemy.getBounds().width, enemy.getBounds().height);

        }

        for (Projectile projectile : db.projectiles) {

            shapeRenderer.rect(projectile.getBounds().x, projectile.getBounds().y, projectile.getBounds().width, projectile.getBounds().height);

        }

        shapeRenderer.rect(db.player.getBounds().x, db.player.getBounds().y, db.player.getBounds().width, db.player.getBounds().height);

        for (Barrier barrier : db.barriers) {
            shapeRenderer.rect(barrier.getBounds().x, barrier.getBounds().y, barrier.getBounds().width, barrier.getBounds().height);
        }

        shapeRenderer.end();
    }

    public void renderHudAreas(ShapeRenderer shapeRenderer, OrthographicCamera camera) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        shapeRenderer.rect(0, GameConfig.WorldConfig.WORLD_HEIGHT - GameConfig.HudConfig.HUD_TOP_HEIGHT,  GameConfig.WorldConfig.WORLD_WIDTH, GameConfig.HudConfig.HUD_TOP_HEIGHT);

        shapeRenderer.rect(0, 0, GameConfig.WorldConfig.WORLD_WIDTH, GameConfig.HudConfig.HUD_BOTTOM_HEIGHT);

        shapeRenderer.end();
    }
    public void renderHudDebug(SpriteBatch batch, BitmapFont font, GameDatabase db) {
        float topY = GameConfig.WorldConfig.WORLD_HEIGHT - 10;

        float x = 10;

        //TIME
        font.draw(batch, "Time: " + (int) db.gameTime, x, topY);
        x += 120;

        //ENEMIES ALIVE
        int aliveEnemies = 0;

        for (Enemy enemy : db.enemies) {
            if (enemy.isAlive) {
                aliveEnemies++;
            }
        }


        font.draw(batch, "Alives: " + aliveEnemies, x, topY);
        x += 120;

        //SCORE
        font.draw(batch, "Score: " + db.score, x, topY);
        x += 120;

        //HIGH SCORE
        font.draw(batch, "High Score: " + db.highScore, x, topY);
        x += 120;

        //DIFFICULTY
        font.draw(batch, "Difficulty: " + String.format("%.2f", db.difficultyMultiplier), x, topY);
        x += 120;

        // TIME FACTOR
        float timeFactor = (int)(db.gameTime / 30) * 0.5f;

        font.draw(batch, "Time Factor: " + String.format("%.2f", timeFactor), x, topY);
        x += 120;

        //MOVE SPEED
        font.draw(batch, "Move: " + (int) db.formationSpeed, x, topY);
        x += 120;

        //COOLDOWN
        font.draw(batch, "Cooldown: " + String.format("%.2f", db.enemyShootCooldown), x, topY);
        x += 120;

        //FPS
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), x, topY);
    }

    public void renderWaveMessage(SpriteBatch batch, BitmapFont font, GameDatabase db) {

        if (!db.waveTransition) {
            return;
        }

        String text = "WAVE " + db.wave;

        float centerX = GameConfig.WorldConfig.WORLD_WIDTH / 2f;
        float centerY = GameConfig.WorldConfig.WORLD_HEIGHT / 2f;

        font.draw(batch, text, centerX - 40, centerY);
    }

    public void renderFloatingScores(SpriteBatch batch, BitmapFont font, GameDatabase db) {
        for (FloatingScore fs : db.floatingScores) {

            font.draw(batch, "+" + fs.value + fs.bonusText, fs.x, fs.y);
        }
    }
}
