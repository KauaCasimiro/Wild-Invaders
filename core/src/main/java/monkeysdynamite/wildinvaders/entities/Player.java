package monkeysdynamite.wildinvaders.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import monkeysdynamite.wildinvaders.config.GameConfig;

public class Player {
    private Rectangle player;
    private Rectangle bounds;

    public boolean isAlive;

    public float animationTimer = 0f;

    public boolean isAttacking;
    public float attackTimer = 0f;

    public Player(float x, float y, float width, float height) {

        player = new Rectangle(x, y, width, height);
        bounds = new Rectangle(x, y, width, height);

        isAlive = true;
    }

    public void updateMovement(boolean left, boolean right) {

        if (left) {
            player.x -= 200 * Gdx.graphics.getDeltaTime();
        }
        if (right) {
            player.x += 200 * Gdx.graphics.getDeltaTime();
        }

        if (player.x < 0) {
            player.x = 0;
        } else if (player.x + player.width> GameConfig.WorldConfig.WORLD_WIDTH) {
            player.x = GameConfig.WorldConfig.WORLD_WIDTH - player.width;
        }

        bounds.setPosition(player.x, player.y);
    }

    public void updateAnimation(float delta) {
        animationTimer += delta;

        if (isAttacking) {
            attackTimer += delta;

            if (attackTimer >= 0.24) {
                isAttacking = false;
                attackTimer = 0;
            }
        }
    }

    public float getX() {
        return player.x;
    }

    public float getY() {
        return player.y;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
