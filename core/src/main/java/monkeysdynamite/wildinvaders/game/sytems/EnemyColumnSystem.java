package monkeysdynamite.wildinvaders.game.sytems;

import monkeysdynamite.wildinvaders.entities.Enemy;
import monkeysdynamite.wildinvaders.entities.Projectile;
import monkeysdynamite.wildinvaders.game.GameDatabase;

import java.util.List;
public class EnemyColumnSystem implements GameSystem {

    private static final float COLUMN_TOLERANCE = 28f;

    @Override
    public void update(GameDatabase db, float delta) {

        // limpa o cache a cada frame
        db.frontEnemyByColumn.clear();

        calculateFrontEnemies(db.enemies, db);
    }

    private void calculateFrontEnemies(List<Enemy> enemies, GameDatabase db) {

        for (Enemy e : enemies) {

            if (!e.isAlive) {
                continue;
            }

            int col = getColumn(e);

            Enemy current = db.frontEnemyByColumn.get(col);

            if (current == null || e.y < current.y) {
                db.frontEnemyByColumn.put(col, e);
            }
        }
    }

    private int getColumn(Enemy e) {
        // transforma X em “coluna lógica”
        return e.columnIndex;
    }
}
