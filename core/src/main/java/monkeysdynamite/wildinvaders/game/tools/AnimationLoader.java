package monkeysdynamite.wildinvaders.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationLoader {

    public static Animation<TextureRegion> load (String baseName, int frameCount, float frameDuration) {
        TextureRegion[] frames = new TextureRegion[frameCount];

        for (int i = 0; i < frameCount; i++) {
            String fileName = baseName + (i + 1) + ".png";

            Texture texture = new Texture(Gdx.files.internal(fileName));

            frames[i] = new TextureRegion(texture);
        }

        return new Animation<>(frameDuration, frames);
    }
}
