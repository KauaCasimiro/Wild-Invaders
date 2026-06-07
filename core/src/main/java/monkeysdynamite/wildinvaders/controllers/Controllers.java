package monkeysdynamite.wildinvaders.controllers;

public class Controllers {
    public boolean left;
    public boolean right;
    public boolean shoot;
    public boolean pause;


    public void update(boolean left, boolean right, boolean shoot, boolean pause) {
        this.left = left;
        this.right = right;
        this.shoot = shoot;
        this.pause = pause;
    }
}

