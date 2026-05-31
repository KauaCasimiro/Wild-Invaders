package monkeysdynamite.wildinvaders.entities;

public class EffectParticle {
    public float x, y, vx, vy;
    public float life;
    public boolean isActive = true;

    public EffectParticle(float x, float y, float vx, float vy, float life) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.life = life;
    }
}
