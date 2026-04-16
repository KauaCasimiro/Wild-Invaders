package monkeysdynamite.wildinvaders;

import com.badlogic.gdx.Game;
import monkeysdynamite.wildinvaders.config.GameConfig;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    @Override
    public void create() {
        GameConfig.init();
        setScreen(new FirstScreen());
    }
}
