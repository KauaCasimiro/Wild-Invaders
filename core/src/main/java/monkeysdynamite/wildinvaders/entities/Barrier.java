package monkeysdynamite.wildinvaders.entities;

import com.badlogic.gdx.math.Rectangle;
public class Barrier {

    public float x;
    public float y;

    public float width;
    public float height;

    public boolean isActive;

    public int hit;

    public boolean isHit;
    public float hitTimer;

    public enum BarrierState {
        IDLE,
        DAMAGED,
        DESTROYED,
    }

    public BarrierState state =  BarrierState.IDLE;
    private Rectangle bounds;
    public Barrier(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;

        this.width = 80;
        this.height = 80;

        this.isActive = true;

        hit = 10;

        bounds = new Rectangle(x, y, width, height);
    }

    public void update(float delta) {
        bounds.setPosition(x, y);

        if (isHit) {
            hitTimer+= delta;

            if (hitTimer > 0.15f) {
                isHit = false;
                hitTimer = 0;
            }
        }

        if (hit <= 3) {
            state = BarrierState.DAMAGED;
        }
        if (hit <= 0) {
            state = BarrierState.DESTROYED;
        }
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
