package monkeysdynamite.wildinvaders.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Enemy {
    public float x, y;
    public float width, height;

    public boolean isAlive;

    private Texture texture;
    private Sprite sprite;
    private Rectangle bounds;

    private EnemyType  type;
    public int rowIndex;
    public int columnIndex;

    public float animationTimer;
    public float rotation;
    public boolean isShooting;
    public float shootVisualTimer;
    public enum EnemyType {
        TRACTOR,
        FARMER,
        MINER
    }

    public Enemy (float x, float y, EnemyType type, Texture texture) {
        this.x = x;
        this.y = y;

        this.type = type;
        this.texture = texture;

        this.width = 48;
        this.height = 48;

        this.isAlive = true;

        sprite = new Sprite(texture);

        rotation = 90;


        bounds = new Rectangle(x, y, width, height);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setDirection(int direction) {
        //direction: -1 (left) | 1 (right)

        if (direction < 0 && !sprite.isFlipX()) {
            sprite.flip(true, false);
        } else if (direction > 0 && !sprite.isFlipY()) {
            sprite.flip(true, false);
        }
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public EnemyType getType() {
        return type;
    }

    public Texture getTexture() {
        return texture;
    }

    public Projectile.ProjectileType getProjectileType() {
        switch (type) {
            case FARMER:
                return Projectile.ProjectileType.BULLET;

            case MINER:
                return Projectile.ProjectileType.PICKAXE;

            case TRACTOR:
                return null;
        }
        return null;
    }

    public void update(float delta) {
        bounds.setPosition(x, y);
        animationTimer += delta;

        if (isShooting) {
            shootVisualTimer -= delta;
            if (shootVisualTimer <= 0) {
                isShooting = false;
            }
        }

        sprite.setBounds(x, y, width, height);

    }
}
