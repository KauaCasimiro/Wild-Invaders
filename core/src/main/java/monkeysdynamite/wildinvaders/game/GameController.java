package monkeysdynamite.wildinvaders.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import monkeysdynamite.wildinvaders.controllers.Controllers;
import monkeysdynamite.wildinvaders.entities.Dynamite;
import monkeysdynamite.wildinvaders.entities.Player;

public class GameController {
    private Controllers controllers;
    private Player player;
    private Dynamite dynamite;
    private  boolean canShoot;

    public GameController() {
        controllers = new Controllers();
        player = new Player(200.0f, 10.0f, 100.0f, 100.0f);

        canShoot = true;
    }


    public void update(boolean left, boolean right, boolean shoot) {

        //Unify in the class Controllers
        controllers.update(left, right, shoot);
        player.updateMovement(controllers);


        //-----SHOOT-----
        if (shoot && canShoot) {
            dynamite = new Dynamite(player.getX(), player.getY());

            canShoot = false;
        }

        //-----UPDATE DYNAMITE-----
        if (dynamite != null) {
            dynamite.update(Gdx.graphics.getDeltaTime());

            if (!dynamite.isActive) {
                dynamite = null;
                canShoot = true;
            }
        }
    }



    public void render(SpriteBatch batch) {

        player.render(batch);

        if (dynamite != null && dynamite.isActive) {
            batch.draw(dynamite.getTexture(), dynamite.x, dynamite.y, 20, 40);
        }
    }


}
