package monkeysdynamite.wildinvaders.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetManager {
    //-----PLAYER-----
    public Texture playerTexture;

    //-----ENEMIES-----
    public Texture tractorTexture;
    public Texture farmerTexture;
    public Texture minerTexture;

    //-----PROJECTILES-----
    public Texture dynamiteTexture;
    public Texture bulletTexture;
    public Texture pickaxeTexture;

    public AssetManager() {
        load();
    }

    private void load() {
        playerTexture = new Texture(Gdx.files.internal("player/george_idle_1.png"));

        tractorTexture = new Texture(Gdx.files.internal("enemies/trator.png"));
        farmerTexture = new Texture(Gdx.files.internal("enemies/fazendeiro.png"));
        minerTexture = new Texture(Gdx.files.internal("enemies/garimpeiro.png"));

        dynamiteTexture = new Texture(Gdx.files.internal("projectiles/dynamite.png"));
        bulletTexture = new Texture(Gdx.files.internal("projectiles/bullet.png"));
        pickaxeTexture = new Texture(Gdx.files.internal("projectiles/pickaxe.png"));
    }

    public void dispose() {
        playerTexture.dispose();

        tractorTexture.dispose();
        farmerTexture.dispose();
        minerTexture.dispose();

        dynamiteTexture.dispose();
        bulletTexture.dispose();
        pickaxeTexture.dispose();
    }
}
