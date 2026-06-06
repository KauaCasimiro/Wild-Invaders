package monkeysdynamite.wildinvaders.game.tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationData {
    private Animation<TextureRegion> animation;
    private Texture[] textures;

    public AnimationData(Animation<TextureRegion> animation, Texture[] textures) {
        this.animation = animation;
        this.textures = textures;
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    public void dispose() {
        for (Texture texture : textures) {
            texture.dispose();
        }
    }
}
