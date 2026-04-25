package monkeysdynamite.wildinvaders.config;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameCamera {

    private OrthographicCamera camera;
    private Viewport viewport;



    public GameCamera(float worldWidth, float worldHeight) {
        camera = new OrthographicCamera();
        viewport = new FitViewport(worldWidth, worldHeight, camera);

        camera.position.set(worldWidth / 2f, worldHeight / 2f, 0);
        camera.update();
    }

    public void apply() {
        viewport.apply();
        camera.update();
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
