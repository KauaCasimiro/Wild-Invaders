package monkeysdynamite.wildinvaders;

import com.badlogic.gdx.Game;
import monkeysdynamite.wildinvaders.config.GameConfig;
import monkeysdynamite.wildinvaders.screen.MainMenuScreen;
import monkeysdynamite.wildinvaders.game.managers.AssetManager;
import monkeysdynamite.wildinvaders.game.managers.SaveManager;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public AssetManager assets;
    public int highScore;

    @Override
    public void create() {

        assets = new AssetManager();
        highScore = SaveManager.loadHighScore();

        
        GameConfig.init();
        setScreen(new MainMenuScreen(this));
    }
}
