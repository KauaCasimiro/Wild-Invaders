package monkeysdynamite.wildinvaders.config;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

public class GameConfig {

    public static boolean isMobile;

    public static void init () {
        Application.ApplicationType type = Gdx.app.getType();

        isMobile = (type == Application.ApplicationType.Android);
    }
}
