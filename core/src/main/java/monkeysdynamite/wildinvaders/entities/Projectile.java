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

    private Texture texture;
    private ProjectileType type;

    public enum ProjectileType {
        DYNAMITE,
        BULLET,
        PICKAXE
    }

    public int collumnIndex;

    public Projectile(float x, float y, ProjectileType type, Texture texture) {
        this.x = x;
        this.y = y;

        this.type = type;
        this.texture = texture;

        this.isActive = true;

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

    public ProjectileType getType() {
        return type;
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

    public void render(SpriteBatch batch) {
        if (!isActive) {
            return;
        }
        batch.draw(texture, x, y, width, height);
    }
}
