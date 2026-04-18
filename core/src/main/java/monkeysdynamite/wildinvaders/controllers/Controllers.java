package monkeysdynamite.wildinvaders.controllers;

public class Controllers {
    public boolean left;
    public boolean right;
    public boolean shoot;


    public void update(boolean left, boolean right, boolean shoot) {
        this.left = left;
        this.right = right;
        this.shoot = shoot;
    }
}

