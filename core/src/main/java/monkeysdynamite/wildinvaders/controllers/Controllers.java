package monkeysdynamite.wildinvaders.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Controllers {
    public boolean left;
    public boolean right;


    public void update(boolean keyLeft, boolean keyRight, boolean buttonLeft, boolean buttonRight) {
        left = keyLeft || buttonLeft;
        right = keyRight || buttonRight;
    }
}
