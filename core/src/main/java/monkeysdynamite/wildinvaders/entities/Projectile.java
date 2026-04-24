package monkeysdynamite.wildinvaders.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Projectile {

    public boolean isActive;

    private float vspd;

    private int width = 16;
    private int height = 32;

    private Rectangle bounds;

    public float x;
    public float y;

    private ProjectileType type;

    private static Texture dynamiteTexture;
    private static Texture pickaxeTexture;
    private static Texture bulletTexture;

    public enum ProjectileType {
        DYNAMITE,
        BULLET,
        PICKAXE
    }

    public Projectile(float x, float y, ProjectileType type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.isActive = true;

        if (dynamiteTexture == null) {
            dynamiteTexture = new Texture(Gdx.files.internal("projectiles/dynamite.png"));
            pickaxeTexture = new Texture(Gdx.files.internal("projectiles/pickaxe.png"));
            bulletTexture = new Texture(Gdx.files.internal("projectiles/bullet.png"));
        }
        bounds = new Rectangle(x, y, width, height);

        switch (type) {
            case DYNAMITE:
                vspd = 300f;
            break;
            case BULLET:
                vspd = 400f;
            break;
            case PICKAXE:
                vspd = 500f;
            break;
        }
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void update(float delta) {
        if (!isActive) {
            return;
        }

        if (type != ProjectileType.DYNAMITE) {
            y -= vspd * delta;
        } else  {
            y += vspd * delta;
        }

        if (y > Gdx.graphics.getHeight() || y + height < 0) {
            isActive = false;
        }

        bounds.setPosition(x, y);
    }

    public ProjectileType getType() {
        return type;
    }


    public void render(SpriteBatch batch) {
        if (!isActive) {
            return;
        }

        switch (type) {
            case DYNAMITE:
                batch.draw(dynamiteTexture, x, y, width, height);
            break;
            case BULLET:
                batch.draw(bulletTexture, x, y, width, height);
            break;
            case PICKAXE:
                batch.draw(pickaxeTexture, x, y, width, height);
            break;
        }
    }

    public static void disposeShared() {
        if (dynamiteTexture != null) {
            dynamiteTexture.dispose();
            dynamiteTexture = null;
        }
        if (pickaxeTexture != null) {
            pickaxeTexture.dispose();
            pickaxeTexture = null;
        }
        if (bulletTexture != null) {
            bulletTexture.dispose();
            bulletTexture = null;
        }
    }
}
