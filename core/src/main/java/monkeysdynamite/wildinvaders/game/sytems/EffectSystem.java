package monkeysdynamite.wildinvaders.game.sytems;

import java.util.Iterator;

import monkeysdynamite.wildinvaders.entities.EffectParticle;
import monkeysdynamite.wildinvaders.game.GameDatabase;

public class EffectSystem implements GameSystem {
    @Override
    public void update(GameDatabase db, float delta) {

        updatePlayerFlash(db, delta);
        updateParticle(db, delta);
        updateScoreBonusFlash(db, delta);
    }

    private void updatePlayerFlash(GameDatabase db, float delta) {
        if (!db.playerDamageFlash) {
            return;
        }

        db.playerFlashTimer += delta;

        if (db.playerFlashTimer >= 0.25f) {
            db.playerDamageFlash = false;
            db.playerFlashTimer = 0f;
        }
    }

    private void updateScoreBonusFlash(GameDatabase db, float delta) {
        if (!db.scoreBonusFlash) {
            return;
        }

        db.scoreBonusFlashTimer += delta;

        if (db.scoreBonusFlashTimer >= 0.5f) {
            db.scoreBonusFlash = false;
        }
    }

    private void updateParticle(GameDatabase db, float delta) {

        Iterator<EffectParticle> iterator = db.particles.iterator();
        while (iterator.hasNext()) {

            EffectParticle p = iterator.next();

            p.x += p.vx * delta;
            p.y += p.vy * delta;

            p.life -= delta;

            if (p.life <= 0) {
                iterator.remove();
            }
        }
    }
}
