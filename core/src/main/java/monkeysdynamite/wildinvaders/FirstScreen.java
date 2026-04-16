package monkeysdynamite.wildinvaders;

import monkeysdynamite.wildinvaders.config.GameConfig;
import monkeysdynamite.wildinvaders.hud.MobileHud;
import com.badlogic.gdx.Screen;


/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {
    private MobileHud mobile;
    @Override
    public void show() {
        if (GameConfig.isMobile) {
            mobile = new MobileHud();
        }
    }

    @Override
    public void render(float delta) {
        if (mobile != null) {
            mobile.render();
        }
    }

    @Override
    public void resize(int width, int height) {

        if(width <= 0 || height <= 0) {
            return;
        }

        if (mobile != null) {
            mobile.resize();
        }

        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        if (mobile != null) {
            mobile.dispose();
        }
    }
}
