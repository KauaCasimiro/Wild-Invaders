package monkeysdynamite.wildinvaders.config;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

public class GameConfig {

    public static boolean isMobile;

    public static void init () {
        Application.ApplicationType type = Gdx.app.getType();

        isMobile = (type == Application.ApplicationType.Android);
    }

    public static class WorldConfig {
        public static final float WORLD_WIDTH = 1280;
        public static final float WORLD_HEIGHT = 720;

    }

    public static class HudConfig {
        public static final float HUD_TOP_HEIGHT = 80f;
        public static final float HUD_BOTTOM_HEIGHT = 80f;
    }

    public static class GameArea {
        public static final float GAME_MIN_Y = HudConfig.HUD_BOTTOM_HEIGHT;
        public static final float GAME_MAX_Y = WorldConfig.WORLD_HEIGHT - HudConfig.HUD_BOTTOM_HEIGHT;
    }
}
