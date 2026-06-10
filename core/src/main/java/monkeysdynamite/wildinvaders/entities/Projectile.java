package monkeysdynamite.wildinvaders.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Projectile {

    public boolean isActive;

    private float vspd;

    private int width = 48;
    private int height = 48;

    private Rectangle bounds;
    private float hitBoxOffset = 10f;

    public float x;
    public float y;

    private ProjectileType type;

    public float animationTimer = 0f;

    public enum ProjectileType {
        DYNAMITE,
        BULLET,
        PICKAXE
    }

    public int collumnIndex;

    public Projectile(float x, float y, ProjectileType type) {
        this.x = x;
        this.y = y;

        this.type = type;

        this.isActive = true;

        bounds = new Rectangle(x + hitBoxOffset, y + hitBoxOffset, width - hitBoxOffset * 2, height - hitBoxOffset * 2);

        switch (type) {
            case DYNAMITE:
                this.x = x + 20f;
                this.y = y;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void update(float delta) {
        if (!isActive) {
            return;
        }

        if (type != ProjectileType.DYNAMITE) {
            y -= vspd * delta;
            animationTimer += delta;
        } else  {
            y += vspd * delta;
            animationTimer += delta;
        }

        if (y > Gdx.graphics.getHeight() || y + height < 0) {
            isActive = false;
        }

        bounds.setPosition(x + hitBoxOffset, y + hitBoxOffset);
    }
}
