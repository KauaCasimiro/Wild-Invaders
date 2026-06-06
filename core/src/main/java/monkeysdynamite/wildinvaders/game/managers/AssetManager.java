package monkeysdynamite.wildinvaders.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import monkeysdynamite.wildinvaders.game.tools.AnimationLoader;
import monkeysdynamite.wildinvaders.game.tools.AnimationData;

public class AssetManager {
    //-----PLAYER-----
    public AnimationData playerIdleAnimation;
    public AnimationData playerAttackAnimation;

    //-----ENEMIES-----
    public Texture tractorTexture;
    public AnimationData tractorAnimation;

    public Texture farmerTexture;
    public AnimationData farmerAnimation;

    public Texture minerTexture;
    public AnimationData minerAnimation;

    //-----PROJECTILES-----
    public AnimationData dynamiteAnimation;

    public AnimationData bulletAnimation;

    public AnimationData pickaxeAnimation;

    // BARRIERS
    public Texture barrierIdle;
    public Texture barrierDamaged;
    public AnimationData barrierHitAnimation;

    // -----BACKGROUNDS-----
    public Texture bgTexture;

    // ----- FONTS -----
    public BitmapFont smallFont;
    public BitmapFont mediumFont;
    public BitmapFont largeFont;
    public BitmapFont titleFont;

    public AssetManager() {
        load();
    }

    private void load() {
        smallFont = createFont(16);
        mediumFont = createFont(24);
        largeFont = createFont(32);
        titleFont = createFont(48);

        playerIdleAnimation = AnimationLoader.load("player/george_idle_", 4, 0.15f);
        playerAttackAnimation = AnimationLoader.load("player/attack/george_attack_", 3, 0.08f);

        tractorTexture = new Texture(Gdx.files.internal("enemies/trator.png"));
        tractorAnimation = AnimationLoader.load("enemies/tractor/tractor_", 5, 0.15f);
        farmerTexture = new Texture(Gdx.files.internal("enemies/fazendeiro.png"));
        farmerAnimation = AnimationLoader.load("enemies/farmer/farmer_idle_", 6, 0.15f);
        minerTexture = new Texture(Gdx.files.internal("enemies/garimpeiro.png"));
        minerAnimation = AnimationLoader.load("enemies/miner/miner_idle_", 6, 0.15f);

        //dynamiteTexture = new Texture(Gdx.files.internal("projectiles/dynamite.png"));
        dynamiteAnimation = AnimationLoader.load("projectiles/bananamite/bananamite_", 4, 0.15f);

        //bulletTexture = new Texture(Gdx.files.internal("projectiles/bullet.png"));
        bulletAnimation = AnimationLoader.load("projectiles/bullet/bullet_", 4, 0.15f);

        //pickaxeTexture = new Texture(Gdx.files.internal("projectiles/pickaxe.png"));
        pickaxeAnimation = AnimationLoader.load("projectiles/pickaxe/pickaxe_", 4, 0.15f);

        barrierIdle = new Texture(Gdx.files.internal("barrier/stem_stopped.png"));
        barrierDamaged = new Texture(Gdx.files.internal("barrier/stem_damaged.png"));

        barrierHitAnimation = AnimationLoader.load("barrier/hit/stem_hit_", 5, 0.08f);

        bgTexture = new Texture(Gdx.files.internal("backgrounds/forest_bg.png"));
    }

    private BitmapFont createFont(int size) {

        FreeTypeFontGenerator generator =
            new FreeTypeFontGenerator(
                Gdx.files.internal("fonts/Pix32.ttf")
            );

        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
            new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = size;

        BitmapFont font = generator.generateFont(parameter);

        generator.dispose();

        return font;
    }

    public void dispose() {
        playerIdleAnimation.dispose();
        playerAttackAnimation.dispose();

        tractorAnimation.dispose();
        farmerAnimation.dispose();
        minerAnimation.dispose();

        dynamiteAnimation.dispose();
        bulletAnimation.dispose();
        pickaxeAnimation.dispose();

        smallFont.dispose();
        mediumFont.dispose();
        largeFont.dispose();
        titleFont.dispose();


        tractorTexture.dispose();
        farmerTexture.dispose();
        minerTexture.dispose();

        barrierIdle.dispose();
        barrierDamaged.dispose();
        barrierHitAnimation.dispose();
        
        bgTexture.dispose();
    }
}
