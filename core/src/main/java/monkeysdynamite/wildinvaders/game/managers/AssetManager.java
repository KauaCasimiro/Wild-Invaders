package monkeysdynamite.wildinvaders.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import monkeysdynamite.wildinvaders.game.tools.AnimationLoader;

public class AssetManager {
    //-----PLAYER-----
    public Animation<TextureRegion> playerIdleAnimation;
    public Animation<TextureRegion> playerAttackAnimation;

    private Texture[] playerAttackFrames;

    //-----ENEMIES-----
    public Texture tractorTexture;
    public Texture farmerTexture;
    public Texture minerTexture;

    //-----PROJECTILES-----
    public Texture dynamiteTexture;
    public Texture bulletTexture;
    public Texture pickaxeTexture;

    // BARRIERS
    public Texture barrierIdle;
    public Texture barrierDamaged;
    public Animation<TextureRegion> barrierHitAnimation;

    public AssetManager() {
        load();
    }

    private void load() {
        playerIdleAnimation = AnimationLoader.load("player/george_idle_", 4, 0.15f);
        playerAttackFrames = loadAnimationFrames("player/attack/george_attack_", 3);
        playerAttackAnimation = new Animation<>(0.08f, toRegions(playerAttackFrames));

        tractorTexture = new Texture(Gdx.files.internal("enemies/trator.png"));
        farmerTexture = new Texture(Gdx.files.internal("enemies/fazendeiro.png"));
        minerTexture = new Texture(Gdx.files.internal("enemies/garimpeiro.png"));

        dynamiteTexture = new Texture(Gdx.files.internal("projectiles/dynamite.png"));
        bulletTexture = new Texture(Gdx.files.internal("projectiles/bullet.png"));
        pickaxeTexture = new Texture(Gdx.files.internal("projectiles/pickaxe.png"));

        barrierIdle = new Texture(Gdx.files.internal("barrier/stem_stopped.png"));
        barrierDamaged = new Texture(Gdx.files.internal("barrier/stem_damaged.png"));

        Texture[] hitFrames = loadAnimationFrames("barrier/hit/stem_hit_", 5);
        barrierHitAnimation = new Animation<>(0.08f, toRegions(hitFrames));
    }

    private Texture[] loadAnimationFrames(String pathPreFix, int totalFrames) {
        Texture[] frames = new Texture[totalFrames];

        for (int i = 0; i < totalFrames; i++) {
            frames[i] = new Texture(Gdx.files.internal(pathPreFix + (i + 1) + ".png"));
        }
        return frames;
    }

    private TextureRegion[] toRegions(Texture[] textures) {
        TextureRegion[] regions = new TextureRegion[textures.length];

        for (int i = 0; i < textures.length; i++) {
            regions[i] = new TextureRegion(textures[i]);
        }
        return regions;
    }

    public void dispose() {
        //playerIdleAnimation.dispose();

        tractorTexture.dispose();
        farmerTexture.dispose();
        minerTexture.dispose();

        dynamiteTexture.dispose();
        bulletTexture.dispose();
        pickaxeTexture.dispose();
    }
}
