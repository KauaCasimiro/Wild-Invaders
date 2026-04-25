package monkeysdynamite.wildinvaders.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Enemy {
    public float x, y;
    public float width, height;

    public boolean isAlive;

    private static Texture texture;
    private Sprite sprite;
    private Rectangle bounds;

    private EnemyType  type;

    private static Texture tractorTexture;
    private static Texture farmerTexture;
    private static Texture minerTexture;

    public enum EnemyType {
        TRACTOR,
        FARMER,
        MINER
    }

    public Enemy (float x, float y, EnemyType type) {
        this.x = x;
        this.y = y;
        this.type = type;

        this.width = 48;
        this.height = 48;

        this.isAlive = true;

        if (tractorTexture == null) {
            tractorTexture = new Texture(Gdx.files.internal("enemies/trator.png"));
            farmerTexture = new Texture(Gdx.files.internal("enemies/fazendeiro.png"));
            minerTexture = new Texture(Gdx.files.internal("enemies/garimpeiro.png"));
        }

        switch (type) {
            case TRACTOR:
                sprite = new Sprite(tractorTexture);
            break;
            case FARMER:
                sprite = new Sprite(farmerTexture);
            break;
            case MINER:
                sprite = new Sprite(minerTexture);
            break;
        }

        bounds = new Rectangle(x, y, width, height);
    }

    public void render (SpriteBatch batch) {
        if (!isAlive) {
            return;
        }

        sprite.setPosition(x, y);
        sprite.setSize(width, height);
        sprite.setOriginCenter();
        sprite.draw(batch);
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

    public Projectile.ProjectileType getProjectileType() {
        switch (type) {
            case FARMER:
                return Projectile.ProjectileType.BULLET;

            case MINER:
                return Projectile.ProjectileType.PICKAXE;

            default:
                return Projectile.ProjectileType.BULLET;
        }
    }

    public void update() {
        bounds.setPosition(x, y);
    }



    public static void disposeShared() {
        if (texture != null) {
            texture.dispose();
            texture = null;
        }
    }
}
