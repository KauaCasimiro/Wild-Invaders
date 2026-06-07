package monkeysdynamite.wildinvaders.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;
import monkeysdynamite.wildinvaders.config.GameConfig;
import monkeysdynamite.wildinvaders.entities.Barrier;
import monkeysdynamite.wildinvaders.entities.Enemy;
import monkeysdynamite.wildinvaders.entities.Projectile;
import monkeysdynamite.wildinvaders.game.GameDatabase;
import monkeysdynamite.wildinvaders.hud.HudCamera;
import monkeysdynamite.wildinvaders.hud.MobileHud;


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
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1f);

        shapeRenderer.rect(0, GameConfig.WorldConfig.WORLD_HEIGHT - GameConfig.HudConfig.HUD_TOP_HEIGHT,  GameConfig.WorldConfig.WORLD_WIDTH, GameConfig.HudConfig.HUD_TOP_HEIGHT);

        shapeRenderer.rect(0, 0, GameConfig.WorldConfig.WORLD_WIDTH, GameConfig.HudConfig.HUD_BOTTOM_HEIGHT);

        shapeRenderer.end();
    }
    public void renderHudDebug(SpriteBatch batch, BitmapFont font, GameDatabase db) {
        float topY = GameConfig.WorldConfig.WORLD_HEIGHT - 20;

        float x = 30;

        //SCORE
        font.draw(batch, "Score ", x, topY);
        boolean blink = ((int)(db.scoreBonusFlashTimer * 20)) % 2 == 0;

        if (db.scoreBonusFlash && blink) {
            font.setColor(Color.YELLOW);
        } else {
            font.setColor(Color.WHITE);
        }
        font.draw(batch, "" + db.score, x + 50, topY - 20);
        font.setColor(Color.WHITE);
        x += 100;
        
        //HIGH SCORE
        font.draw(batch, "Hi-Score: ", x, topY);
        font.draw(batch, "" + db.highScore, x + 50, topY - 20);
        x += 400;

        //TITLE GAME
        font.getData().setScale(1.5f);
        font.draw(batch, "WILD INVADERS", x, topY);
        font.getData().setScale(1f);
        x += 320;

        //WAVE
        font.draw(batch, "Wave < " + db.wave + " >", x, topY);
        x += 180;

        //ENEMIES ALIVE
        int aliveEnemies = 0;

        for (Enemy enemy : db.enemies) {
            if (enemy.isAlive) {
                aliveEnemies++;
            }
        }


        font.draw(batch, "Alives: " + aliveEnemies, x, topY);
        x += 140;

        //TIME
        font.draw(batch, "Time: " + (int) db.gameTime, x, topY);

        /*//DIFFICULTY
        font.draw(batch, "Difficulty: " + String.format("%.2f", db.difficultyMultiplier), x, topY);
        x += 170;

        // TIME FACTOR
        float timeFactor = (int)(db.gameTime / 30) * 0.5f;

        font.draw(batch, "Time Factor: " + String.format("%.2f", timeFactor), x, topY);
        x += 220;

        //MOVE SPEED
        font.draw(batch, "Move: " + (int) db.formationSpeed, x, topY);
        x += 120;

        //COOLDOWN
        font.draw(batch, "Cooldown: " + String.format("%.2f", db.enemyShootCooldown), x, topY);
        x += 160;

        //FPS
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), x, topY);*/

        if (db.isPaused) {
            font.getData().setScale(2f);
            font.draw(batch, "PAUSED", GameConfig.WorldConfig.WORLD_WIDTH / 2f - 100, GameConfig.WorldConfig.WORLD_HEIGHT / 2f);
            font.getData().setScale(1f);
        }
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
        font.getData().setScale(3.2f);
        for (FloatingScore fs : db.floatingScores) {

            font.draw(batch, "+" + fs.value + fs.bonusText, fs.x, fs.y);
        }
        font.getData().setScale(1f);
    }

    public void renderMobileHudDebug(ShapeRenderer shapeRenderer, HudCamera hudCamera, MobileHud mobile) {
        if (mobile == null) {
            return;
        }
        shapeRenderer.setProjectionMatrix(hudCamera.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        // LEFT BUTTON
        shapeRenderer.setColor(
            mobile.isLeftPressed ? Color.GREEN : Color.DARK_GRAY
        );

        Circle left = mobile.getLeftButton();

        shapeRenderer.circle(left.x, left.y, left.radius);

        // RIGHT BUTTON
        shapeRenderer.setColor(
            mobile.isRightPressed ? Color.GREEN : Color.GRAY
        );

        Circle right = mobile.getRightButton();

        shapeRenderer.circle(right.x, right.y, right.radius);
        // SHOOT BUTTON
        shapeRenderer.setColor(
            mobile.isShootPressed ? Color.GREEN : Color.RED
        );

        Circle shoot = mobile.getShootButton();

        shapeRenderer.circle(shoot.x, shoot.y, shoot.radius);

        // TOUCH POSITION
        if (Gdx.input.isTouched()) {

            Vector3 touch = new Vector3(
                Gdx.input.getX(),
                Gdx.input.getY(),
                0
            );

            hudCamera.getViewport().unproject(touch);

            shapeRenderer.setColor(Color.MAGENTA);

            float size = 20f;

            shapeRenderer.line(
                touch.x - size,
                touch.y,
                touch.x + size,
                touch.y
            );

            shapeRenderer.line(
                touch.x,
                touch.y - size,
                touch.x,
                touch.y + size
            );

            // TOUCH AREA
            shapeRenderer.circle(touch.x, touch.y, 30f);
        }

        shapeRenderer.end();
    }

    public void renderMobileInputText (SpriteBatch batch, BitmapFont font, HudCamera hudCamera, MobileHud mobile) {
        if (mobile == null) {
            return;
        }

        Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

        hudCamera.getViewport().unproject(touch);

        float x = 20f;
        float y = 200f;

        font.draw(batch, "=== MOBILE DEBUG ===", x, y);
        y -= 25;

        font.draw(batch, "Touched: " + Gdx.input.isTouched(), x, y);
        y -= 25;

        font.draw(batch, "Screen X: " + Gdx.input.getX(), x, y);
        y -= 25;

        font.draw(batch, "Screen Y: " + Gdx.input.getY(), x, y);
        y -= 25;

        font.draw(batch, "HUD X: " + (int) touch.x, x, y);
        y -= 25;

        font.draw(batch, "HUD Y: " + (int) touch.y, x, y);
        y -= 25;

        font.draw(batch, "LEFT: " + mobile.isLeftPressed, x, y);
        y -= 25;

        font.draw(batch, "RIGHT: " + mobile.isRightPressed, x, y);
        y -= 25;

        font.draw(batch, "SHOOT: " + mobile.isShootPressed, x, y);
    }

    public void updateDebugInput(GameDatabase db) {

        // REMOVE LIMITAÇÃO DE TIRO
        if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {

            db.debugInfiniteShoot = true;

            System.out.println("DEBUG: Infinite Shoot ON");
        }

        // VOLTA LIMITAÇÃO
        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {

            db.debugInfiniteShoot = false;

            System.out.println("DEBUG: Infinite Shoot OFF");
        }

        // REMOVE VIDA
        if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {

            db.playerLives--;

            System.out.println("DEBUG: -1 Life");
        }

        // ADICIONA VIDA
        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {

            db.playerLives++;

            System.out.println("DEBUG: +1 Life");
        }
    }

}
