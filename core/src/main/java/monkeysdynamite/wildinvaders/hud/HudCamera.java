package monkeysdynamite.wildinvaders.hud;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class HudCamera {

    private OrthographicCamera camera;
    private Viewport viewport;

    public HudCamera(float width, float height) {
        camera = new OrthographicCamera();
        viewport = new FitViewport(width, height, camera);

        camera.position.set(width / 2f, height / 2f, 0);
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
